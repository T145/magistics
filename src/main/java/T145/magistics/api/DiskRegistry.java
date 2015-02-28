package T145.magistics.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public class DiskRegistry {
	private static Map<Item, String> diskTextures = new HashMap<Item, String>();

	public static void addDiskTexture(Item item, String url) {
		diskTextures.put(item, url);
	}

	public static String getDiskTexture(Item item) {
		return diskTextures.get(item) != null ? diskTextures.get(item) : "magistics:textures/models/pillars/disk_unknown.png";
	}
}