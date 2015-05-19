package T145.magistics.common.config;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import org.apache.logging.log4j.Level;

import T145.magistics.common.Magistics;
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
	
	private static Dictionary modObjects = new Hashtable();
	
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
			Enumeration keys = modObjects.keys();
			
			// go through each key
			while (keys.hasMoreElements()) {
				String name = (String) keys.nextElement();
				
				// determine the key's value
				Object value = modObjects.get(name);
				
				// register said value
				if (value instanceof BlockNode) {
					BlockNode node = (BlockNode) value;
					
					if (node.item == null) {
						GameRegistry.registerBlock(node.block, name);
					} else {
						GameRegistry.registerBlock(node.block, node.item, name);
					}
				} else if (value instanceof Item) {
					Item item = (Item) value;
					
					GameRegistry.registerItem(item, name);
				} else if (value instanceof Class) {
					Class tile = (Class) value;
					
					GameRegistry.registerTileEntity(tile, name);
				} else {
					// unknown object
				}
			}
		} else {
			// nothing to register
			Magistics.logger.log(Level.INFO, "Nothing to register!");
		}
	}
}