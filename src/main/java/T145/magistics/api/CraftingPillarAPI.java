package T145.magistics.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public class CraftingPillarAPI {
	private static Map<Item, String> diskTextures = new HashMap<Item, String>();

	public static void addDiskTexture(Item item, String url) {
		diskTextures.put(item, url);
	}

	public static String getDiskTexture(Item item) {
		return (String) (diskTextures.get(item) != null ? diskTextures.get(item) : "craftingpillars:textures/models/disk_unknown.png");
	}
}