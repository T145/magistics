package T145.magistics.api.variants.blocks;

import T145.magistics.api.variants.IVariant;

public enum EnumConduit implements IVariant {

	BASE;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}
}