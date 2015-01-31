package T145.magistics.client;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.config.ModBlocks;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void postInit() {
		ModBlocks.loadClient();
		for (Class tile : ModBlocks.tiles)
			if (ModBlocks.tileRenderers.get(tile) != null)
				ClientRegistry.bindTileEntitySpecialRenderer(tile, ModBlocks.tileRenderers.get(tile));
		for (ISimpleBlockRenderingHandler blockRenderer : ModBlocks.blockRenderers)
			RenderingRegistry.registerBlockHandler(blockRenderer);
		for (Item block : ModBlocks.itemRenderers.keySet())
			MinecraftForgeClient.registerItemRenderer(block, ModBlocks.itemRenderers.get(block));
	}
}