package T145.magistics.api.variants.blocks;

import T145.magistics.api.variants.IVariant;
import net.minecraft.util.math.MathHelper;

public enum EnumForge implements IVariant {

	NETHERRACK, NETHER_FORGE;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}

	public static EnumForge byMetadata(int meta) {
		return values()[MathHelper.clamp(meta, 0, meta)];
	}
}