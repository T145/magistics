package T145.magistics.client.lib;

import net.minecraft.util.ResourceLocation;
import cpw.mods.ironchest.IronChestType;

public class TextureHelper {
	public static ResourceLocation[] ironChestTextures = new ResourceLocation[IronChestType.values().length];

	public static String getSimpleIronChestName(IronChestType type) {
		return type.name().toLowerCase().replaceAll("[0-9]", "").replaceAll("chest", "");
	}

	static {
		for (IronChestType type : IronChestType.values())
			if (type != type.WOOD)
				ironChestTextures[type.ordinal()] = new ResourceLocation("magistics", "textures/models/chest_hungry/" + getSimpleIronChestName(type) + ".png");
	}
}