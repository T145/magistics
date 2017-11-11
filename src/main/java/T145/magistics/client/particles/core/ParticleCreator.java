package T145.magistics.client.particles.core;

import java.awt.Color;

import T145.magistics.client.particles.ParticleGreenFlame;
import T145.magistics.client.particles.ParticleWisp;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ParticleCreator {

	private ParticleCreator() {}

	public static World getWorld() {
		return FMLClientHandler.instance().getClient().world;
	}

	public static int particleCount(int base) {
		return FMLClientHandler.instance().getClient().gameSettings.particleSetting == 1 ? base * 1 : FMLClientHandler.instance().getClient().gameSettings.particleSetting == 2 ? base / 2 : base * 2;
	}

	public static void greenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleGreenFlame(world, x, y, z, 0F, 0F, 0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}

	public static void smallGreenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleGreenFlame(world, x, y, z, 0F, 0F, 0F);
		flame.setScale(0.1F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}

	public static void customWispFX(double x, double y, double z, double destX, double destY, double destZ, float gravity, int type) {
		ParticleWisp fx = new ParticleWisp(getWorld(), x, y, z, destX, destY, destZ, gravity, type);
		ParticleEngine.addEffect(getWorld(), fx);
	}

	public static void wispFX(double posX, double posY, double posZ, float f, float g, float h, float i) {
		ParticleWisp effect = new ParticleWisp(getWorld(), posX, posY, posZ, f, g, h, i);
		effect.setGravity(0.02F);
		ParticleEngine.addEffect(getWorld(), effect);
	}

	public static void wispFX2(double posX, double posY, double posZ, float size, int type, boolean shrink, boolean clip, float gravity) {
		ParticleWisp effect = new ParticleWisp(getWorld(), posX, posY, posZ, size, type);
		effect.setGravity(gravity);
		effect.shrink = shrink;
		effect.canCollide(clip);
		ParticleEngine.addEffect(getWorld(), effect);
	}

	public static void wispFX3(double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity) {
		ParticleWisp effect = new ParticleWisp(getWorld(), posX, posY, posZ, posX2, posY2, posZ2, size, type);
		effect.setGravity(gravity);
		effect.shrink = shrink;
		ParticleEngine.addEffect(getWorld(), effect);
	}

	public static void wispFX4(double posX, double posY, double posZ, Entity target, int type, boolean shrink, float gravity) {
		ParticleWisp effect = new ParticleWisp(getWorld(), posX, posY, posZ, target, type);
		effect.setGravity(gravity);
		effect.shrink = shrink;
		ParticleEngine.addEffect(getWorld(), effect);
	}

	public static void wispFX5(double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, boolean shrink, float gravity, int color) {
		ParticleWisp effect = new ParticleWisp(getWorld(), posX, posY, posZ, posX2, posY2, posZ2, size, -1);
		Color c = new Color(color);
		effect.setRBGColorF(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F);
		effect.setGravity(gravity);
		effect.shrink = shrink;
		ParticleEngine.addEffect(getWorld(), effect);
	}
}