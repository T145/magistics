package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import T145.magistics.common.Magistics;
import cpw.mods.fml.common.registry.GameRegistry;

public class ConfigObjects {
	private class BlockNode {
		private Block b;
		private Class i;

		public BlockNode(Block block) {
			b = block;
		}

		public BlockNode(Block block, Class item) {
			b = block;
			i = item;
		}
	}

	private static ConfigObjects instance = new ConfigObjects();

	public static ConfigObjects getInstance() {
		return instance;
	}

	private CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Magistics.proxy.blockChestHungryEnder);
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	}.setBackgroundImageName("magistics.png").setNoTitle();

	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<Class> tiles = new LinkedList<Class>();
	private LinkedList<BlockNode> blocks = new LinkedList<BlockNode>();

	public void addItem(Item item) {
		items.add(item);
	}

	public void addTile(Class tile) {
		tiles.add(tile);
	}

	public void addBlock(Block block) {
		blocks.add(new BlockNode(block));
	}

	public void addBlock(Block block, Class item) {
		blocks.add(new BlockNode(block, item));
	}

	public List<Item> getItems() {
		return items;
	}

	public List<Class> getTiles() {
		return tiles;
	}

	public List<Block> getBlocks() {
		List<Block> dest = new ArrayList<Block>();
		Iterator<BlockNode> iterator = blocks.iterator();

		while (iterator.hasNext())
			dest.add(iterator.next().b);

		return dest;
	}

	public Map<Block, Class> getBlocksWithItems() {
		Map<Block, Class> dest = new HashMap<Block, Class>();
		Iterator<BlockNode> iterator = blocks.iterator();

		while (iterator.hasNext()) {
			BlockNode node = iterator.next();
			dest.put(node.b, node.i);
		}

		return dest;
	}

	public void register() {
		for (Item item : items)
			GameRegistry.registerItem(item.setCreativeTab(tabMagistics), item.getUnlocalizedName());
		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, "magistics:" + tile.getSimpleName());
		for (BlockNode node : blocks) {
			Block block = node.b;
			Class item = node.i;

			if (item == null)
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), block.getUnlocalizedName());
			else
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), item, block.getUnlocalizedName());
		}
	}
}