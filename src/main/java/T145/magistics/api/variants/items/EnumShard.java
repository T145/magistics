package T145.magistics.api.variants.items;

import T145.magistics.api.variants.IVariant;

public enum EnumShard implements IVariant {

	AIR, FIRE, WATER, EARTH, LIGHT, DARK;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}
}