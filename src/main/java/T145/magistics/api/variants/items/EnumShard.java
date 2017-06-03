package T145.magistics.api.variants.items;

import net.minecraft.util.IStringSerializable;

public enum EnumShard implements IStringSerializable {

	DULL, AIR, FIRE, WATER, EARTH, MAGIC, VOID;

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}