package T145.magistics.api.variants;

import net.minecraft.util.math.MathHelper;

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

	public static EnumTank byMetadata(int meta) {
		return values()[MathHelper.clamp(meta, 0, meta)];
	}
}