package T145.magistics.api.enums;

import T145.magistics.api.objects.IVariant;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

public enum EnumShard implements IStringSerializable, IVariant {

	AIR, FIRE, WATER, EARTH, LIGHT, DARK;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public String getClientName() {
		return "variant=" + getName();
	}

	public static EnumShard byMetadata(int meta) {
		return values()[MathHelper.clamp_int(meta, 0, meta)];
	}
}