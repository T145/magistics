package T145.magistics.client.lib;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import thaumcraft.common.blocks.BlockCustomOreItem;
import cpw.mods.ironchest.IronChestType;

public class RenderHelper {
	public static ResourceLocation[] ironChestTextures = new ResourceLocation[IronChestType.values().length];

	public static String getSimpleIronChestName(IronChestType type) {
		return type.name().toLowerCase().replaceAll("[0-9]", "").replaceAll("chest", "");
	}

	static {
		for (IronChestType type : IronChestType.values())
			if (type != type.WOOD)
				ironChestTextures[type.ordinal()] = new ResourceLocation("magistics", "textures/models/chest_hungry/" + getSimpleIronChestName(type) + ".png");
	}

	public static int getColorCode(int meta) {
		int color = BlockCustomOreItem.colors[5];
		for (int a = 1; a < 6; a++)
			color = BlockCustomOreItem.colors[meta + 1];
		return color;
	}

	public static List<Integer> getColorCodes() {
		List<Integer> dest = new ArrayList<Integer>(), temp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++)
			temp.add(getColorCode(i));
		dest.addAll(temp);
		dest.addAll(temp);
		return dest;
	}

	public static LinkedList<Color> getColors() {
		LinkedList<Integer> codes = new LinkedList<Integer>();
		LinkedList<Color> colors = new LinkedList<Color>();

		codes.addAll(getColorCodes());
		codes.addAll(getColorCodes());

		for (int code : codes)
			colors.add(new Color(code));
		return colors;
	}

	public static int getIntFromColor(int red, int green, int blue) {
		red = (red << 16) & 0x00FF0000; // shift red 16-bits and mask out other stuff
		green = (green << 8) & 0x0000FF00; // shift green 8-bits and mask out other stuff
		blue = blue & 0x000000FF; // mask out anything not blue

		return 0xFF000000 | red | green | blue; // 0xFF000000 for 100% alpha bitwise "ored" w/ everything else
	}

	public static int getIntFromColor(float red, float green, float blue) {
		int r = Math.round(255 * red), g = Math.round(255 * green), b = Math.round(255 * blue);

		r = (r << 16) & 0x00FF0000;
		g = (g << 8) & 0x0000FF00;
		b = b & 0x000000FF;

		return 0xFF000000 | r | g | b;
	}
}