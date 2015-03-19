package T145.magistics.client.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ConfigRenderers {
	private static ConfigRenderers instance = new ConfigRenderers();

	public static ConfigRenderers getInstance() {
		return instance;
	}

	private Map<Item, IItemRenderer> itemRenderers = new HashMap<Item, IItemRenderer>();
	private Map<Class, TileEntitySpecialRenderer> tileRenderers = new HashMap<Class, TileEntitySpecialRenderer>();
	private List<ISimpleBlockRenderingHandler> blockRenderers = new ArrayList<ISimpleBlockRenderingHandler>();

	public void addItemRenderer(Item item, IItemRenderer renderer) {
		itemRenderers.put(item, renderer);
	}

	public void addTileRenderer(Class tile, TileEntitySpecialRenderer renderer) {
		tileRenderers.put(tile, renderer);
	}

	public void addBlockRenderer(ISimpleBlockRenderingHandler renderer) {
		blockRenderers.add(renderer);
	}

	public void register() {
		for (Item item : itemRenderers.keySet())
			MinecraftForgeClient.registerItemRenderer(item, itemRenderers.get(item));
		for (Class tile : ConfigObjects.getInstance().getTiles())
			if (tileRenderers.get(tile) != null)
				ClientRegistry.bindTileEntitySpecialRenderer(tile, tileRenderers.get(tile));
		for (ISimpleBlockRenderingHandler blockRenderer : blockRenderers)
			RenderingRegistry.registerBlockHandler(blockRenderer);
	}
}