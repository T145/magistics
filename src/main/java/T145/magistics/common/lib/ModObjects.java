package T145.magistics.common.lib;

import java.util.Iterator;
import java.util.LinkedHashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.apache.logging.log4j.Level;

import T145.magistics.common.Magistics;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModObjects {
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

	//private static Dictionary modObjects = new Hashtable(), modRenderers = new Hashtable();
	private static LinkedHashMap<String, Object> modObjects = new LinkedHashMap<String, Object>();
	private static LinkedHashMap<Object, Object> modRenderers = new LinkedHashMap<Object, Object>();

	public static void addBlock(Block block) {
		modObjects.put(block.getUnlocalizedName(), new BlockNode(block));
	}

	public static void addBlock(Block block, Class item) {
		modObjects.put(block.getUnlocalizedName(), new BlockNode(block, item));
	}

	public static void addItem(Item item) {
		modObjects.put(item.getUnlocalizedName(), item);
	}

	public static void addTile(Class tile) {
		modObjects.put(tile.getSimpleName(), tile);
	}

	public static void registerObjects() {
		// be sure we have objects to register
		if (!modObjects.isEmpty()) {
			Iterator keys = modObjects.keySet().iterator();

			// go through each key
			while (keys.hasNext()) {
				Object key = keys.next();
				String name = (String) key;

				// determine the key's value
				Object value = modObjects.get(name);

				// register said value
				if (value instanceof BlockNode) {
					BlockNode node = (BlockNode) value;

					if (node.item == null) {
						GameRegistry.registerBlock(node.block.setCreativeTab(tabMagistics), name);
					} else {
						GameRegistry.registerBlock(node.block.setCreativeTab(tabMagistics), node.item, name);
					}
				} else if (value instanceof Item) {
					Item item = (Item) value;

					GameRegistry.registerItem(item.setCreativeTab(tabMagistics), name);
				} else if (value instanceof Class) {
					Class tile = (Class) value;

					GameRegistry.registerTileEntity(tile, name);
				} else {
					// unknown object
					Magistics.logger.log(Level.WARN, "An unknown object attempted to register: " + key.toString() + " ... " + value.toString());
				}
			}
		} else {
			// nothing to register
			Magistics.logger.log(Level.INFO, "Object Registry: Nothing to register!");
		}
	}

	public static void addBlockRenderer(Block block, ISimpleBlockRenderingHandler blockRenderer) {
		modRenderers.put(block, blockRenderer);
	}

	public static void addItemRenderer(Item item, IItemRenderer itemRenderer) {
		modRenderers.put(item, itemRenderer);
	}

	public static void addTileRenderer(Class tile, TileEntitySpecialRenderer tileRenderer) {
		modRenderers.put(tile, tileRenderer);
	}

	public static void registerRenderers() {
		if (!modRenderers.isEmpty()) {
			Iterator keys = modObjects.keySet().iterator();

			// go through each key
			while (keys.hasNext()) {
				// determine the key's value
				Object key = keys.next(), value = modRenderers.get(key);

				// register said value
				if (key instanceof Block) {
					ISimpleBlockRenderingHandler renderer = (ISimpleBlockRenderingHandler) value;

					RenderingRegistry.registerBlockHandler(renderer);
				} else if (key instanceof Item) {
					Item item = (Item) key;
					IItemRenderer renderer = (IItemRenderer) value;

					MinecraftForgeClient.registerItemRenderer(item, renderer);
				} else if (key instanceof Class) {
					Class tile = (Class) key;
					TileEntitySpecialRenderer renderer = (TileEntitySpecialRenderer) value;

					ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
				} else {
					// unknown renderer
					Magistics.logger.log(Level.WARN, "An unknown renderer attempted to register: " + key.toString() + " ... " + value.toString());
				}
			}
		} else {
			// nothing to register
			Magistics.logger.log(Level.INFO, "Renderer Registry: Nothing to register!");
		}
	}
}