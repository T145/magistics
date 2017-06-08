package T145.magistics.api.variants.blocks;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

public enum EnumWood implements IStringSerializable {

	GREATWOOD, SILVERWOOD;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	public static EnumWood byMetadata(int meta) {
		return values()[MathHelper.clamp(meta, 0, meta)];
	}
}