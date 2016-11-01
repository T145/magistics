package T145.magistics.network;

import T145.magistics.client.fx.ParticleEngine;
import T145.magistics.client.fx.particles.ParticleGreenFlame;
import T145.magistics.client.fx.particles.ParticleSmallGreenFlame;
import T145.magistics.client.fx.particles.ParticleWisp;
import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.lib.ColorHandler;
import T145.magistics.client.lib.events.IconAtlas;
import T145.magistics.lib.sounds.SoundHandler;
import T145.magistics.load.ModBlocks;
import T145.magistics.load.ModItems;
import T145.magistics.tiles.TileInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		switch (ID) {
		case 0:
			return new GuiInfuser(player.inventory, (TileInfuser) world.getTileEntity(pos));
		default:
			return null;
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		SoundHandler.registerSounds();

		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(ParticleEngine.INSTANCE);
		MinecraftForge.EVENT_BUS.register(IconAtlas.INSTANCE);

		ModBlocks.initClient();
		ModItems.initClient();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		ColorHandler.registerColorHandlers();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void greenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleGreenFlame(world, x, y, z, 0F, 0F, 0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}

	@Override
	public void smallGreenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleSmallGreenFlame(world, x, y, z, 0F, 0F, 0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}

	@Override
	public void wispFX(World world, double x, double y, double z, float f, float g, float h, float i) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, f, g, h, i);
		fx.setGravity(0.02F);

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}

	@Override
	public void wispFX2(World world, double x, double y, double z, float size, int type, boolean shrink, boolean clip, float gravity) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, size, type);
		fx.setGravity(gravity);
		fx.shrink = shrink;
		fx.setNoClip(clip);

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}

	@Override
	public void wispFX3(World world, double x, double y, double z, double x2, double y2, double z2, float size, int type, boolean shrink, float gravity) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, x2, y2, z2, size, type);

		fx.setGravity(gravity);
		fx.shrink = shrink;

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}

	@Override
	public void wispFX4(World world, double x, double y, double z, Entity target, int type, boolean shrink, float gravity) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, target, type);
		fx.setGravity(gravity);
		fx.shrink = shrink;

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}
}