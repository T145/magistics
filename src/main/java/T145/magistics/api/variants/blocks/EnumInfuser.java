package T145.magistics.api.variants.blocks;

import T145.magistics.api.variants.IVariant;

public enum EnumInfuser implements IVariant {

	LIGHT, DARK;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}
}