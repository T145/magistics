package T145.magistics.client.fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;

import org.lwjgl.opengl.GL11;

import T145.magistics.Magistics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.player.EntityPlayer;
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
public class FXEngine {

	public static final ResourceLocation PARTICLE_TEXTURE = new ResourceLocation(Magistics.MODID, "textures/misc/particles.png");

	private static HashMap[] particles = { new HashMap(), new HashMap(), new HashMap(), new HashMap() };
	private static ArrayList<DelayedParticle> delayedParticles = new ArrayList();
	private Random rand = new Random();

	public static void addEffect(World world, Particle effect) {
		addEffect(world.provider.getDimension(), effect);
	}

	private static int getParticleLimit() {
		return FMLClientHandler.instance().getClient().gameSettings.particleSetting == 1 ? 2500 : FMLClientHandler.instance().getClient().gameSettings.particleSetting == 2 ? 1000 : 5000;
	}

	public static void addEffect(int dim, Particle effect) {
		if (!particles[effect.getFXLayer()].containsKey(dim)) {
			particles[effect.getFXLayer()].put(dim, new ArrayList());
		}

		ArrayList<Particle> fx = (ArrayList<Particle>) particles[effect.getFXLayer()].get(dim);

		if (fx.size() >= getParticleLimit()) {
			fx.remove(0);
		}

		fx.add(effect);

		particles[effect.getFXLayer()].put(dim, fx);
	}

	public static void addEffectWithDelay(World world, Particle effect, int delay) {
		delayedParticles.add(new DelayedParticle(effect, world.provider.getDimension(), delay));
	}

	@SubscribeEvent
	public static void onPostRender(RenderWorldLastEvent event) {
		float frame = event.getPartialTicks();
		EntityPlayer player = Minecraft.getMinecraft().player;
		TextureManager renderer = Minecraft.getMinecraft().renderEngine;
		int dim = Minecraft.getMinecraft().world.provider.getDimension();

		GlStateManager.pushMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.enableBlend();
		GlStateManager.alphaFunc(516, 0.004F);
		renderer.bindTexture(PARTICLE_TEXTURE);
		GlStateManager.depthMask(false); // isTransparent = false

		for (int layer = 3; layer >= 0; layer--) {
			if (particles[layer].containsKey(dim)) {
				ArrayList<Particle> fx = (ArrayList<Particle>) particles[layer].get(dim);

				if (fx.size() != 0) {
					switch (layer) {
					case 0:
						GlStateManager.blendFunc(770, 1);
						break;
					case 1:
						GlStateManager.blendFunc(770, 771);
						break;
					case 2:
						GlStateManager.blendFunc(770, 1);
						GlStateManager.disableDepth();
						break;
					case 3:
						GlStateManager.blendFunc(770, 771);
						GlStateManager.disableDepth();
					}

					float rotationX = ActiveRenderInfo.getRotationX();
					float rotationZ = ActiveRenderInfo.getRotationZ();
					float rotationYZ = ActiveRenderInfo.getRotationYZ();
					float rotationXY = ActiveRenderInfo.getRotationXY();
					float rotationXZ = ActiveRenderInfo.getRotationXZ();

					Particle.interpPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * frame;
					Particle.interpPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * frame;
					Particle.interpPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * frame;

					Tessellator tessellator = Tessellator.getInstance();
					VertexBuffer buffer = tessellator.getBuffer();
					buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

					for (int j = 0; j < fx.size(); j++) {
						final Particle effect = fx.get(j);

						if (effect != null) {
							try {
								effect.renderParticle(buffer, player, frame, rotationX, rotationXZ, rotationZ, rotationYZ, rotationXY);
							} catch (Exception err) {
								Magistics.LOGGER.catching(err);
								
								CrashReport report = CrashReport.makeCrashReport(err, "Rendering Particle");
								CrashReportCategory reportCategory = report.makeCategory("Particle being rendered");

								reportCategory.addDetail("Particle", new ICrashReportDetail() {
									public String call() {
										return effect.toString();
									}
								});

								reportCategory.addDetail("Particle Type", new ICrashReportDetail() {
									public String call() {
										return "ENTITY_PARTICLE_TEXTURE";
									}
								});

								throw new ReportedException(report);
							}
						}
					}

					tessellator.draw();

					switch (layer) {
					case 2: case 3:
						GlStateManager.enableDepth();
					}
				}
			}
		}

		GlStateManager.depthMask(true);
		GlStateManager.blendFunc(770, 771);
		GlStateManager.disableBlend();
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.popMatrix();
	}

	@SubscribeEvent
	public static void updateParticles(TickEvent.ClientTickEvent event) {
		if (event.side == Side.SERVER) {
			return;
		}

		Minecraft mc = FMLClientHandler.instance().getClient();
		World world = mc.world;

		if (mc.world == null) {
			return;
		}

		int dim = world.provider.getDimension();

		if (event.phase == TickEvent.Phase.START) {
			Iterator<DelayedParticle> i = delayedParticles.iterator();

			while (i.hasNext()) {
				DelayedParticle effect = i.next();
				effect.time -= 1;

				if (effect.time <= 0) {
					if (effect.getDimensionId() == dim) {
						addEffect(effect.getDimensionId(), effect.getEffect());
					}

					i.remove();
				}
			}

			for (int layer = 0; layer < 4; layer++) {
				if (particles[layer].containsKey(dim)) {
					ArrayList<Particle> fx = (ArrayList<Particle>) particles[layer].get(dim);

					for (int j = 0; j < fx.size(); j++) {
						final Particle effect = fx.get(j);

						try {
							if (effect != null) {
								effect.onUpdate();
							}
						} catch (Exception err) {
							try {
								Magistics.LOGGER.catching(err);

								CrashReport report = CrashReport.makeCrashReport(err, "Ticking Particle");
								CrashReportCategory reportCategory = report.makeCategory("Particle being ticked");

								reportCategory.addCrashSection("Particle", new Callable() {
									public String call() {
										return effect.toString();
									}
								});

								reportCategory.addCrashSection("Particle Type", new Callable() {
									public String call() {
										return "ENTITY_PARTICLE_TEXTURE";
									}
								});

								effect.setExpired();
							} catch (Exception reportErr) {
								Magistics.LOGGER.catching(reportErr);
							}
						}

						if (effect == null || !effect.isAlive()) {
							fx.remove(j--);
							particles[layer].put(dim, fx);
						}
					}
				}
			}
		}
	}
}
