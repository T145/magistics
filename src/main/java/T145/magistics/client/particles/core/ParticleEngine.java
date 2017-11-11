package T145.magistics.client.particles.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

import org.lwjgl.opengl.GL11;

import T145.magistics.Magistics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Magistics.MODID, value = Side.CLIENT)
public class ParticleEngine {

	private ParticleEngine() {}

	public static final ResourceLocation SPRITE_SHEET = new ResourceLocation(Magistics.MODID, "textures/misc/particles.png");
	public static final ResourceLocation SPRITE_SHEET_ALT = new ResourceLocation(Magistics.MODID, "textures/misc/particles2.png");
	private static HashMap[] particles = { new HashMap(), new HashMap(), new HashMap(), new HashMap() };

	public static void addEffect(World world, Particle effect) {
		addEffect(world.provider.getDimension(), effect);
	}

	public static void addEffect(int dim, Particle effect) {
		if (!particles[effect.getFXLayer()].containsKey(dim)) {
			particles[effect.getFXLayer()].put(dim, new ArrayList());
		}

		ArrayList<Particle> fx = (ArrayList) particles[effect.getFXLayer()].get(dim);

		if (fx.size() >= getParticleLimit()) {
			fx.remove(0);
		}

		fx.add(effect);
		particles[effect.getFXLayer()].put(dim, fx);
	}

	private static int getParticleLimit() {
		return FMLClientHandler.instance().getClient().gameSettings.particleSetting == 1 ? 2500 : FMLClientHandler.instance().getClient().gameSettings.particleSetting == 2 ? 1000 : 5000;
	}

	@SubscribeEvent
	public static void onPostRender(RenderWorldLastEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		TextureManager renderer = mc.renderEngine;
		int dim = mc.world.provider.getDimension();
		EntityPlayerSP player = mc.player;
		float frame = event.getPartialTicks();

		renderer.bindTexture(SPRITE_SHEET_ALT);

		GL11.glPushMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GlStateManager.enableBlend();
		GL11.glEnable(GL11.GL_BLEND); // 3042 = 0xBE2 = GL_BLEND (redundant?)
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.004F);
		GlStateManager.depthMask(false); // isTransparent (therefore calls unnecessary in particle classes)

		boolean rebound = false;

		for (int layer = 3; layer >= 0; --layer) {
			if (particles[layer].containsKey(dim)) {
				ArrayList<Particle> fx = (ArrayList) particles[layer].get(dim);

				if (!fx.isEmpty()) {
					if (!rebound && layer < 2) {
						renderer.bindTexture(SPRITE_SHEET);
						rebound = true;
					}

					GL11.glPushMatrix();

					switch (layer) {
					case 0: case 2:
						GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_CURRENT_BIT);
						break;
					case 1: case 3:
						GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
						break;
					}

					float f1 = ActiveRenderInfo.getRotationX();
					float f2 = ActiveRenderInfo.getRotationZ();
					float f3 = ActiveRenderInfo.getRotationYZ();
					float f4 = ActiveRenderInfo.getRotationXY();
					float f5 = ActiveRenderInfo.getRotationXZ();

					Particle.interpPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * frame;
					Particle.interpPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * frame;
					Particle.interpPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * frame;

					Tessellator tessellator = Tessellator.getInstance();
					VertexBuffer worldRenderer = tessellator.getBuffer();

					worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

					for (final Particle effect : fx) {
						if (effect != null) {
							try {
								effect.renderParticle(worldRenderer, player, frame, f1, f5, f2, f3, f4);
							} catch (Exception err) {
								crashParticle(err, effect);
							}
						}
					}

					tessellator.draw();
					GL11.glPopMatrix();
				}
			}
		}

		GlStateManager.depthMask(true);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.disableBlend();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glPopMatrix();
	}

	@SubscribeEvent
	public static void updateParticles(TickEvent.ClientTickEvent event) {
		/*if (event.side == Side.SERVER) {
			return;
		}*/

		Minecraft mc = FMLClientHandler.instance().getClient();
		World world = mc.world;

		if (world == null) {
			return;
		}

		int dim = world.provider.getDimension();

		if (event.phase == TickEvent.Phase.START) {
			for (int layer = 0; layer < 4; ++layer) {
				if (particles[layer].containsKey(dim)) {
					ArrayList<Particle> fx = (ArrayList) particles[layer].get(dim);

					for (int i = 0; i < fx.size(); ++i) {
						final Particle effect = fx.get(i);

						try {
							if (effect != null) {
								effect.onUpdate();
							}
						} catch (Exception err) {
							crashParticle(err, effect);
						}

						if (effect == null || !effect.isAlive()) {
							fx.remove(i--);
							particles[layer].put(dim, fx);
						}
					}
				}
			}
		}
	}

	private static Callable getParticleCallable(Particle effect) {
		return new Callable() {

			@Override
			public String call() throws Exception {
				return effect.toString();
			}
		};
	}

	private static Callable getParticleTypeCallable() {
		return new Callable() {

			@Override
			public String call() throws Exception {
				return "ENTITY_PARTICLE_TEXTURE";
			}
		};
	}

	private static void crashParticle(Exception err, Particle effect) {
		CrashReport crash = CrashReport.makeCrashReport(err, "Ticking Particle");
		CrashReportCategory category = crash.makeCategory("Particle being ticked");
		category.addCrashSection("Particle", getParticleCallable(effect));
		category.addCrashSection("Particle Type", getParticleTypeCallable());
		throw new ReportedException(crash);
	}
}