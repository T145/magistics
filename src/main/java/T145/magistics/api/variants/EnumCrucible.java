package T145.magistics.api.variants;

import net.minecraft.util.math.MathHelper;

public enum EnumCrucible implements IVariant {

	BASIC, EYES, THAUMIUM, SOULS;

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