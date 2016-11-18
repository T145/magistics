package T145.magistics.network;

import T145.magistics.lib.events.WorldEventHandler;
import T145.magistics.lib.world.biomes.BiomeHandler;
import T145.magistics.load.ModBlocks;
import T145.magistics.load.ModEntities;
import T145.magistics.load.ModItems;
import T145.magistics.tiles.TileInfuser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		switch (ID) {
		case 0:
			TileInfuser infuser = (TileInfuser) world.getTileEntity(pos);
			return infuser.createContainer(player.inventory, player);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public void preInit(FMLPreInitializationEvent event) {
		BiomeHandler.registerBiomes();
		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());

		ModBlocks.preInit(event);
		ModItems.preInit(event);
		ModEntities.preInit(event);
	}

	public void init(FMLInitializationEvent event) {
		ModBlocks.init(event);
		ModItems.init(event);
		ModEntities.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModBlocks.postInit(event);
		ModItems.postInit(event);
		ModEntities.postInit(event);
	}

	public World getClientWorld() {
		return null;
	}

	public void greenFlameFX(World world, float x, float y, float z) {}

	public void smallGreenFlameFX(World world, float x, float y, float z) {}

	public void customWispFX(World world, double x, double y, double z, double destX, double destY, double destZ, float gravity, int type) {}

	public void wispFX(World world, double x, double y, double z, float f, float g, float h, float gravity) {}

	public void wispFX2(World world, double x, double y, double z, float size, int type, boolean shrink, boolean clip, float gravity) {}

	public void wispFX3(World world, double x, double y, double z, double destX, double destY, double destZ, float size, int type, boolean shrink, float gravity) {}

	public void wispFX4(World world, double x, double y, double z, Entity target, int type, boolean shrink, float gravity) {}
}