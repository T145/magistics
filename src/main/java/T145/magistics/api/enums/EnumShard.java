package T145.magistics.api.enums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

public enum EnumShard implements IStringSerializable {

	AIR, FIRE, WATER, EARTH, LIGHT, DARK;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	public String getClientName() {
		return "variant=" + getName();
	}

	public static EnumShard byMetadata(int meta) {
		return values()[MathHelper.clamp_int(meta, 0, meta)];
	}
}