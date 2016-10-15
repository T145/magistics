package T145.magistics.client.fx;

import java.util.ArrayList;
import java.util.HashMap;
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
import net.minecraft.entity.Entity;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// Derived from Azanor's ParticleEngine
public class ParticleEngine {
	public static ParticleEngine INSTANCE = new ParticleEngine();
	public static final ResourceLocation particleTexture = new ResourceLocation(Magistics.MODID, "textures/misc/particles.png");
	protected World worldObj;
	private HashMap[] particles = { new HashMap(), new HashMap(), new HashMap(), new HashMap() };
	private Random rand = new Random();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		float frame = event.getPartialTicks();
		Entity entity = Minecraft.getMinecraft().thePlayer;
		TextureManager renderer = Minecraft.getMinecraft().renderEngine;
		int dim = Minecraft.getMinecraft().theWorld.provider.getDimension();

		GL11.glPushMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GlStateManager.enableBlend();
		GL11.glEnable(3042);
		GL11.glAlphaFunc(516, 0.003921569F);
		renderer.bindTexture(particleTexture);
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
					}

					float f1 = ActiveRenderInfo.getRotationX();
					float f2 = ActiveRenderInfo.getRotationZ();
					float f3 = ActiveRenderInfo.getRotationYZ();
					float f4 = ActiveRenderInfo.getRotationXY();
					float f5 = ActiveRenderInfo.getRotationXZ();
					Particle.interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * frame;
					Particle.interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * frame;
					Particle.interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * frame;

					Tessellator tessellator = Tessellator.getInstance();
					VertexBuffer VertexBuffer = tessellator.getBuffer();
					VertexBuffer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

					for (int j = 0; j < parts.size(); j++) {
						final Particle Particle = parts.get(j);

						if (Particle != null) {
							try {
								Particle.renderParticle(VertexBuffer, entity, frame, f1, f5, f2, f3, f4);
							} catch (Throwable throwable) {
								CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Rendering Particle");
								CrashReportCategory crashreportcategory = crashreport.makeCategory("Particle being rendered");

								crashreportcategory.setDetail("Particle", new ICrashReportDetail() {
									public String call() {
										return Particle.toString();
									}
								});

								crashreportcategory.setDetail("Particle Type", new ICrashReportDetail() {
									public String call() {
										return "ENTITY_PARTICLE_TEXTURE";
									}
								});

								throw new ReportedException(crashreport);
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

	public void addEffect(World world, Particle fx) {
		if (!particles[fx.getFXLayer()].containsKey(Integer.valueOf(world.provider.getDimension()))) {
			particles[fx.getFXLayer()].put(Integer.valueOf(world.provider.getDimension()), new ArrayList());
		}

		ArrayList<Particle> parts = (ArrayList) particles[fx.getFXLayer()].get(Integer.valueOf(world.provider.getDimension()));

		if (parts.size() >= 2000) {
			parts.remove(0);
		}

		parts.add(fx);
		particles[fx.getFXLayer()].put(Integer.valueOf(world.provider.getDimension()), parts);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void updateParticles(TickEvent.ClientTickEvent event) {
		if (event.side == Side.SERVER) {
			return;
		}

		Minecraft mc = FMLClientHandler.instance().getClient();
		World world = mc.theWorld;

		if (mc.theWorld == null) {
			return;
		}

		int dim = world.provider.getDimension();

		if (event.phase == TickEvent.Phase.START) {
			for (int layer = 0; layer < 4; layer++) {
				if (particles[layer].containsKey(Integer.valueOf(dim))) {
					ArrayList<Particle> parts = (ArrayList) particles[layer].get(Integer.valueOf(dim));

					for (int j = 0; j < parts.size(); j++) {
						final Particle Particle = parts.get(j);

						try {
							if (Particle != null) {
								Particle.onUpdate();
							}
						} catch (Exception e) {
							try {
								CrashReport report = CrashReport.makeCrashReport(e, "Ticking Particle");
								CrashReportCategory category = report.makeCategory("Particle being ticked");

								category.addCrashSection("Particle", new Callable() {
									public String call() {
										return Particle.toString();
									}
								});

								category.addCrashSection("Particle Type", new Callable() {
									public String call() {
										return "ENTITY_PARTICLE_TEXTURE";
									}
								});

								Particle.setExpired();
							} catch (Exception err) {
								Magistics.logger.catching(err);
							}
						}

						if ((Particle == null) || (!Particle.isAlive())) {
							parts.remove(j--);
							particles[layer].put(Integer.valueOf(dim), parts);
						}
					}
				}
			}
		}
	}
}