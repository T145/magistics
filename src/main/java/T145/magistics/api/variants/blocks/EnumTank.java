package T145.magistics.api.variants.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumTank implements IStringSerializable {

	BASE, REINFORCED;

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}