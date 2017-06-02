package T145.magistics.api.variants.blocks;

import net.minecraft.util.IStringSerializable;

public enum EnumForge implements IStringSerializable {

	NETHERRACK, NETHER_FORGE;

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}