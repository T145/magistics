package T145.magistics.client;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.client.renderers.block.BlockAestheticRenderer;
import T145.magistics.client.renderers.block.BlockAestheticStructureRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryEnderRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryIronItemRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryIronRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.config.Settings;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryIron;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerBlockHandler(new BlockChestHungryEnderRenderer());
		RenderingRegistry.registerBlockHandler(new BlockAestheticRenderer());
		RenderingRegistry.registerBlockHandler(new BlockAestheticStructureRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Settings.blockChestHungryIron), new BlockChestHungryIronItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryIron.class, new TileChestHungryIronRenderer());
	}
}