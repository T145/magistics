package T145.magistics.common.config;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import T145.magistics.common.Magistics;

public class ConfigObjects {
	public class BlockNode {
		private Block b;
		private ItemBlock i;

		public BlockNode(Block block) {
			b = block;
		}

		public BlockNode(Block block, ItemBlock item) {
			b = block;
			i = item;
		}
	}

	/*private static Map<Object, List> objMatrix;

	public ConfigObjects() {
		List<BlockNode> blocks = new ArrayList<BlockNode>();
		List<Item> items = new ArrayList<Item>();
		List<Class> tiles = new ArrayList<Class>();
		objMatrix = new HashMap<Object, List>();

		objMatrix.put(Block.class, blocks);
		objMatrix.put(Item.class, items);
		objMatrix.put(Class.class, tiles);
	}

	public static void add(Object input) {
		for (Object key : objMatrix.keySet()) {
			if (key.getClass().isAssignableFrom(input.getClass())) {
				List dest = objMatrix.get(key);
				dest.add(input);
				objMatrix.put(key.getClass(), dest);
			} else
				Magistics.logger.info("Couldn't add in the object " + input + "!");
		}
	}*/

	public static CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.anvil);
		}
	};

	/*public static void register() {
		for (Object key : objMatrix.keySet()) {
			if (key instanceof Block) {
				List<BlockNode> values = objMatrix.get(key);
				for (BlockNode block : values)
					if (block.i == null)
						GameRegistry.registerBlock(block.b.setCreativeTab(tabMagistics), block.b.getUnlocalizedName());
					else
						GameRegistry.registerBlock(block.b.setCreativeTab(tabMagistics), block.i.getClass(), block.b.getUnlocalizedName());
			} else if (key instanceof Item) {
				List<Item> values = objMatrix.get(key);
				for (Item item : values)
					GameRegistry.registerItem(item.setCreativeTab(tabMagistics), item.getUnlocalizedName());
			} else {
				List<Class> values = objMatrix.get(key);
				for (Class tile : values)
					GameRegistry.registerTileEntity(tile, tile.getSimpleName());
			}
		}
	}*/
	
	
}