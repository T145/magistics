package T145.magistics.api.variants.blocks;

import T145.magistics.api.variants.IVariant;
import net.minecraft.util.math.MathHelper;

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

	public static EnumInfuser byMetadata(int meta) {
		return values()[MathHelper.clamp(meta, 0, meta)];
	}
}