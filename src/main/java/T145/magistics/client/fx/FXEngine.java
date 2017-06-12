package T145.magistics.client.fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;

import org.lwjgl.opengl.GL11;

import T145.magistics.Magistics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
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
	private static ArrayList<ParticleDelay> particlesDelayed = new ArrayList();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onRenderWorldLast(RenderWorldLastEvent event) {
		float frame = event.getPartialTicks();
		EntityPlayer player = Minecraft.getMinecraft().player;
		int dim = Minecraft.getMinecraft().world.provider.getDimension();

		GL11.glPushMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GlStateManager.enableBlend();

		GL11.glEnable(3042);
		GL11.glAlphaFunc(516, 0.003921569F);
		Minecraft.getMinecraft().renderEngine.bindTexture(PARTICLE_TEXTURE);
		GlStateManager.depthMask(false);

		for (int layer = 3; layer >= 0; layer--) {
			if (particles[layer].containsKey(Integer.valueOf(dim))) {
				ArrayList<Particle> parts = (ArrayList) particles[layer].get(Integer.valueOf(dim));

				if (parts.size() != 0) {
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
					VertexBuffer buffer = tessellator.getBuffer();
					buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

					for (int j = 0; j < parts.size(); j++) {
						Particle particle = parts.get(j);

						if (particle != null) {
							try {
								particle.renderParticle(buffer, player, frame, f1, f5, f2, f3, f4);
							} catch (Exception err) {
								CrashReport report = CrashReport.makeCrashReport(err, "Rendering Particle");
								CrashReportCategory reportCategory = report.makeCategory("Particle being rendered");

								reportCategory.setDetail("Particle", new ICrashReportDetail() {
									public String call() {
										return particle.toString();
									}
								});

								reportCategory.setDetail("Particle Type", new ICrashReportDetail() {
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
		GL11.glPopMatrix();
	}

	public static void addEffect(World world, Particle fx) {
		addEffect(world.provider.getDimension(), fx);
	}

	private static int getParticleLimit() {
		return FMLClientHandler.instance().getClient().gameSettings.particleSetting == 1 ? 2500 : FMLClientHandler.instance().getClient().gameSettings.particleSetting == 2 ? 1000 : 5000;
	}

	public static void addEffect(int dim, Particle fx) {
		if (!particles[fx.getFXLayer()].containsKey(Integer.valueOf(dim))) {
			particles[fx.getFXLayer()].put(Integer.valueOf(dim), new ArrayList());
		}

		ArrayList<Particle> parts = (ArrayList<Particle>) particles[fx.getFXLayer()].get(Integer.valueOf(dim));

		if (parts.size() >= getParticleLimit()) {
			parts.remove(0);
		}

		parts.add(fx);
		particles[fx.getFXLayer()].put(Integer.valueOf(dim), parts);
	}

	public static void addEffectWithDelay(World world, Particle fx, int delay) {
		particlesDelayed.add(new ParticleDelay(fx, world.provider.getDimension(), delay));
	}

	@SideOnly(Side.CLIENT)
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
			Iterator<ParticleDelay> i = particlesDelayed.iterator();

			while (i.hasNext()) {
				ParticleDelay pd = (ParticleDelay) i.next();
				pd.delay -= 1;

				if (pd.delay <= 0) {
					if (pd.dim == dim) {
						addEffect(pd.dim, pd.particle);
					}

					i.remove();
				}
			}

			for (int layer = 0; layer < 4; layer++) {
				if (particles[layer].containsKey(Integer.valueOf(dim))) {
					ArrayList<Particle> parts = (ArrayList) particles[layer].get(Integer.valueOf(dim));

					for (int j = 0; j < parts.size(); j++) {
						Particle particle = parts.get(j);

						try {
							if (particle != null) {
								particle.onUpdate();
							}
						} catch (Exception err) {
							CrashReport report = CrashReport.makeCrashReport(err, "Ticking Particle");
							CrashReportCategory reportCategory = report.makeCategory("Particle being ticked");

							reportCategory.addCrashSection("Particle", new Callable() {
								public String call() {
									return particle.toString();
								}
							});

							reportCategory.addCrashSection("Particle Type", new Callable() {
								public String call() {
									return "ENTITY_PARTICLE_TEXTURE";
								}
							});

							particle.setExpired();
						}

						if (particle == null || !particle.isAlive()) {
							parts.remove(j--);
							particles[layer].put(Integer.valueOf(dim), parts);
						}
					}
				}
			}
		}
	}
}