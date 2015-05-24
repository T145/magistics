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

	private static class TileRenderer {
		private Class tile;
		private TileEntitySpecialRenderer renderer;

		public TileRenderer(Class t, TileEntitySpecialRenderer r) {
			tile = t;
			renderer = r;
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
		if (!modObjects.isEmpty()) {
			Iterator keychain = modObjects.keySet().iterator();

			while (keychain.hasNext()) {
				String key = (String) keychain.next();
				Object val = modObjects.get(key);

				if (val instanceof BlockNode) {
					BlockNode node = (BlockNode) val;
					Block block = node.block.setCreativeTab(tabMagistics);
					Class itemclass = node.item;

					if (itemclass == null)
						GameRegistry.registerBlock(block, key);
					else
						GameRegistry.registerBlock(block, itemclass, key);
				} else if (val instanceof Item) {
					Item item = (Item) val;

					GameRegistry.registerItem(item.setCreativeTab(tabMagistics), key);
				} else if (val instanceof Class) {
					Class tile = (Class) val;

					GameRegistry.registerTileEntity(tile, key);
				} else {
					Magistics.logger.error("An unknown object attempted to register: " + key + ":" + val.toString());
				}
			}
		}
	}

	public static void addBlockRenderer(Block block, ISimpleBlockRenderingHandler renderer) {
		modRenderers.put(block, renderer);
	}

	public static void addItemRenderer(Item item, IItemRenderer renderer) {
		modRenderers.put(item, renderer);
	}

	public static void addTileRenderer(Class tile, TileEntitySpecialRenderer renderer) {
		modRenderers.put(tile, renderer);
	}

	public static void registerRenderers() {
		if (!modRenderers.isEmpty()) {
			Iterator keychain = modRenderers.keySet().iterator();

			while (keychain.hasNext()) {
				Object key = keychain.next(), val = modRenderers.get(key);

				if (key instanceof Block) {
					ISimpleBlockRenderingHandler renderer = (ISimpleBlockRenderingHandler) val;

					RenderingRegistry.registerBlockHandler(renderer);
				} else if (key instanceof Item) {
					Item item = (Item) key;
					IItemRenderer renderer = (IItemRenderer) val;

					MinecraftForgeClient.registerItemRenderer(item, renderer);
				} else if (key instanceof Class) {
					Class tile = (Class) key;
					TileEntitySpecialRenderer renderer = (TileEntitySpecialRenderer) val;

					ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
				} else {
					Magistics.logger.error("An unknown renderer attempted to register: " + key.toString() + ":" + val.toString());
				}
			}
		}
	}
}