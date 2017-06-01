package T145.magistics.proxies;

import T145.magistics.addons.ModAddons;
import T145.magistics.init.ModBlocks;
import T145.magistics.init.ModEntities;
import T145.magistics.init.ModItems;
import T145.magistics.init.ModRecipes;
import T145.magistics.init.ModSounds;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case 0:
			return ((TileInfuser) tile).createContainer(player.inventory, player);
		case 1:
			return ((TileChestHungry) tile).createContainer(player.inventory, player);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
		ModAddons.preInit(event);
	}

	public void init(FMLInitializationEvent event) {
		ModSounds.registerSounds();
		ModAddons.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModRecipes.registerRecipes();
		ModAddons.postInit(event);
	}
}