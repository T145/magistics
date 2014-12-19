package T145.magistics.client;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.client.renderers.block.BlockAestheticRenderer;
import T145.magistics.client.renderers.block.BlockAestheticStructureRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryEnderRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryMetalItemRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.config.Settings;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Settings.blockChestHungryMetal), new BlockChestHungryMetalItemRenderer());
		RenderingRegistry.registerBlockHandler(new BlockChestHungryEnderRenderer());
		RenderingRegistry.registerBlockHandler(new BlockAestheticRenderer());
		RenderingRegistry.registerBlockHandler(new BlockAestheticStructureRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
	}
}