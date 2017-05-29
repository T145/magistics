package T145.magistics.api.variants.blocks;

import T145.magistics.api.variants.IVariant;

public enum EnumChestHungry implements IVariant {

	BASIC, TRAPPED, ENDER;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}
}