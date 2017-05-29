package T145.magistics.network;

import T145.magistics.client.fx.FXEngine;
import T145.magistics.client.gui.GuiChestHungry;
import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.lib.ClientBakery;
import T145.magistics.init.ModBlocks;
import T145.magistics.init.ModEntities;
import T145.magistics.init.ModItems;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case 0:
			return new GuiInfuser(player.inventory, ((TileInfuser) tile));
		case 1:
			return new GuiChestHungry(player.inventory, ((TileChestHungry) tile));
		default:
			return null;
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(ClientBakery.INSTANCE);
		MinecraftForge.EVENT_BUS.register(FXEngine.INSTANCE);

		ModBlocks.initClient();
		ModItems.initClient();
		ModEntities.initClient();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}