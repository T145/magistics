package T145.magistics.api.variants.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumCrucible implements IStringSerializable {

	BASIC(500F, 0.5F, 0.25F),
	EYES(600F, 0.6F, 0.5F),
	THAUMIUM(750F, 0.7F, 0.75F),
	SOULS(750F, 0.4F, 0.75F);

	private final float maxQuints;
	private final float conversion;
	private final float speed;

	EnumCrucible(float maxQuints, float conversion, float speed) {
		this.maxQuints = maxQuints;
		this.conversion = conversion;
		this.speed = speed;
	}

	public float getMaxQuints() {
		return maxQuints;
	}

	public float getConversion() {
		return conversion;
	}

	public float getSpeed() {
		return speed;
	}

	public boolean canProvidePower() {
		return this == EYES || this == THAUMIUM;
	}

	public boolean canDrainMobs() {
		return this == SOULS;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}