package T145.magistics.common.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModConfig;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRegistry {
	public static ModRegistry getRegistry() {
		return new ModRegistry();
	}

	private class BlockNode {
		private Block block;
		private Class item;

		public BlockNode(Block b) {
			block = b;
			item = null;
		}

		public BlockNode(Block b, Class c) {
			block = b;
			item = c;
		}
	}

	private CreativeTabs tabMagistics = new CreativeTabs(Magistics.MODID) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModConfig.blockChestHungryEnder);
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	}.setBackgroundImageName("magistics.png").setNoTitle();

	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<Class> tiles = new LinkedList<Class>();
	private LinkedList<BlockNode> blocks = new LinkedList<BlockNode>();

	public void addBlock(Block block) {
		blocks.add(new BlockNode(block.setCreativeTab(tabMagistics)));

		if (ModConfig.debug)
			Magistics.logger.info("Added Block: " + block.getUnlocalizedName());
	}

	public void addBlock(Block block, Class item) {
		blocks.add(new BlockNode(block.setCreativeTab(tabMagistics), item));

		if (ModConfig.debug)
			Magistics.logger.info("Added ItemBlock: " + block.getUnlocalizedName());
	}

	public void addItem(Item item) {
		items.add(item.setCreativeTab(tabMagistics));

		if (ModConfig.debug)
			Magistics.logger.info("Added Item: " + item.getUnlocalizedName());
	}

	public void addTile(Class tile) {
		tiles.add(tile);

		if (ModConfig.debug)
			Magistics.logger.info("Added Tile: " + tile.getSimpleName());
	}

	public void registerObjects() {
		if (!blocks.isEmpty()) {
			for (BlockNode node : blocks) {
				Block block = node.block;
				Class item = node.item;

				if (item == null) {
					GameRegistry.registerBlock(block, block.getUnlocalizedName());

					if (ModConfig.debug)
						Magistics.logger.info("Registered Block: " + block.getUnlocalizedName());
				} else {
					GameRegistry.registerBlock(block, item, block.getUnlocalizedName());

					if (ModConfig.debug)
						Magistics.logger.info("Registered ItemBlock: " + block.getUnlocalizedName());
				}
			}
		} else {
			if (ModConfig.debug)
				Magistics.logger.warn("No blocks to register!");
		}

		if (!items.isEmpty()) {
			for (Item item : items) {
				GameRegistry.registerItem(item, item.getUnlocalizedName());

				if (ModConfig.debug)
					Magistics.logger.info("Registered Item: " + item.getUnlocalizedName());
			}
		} else {
			if (ModConfig.debug)
				Magistics.logger.warn("No items to register!");
		}

		if (!tiles.isEmpty()) {
			for (Class tile : tiles) {
				GameRegistry.registerTileEntity(tile, tile.getSimpleName());

				if (ModConfig.debug)
					Magistics.logger.info("Registered Tile: " + tile.getSimpleName());
			}
		} else {
			if (ModConfig.debug)
				Magistics.logger.warn("No tiles to register!");
		}
	}

	private Map<Item, IItemRenderer> itemRenderers = new HashMap<Item, IItemRenderer>();
	private Map<Class, TileEntitySpecialRenderer> tileRenderers = new HashMap<Class, TileEntitySpecialRenderer>();
	private List<ISimpleBlockRenderingHandler> blockRenderers = new ArrayList<ISimpleBlockRenderingHandler>();

	public void addBlockRenderer(ISimpleBlockRenderingHandler renderer) {
		blockRenderers.add(renderer);

		if (ModConfig.debug)
			Magistics.logger.info("Added Block Renderer: " + renderer.toString());
	}

	public void addItemRenderer(Item item, IItemRenderer renderer) {
		itemRenderers.put(item, renderer);

		if (ModConfig.debug)
			Magistics.logger.info("Added Item Renderer: " + renderer.toString());
	}

	public void addTileRenderer(Class tile, TileEntitySpecialRenderer renderer) {
		tileRenderers.put(tile, renderer);

		if (ModConfig.debug)
			Magistics.logger.info("Added Tile Renderer: " + renderer.toString());
	}

	public void registerRenderers() {
		if (!blockRenderers.isEmpty()) {
			for (ISimpleBlockRenderingHandler renderer : blockRenderers) {
				RenderingRegistry.registerBlockHandler(renderer);

				if (ModConfig.debug)
					Magistics.logger.info("Registered Block Renderer: " + renderer.toString());
			}
		} else {
			if (ModConfig.debug)
				Magistics.logger.warn("No block renderers to register!");
		}

		if (!itemRenderers.isEmpty()) {
			for (Item item : itemRenderers.keySet()) {
				MinecraftForgeClient.registerItemRenderer(item, itemRenderers.get(item));

				//if (ModConfig.debug)
					//Magistics.logger.info("Registered Item Renderer: " + itemRenderers.get(item) + " @ " + item.getUnlocalizedName());
			}
		} else {
			if (ModConfig.debug)
				Magistics.logger.warn("No item renderers to register!");
		}

		if (!tileRenderers.isEmpty()) {
			for (Class tile : tileRenderers.keySet()) {
				ClientRegistry.bindTileEntitySpecialRenderer(tile, tileRenderers.get(tile));

				if (ModConfig.debug)
					Magistics.logger.info("Registered Tile Renderer: " + tile.getSimpleName() + " @ " + tileRenderers.get(tile));
			}
		} else {
			if (ModConfig.debug)
				Magistics.logger.warn("No tile renderers to register!");
		}
	}
}