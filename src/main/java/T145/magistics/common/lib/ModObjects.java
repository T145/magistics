package T145.magistics.common.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModConfig;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModObjects {
	private static boolean debug = ModConfig.debug;

	public static ModObjects getRegistry() {
		return new ModObjects();
	}

	private static class BlockNode {
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

	private static CreativeTabs tabMagistics = new CreativeTabs(Magistics.MODID) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.anvil);
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	}.setBackgroundImageName("magistics.png").setNoTitle();

	private static LinkedList<Item> items = new LinkedList<Item>();
	private static LinkedList<BlockNode> blocks = new LinkedList<BlockNode>();
	private static LinkedList<Class> tiles = new LinkedList<Class>();

	public static void addBlock(Block block) {
		blocks.add(new BlockNode(block));

		if (debug)
			Magistics.logger.info("Added Block: " + block.getUnlocalizedName());
	}

	public static void addBlock(Block block, Class item) {
		blocks.add(new BlockNode(block, item));

		if (debug)
			Magistics.logger.info("Added ItemBlock: " + block.getUnlocalizedName());
	}

	public static void addItem(Item item) {
		items.add(item);

		if (debug)
			Magistics.logger.info("Added Item: " + item.getUnlocalizedName());
	}

	public static void addTile(Class tile) {
		tiles.add(tile);

		if (debug)
			Magistics.logger.info("Added Tile: " + tile.getSimpleName());
	}

	public static void registerObjects() {
		if (!blocks.isEmpty()) {
			for (BlockNode node : blocks) {
				Block block = node.block.setCreativeTab(tabMagistics);
				Class item = node.item;

				if (item == null) {
					GameRegistry.registerBlock(block, block.getUnlocalizedName());

					if (debug)
						Magistics.logger.info("Registered Block: " + block.getUnlocalizedName());
				} else {
					GameRegistry.registerBlock(block, item, block.getUnlocalizedName());

					if (debug)
						Magistics.logger.info("Registered ItemBlock: " + block.getUnlocalizedName());
				}
			}
		} else {
			if (debug)
				Magistics.logger.warn("No blocks to register!");
		}

		if (!items.isEmpty()) {
			for (Item item : items) {
				GameRegistry.registerItem(item, item.getUnlocalizedName());

				if (debug)
					Magistics.logger.info("Registered Item: " + item.getUnlocalizedName());
			}
		} else {
			if (debug)
				Magistics.logger.warn("No items to register!");
		}

		if (!tiles.isEmpty()) {
			for (Class tile : tiles) {
				GameRegistry.registerTileEntity(tile, tile.getSimpleName());

				if (debug)
					Magistics.logger.info("Registered Tile: " + tile.getSimpleName());
			}
		} else {
			if (debug)
				Magistics.logger.warn("No tiles to register!");
		}
	}

	private static Map<Item, IItemRenderer> itemRenderers = new HashMap<Item, IItemRenderer>();
	private static List<ISimpleBlockRenderingHandler> blockRenderers = new ArrayList<ISimpleBlockRenderingHandler>();
	private static Map<Class, TileEntitySpecialRenderer> tileRenderers = new HashMap<Class, TileEntitySpecialRenderer>();

	public static void addBlockRenderer(ISimpleBlockRenderingHandler renderer) {
		blockRenderers.add(renderer);

		if (debug)
			Magistics.logger.info("Added Block Renderer: " + renderer.toString());
	}

	public static void addItemRenderer(Item item, IItemRenderer renderer) {
		itemRenderers.put(item, renderer);

		if (debug)
			Magistics.logger.info("Added Item Renderer: " + renderer.toString());
	}

	public static void addTileRenderer(Class tile, TileEntitySpecialRenderer renderer) {
		tileRenderers.put(tile, renderer);

		if (debug)
			Magistics.logger.info("Added Tile Renderer: " + renderer.toString());
	}

	public static void registerRenderers() {
		if (!blockRenderers.isEmpty()) {
			for (ISimpleBlockRenderingHandler renderer : blockRenderers) {
				RenderingRegistry.registerBlockHandler(renderer);

				if (debug)
					Magistics.logger.info("Registered Block Renderer: " + renderer.toString());
			}
		} else {
			if (debug)
				Magistics.logger.warn("No block renderers to register!");
		}

		if (!itemRenderers.isEmpty()) {
			for (Item item : itemRenderers.keySet()) {
				MinecraftForgeClient.registerItemRenderer(item, itemRenderers.get(item));

				if (debug)
					Magistics.logger.info("Registered Item Renderer: " + item.getUnlocalizedName() + " @ " + itemRenderers.get(item));
			}
		} else {
			if (debug)
				Magistics.logger.warn("No item renderers to register!");
		}

		if (!tileRenderers.isEmpty()) {
			for (Class tile : tileRenderers.keySet()) {
				ClientRegistry.bindTileEntitySpecialRenderer(tile, tileRenderers.get(tile));

				if (debug)
					Magistics.logger.info("Registered Tile Renderer: " + tile.getSimpleName() + " @ " + tileRenderers.get(tile));
			}
		} else {
			if (debug)
				Magistics.logger.warn("No tile renderers to register!");
		}
	}
}