package T145.magistics.client.lib;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import thaumcraft.common.blocks.BlockCustomOreItem;
import cpw.mods.ironchest.IronChestType;

public class RenderHelper {
	public static String getSimpleIronChestName(IronChestType type) {
		return type.name().toLowerCase().replaceAll("[0-9]", "").replaceAll("chest", "");
	}

	public static LinkedList<String> getMetalChestNames() {
		LinkedList<String> names = new LinkedList<String>();

		for (IronChestType type : IronChestType.values())
			if (type != type.WOOD)
				names.add(getSimpleIronChestName(type));

		return names;
	}

	public static ResourceLocation[] getMetalChestTextures() {
		ResourceLocation[] metalChestTextures = new ResourceLocation[IronChestType.values().length];

		for (IronChestType type : IronChestType.values())
			if (type != type.WOOD)
				metalChestTextures[type.ordinal()] = new ResourceLocation("magistics", "textures/models/chest_hungry/" + getSimpleIronChestName(type) + ".png");

		return metalChestTextures;
	}

	public static int getCrystalColorCode(int meta) {
		int color = BlockCustomOreItem.colors[5];
		for (int a = 1; a < 6; a++)
			color = BlockCustomOreItem.colors[meta + 1];
		return color;
	}

	public static List<Integer> getCrystalColorCodes() {
		List<Integer> dest = new ArrayList<Integer>(), temp = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++)
			temp.add(getCrystalColorCode(i));
		dest.addAll(temp);
		dest.addAll(temp);
		return dest;
	}

	public static LinkedList<Color> getCrystalColors() {
		LinkedList<Integer> codes = new LinkedList<Integer>();
		LinkedList<Color> colors = new LinkedList<Color>();

		codes.addAll(getCrystalColorCodes());
		codes.addAll(getCrystalColorCodes());

		for (int code : codes)
			colors.add(new Color(code));
		return colors;
	}
}