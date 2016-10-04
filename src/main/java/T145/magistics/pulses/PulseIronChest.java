package T145.magistics.pulses;

import T145.magistics.api.pulses.CorePulse;
import T145.magistics.blocks.BlockChestHungryMetal;
import T145.magistics.blocks.BlockChestHungryMetalItem;
import T145.magistics.client.render.blocks.RenderBlockChest;
import T145.magistics.client.render.tiles.RenderChestHungryMetal;
import T145.magistics.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class PulseIronChest extends CorePulse {
	@Override
	public void registerRenderInformation() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, RenderChestHungryMetal.INSTANCE);
		RenderingRegistry.registerBlockHandler(new RenderBlockChest(BlockChestHungryMetal.INSTANCE.getRenderType(), RenderChestHungryMetal.getChestTextures()));
	}

	@Override
	public String getModId() {
		return "IronChest";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(TileChestHungryMetal.class, TileChestHungryMetal.class.getSimpleName());
		GameRegistry.registerBlock(BlockChestHungryMetal.INSTANCE, BlockChestHungryMetalItem.class, BlockChestHungryMetal.INSTANCE.getUnlocalizedName());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {}
}