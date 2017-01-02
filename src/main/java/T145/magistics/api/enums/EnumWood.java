package T145.magistics.api.enums;

import T145.magistics.api.objects.IVariant;
import net.minecraft.util.math.MathHelper;

public enum EnumWood implements IVariant {

	GREATWOOD, SILVERWOOD;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}

	public static EnumWood byMetadata(int meta) {
		return values()[MathHelper.clamp_int(meta, 0, meta)];
	}
}