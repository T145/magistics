package T145.magistics.api.variants.blocks;

import T145.magistics.api.variants.IVariant;

public enum EnumTank implements IVariant {

	NORMAL, REINFORCED;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}
}