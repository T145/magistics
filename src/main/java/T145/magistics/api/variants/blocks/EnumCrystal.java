package T145.magistics.api.variants.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumCrystal implements IStringSerializable {

	AIR, FIRE, WATER, EARTH, MAGIC, VOID;

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}