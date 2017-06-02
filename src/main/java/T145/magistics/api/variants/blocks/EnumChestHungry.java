package T145.magistics.api.variants.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumChestHungry implements IStringSerializable {

	BASIC, TRAPPED, ENDER;

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}