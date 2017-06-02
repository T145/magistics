package T145.magistics.api.variants.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumInfuser implements IStringSerializable {

	LIGHT, DARK;

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}