package T145.magistics.api.variants.blocks;

import T145.magistics.api.variants.IVariant;
import net.minecraft.util.math.MathHelper;

public enum EnumCrucible implements IVariant {

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

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}

	public static EnumCrucible byMetadata(int meta) {
		return values()[MathHelper.clamp(meta, 0, meta)];
	}
}