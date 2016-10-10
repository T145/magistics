package T145.magistics.network;

import T145.magistics.api.IFMLEventHandler;
import T145.magistics.load.ModBlocks;
import T145.magistics.load.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IFMLEventHandler, IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.preInit();
		ModItems.preInit();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		ModBlocks.postInit();
		ModItems.postInit();
	}
}