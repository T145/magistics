package T145.magistics.api.variants;

import net.minecraft.util.math.MathHelper;

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

	public static EnumConduit byMetadata(int meta) {
		return values()[MathHelper.clamp(meta, 0, meta)];
	}
}