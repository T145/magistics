package T145.magistics.api.variants.items;

import net.minecraft.util.IStringSerializable;

public enum ResearchType implements IStringSerializable {

	LOST, FORBIDDEN, BLIGHTED, ELDRITCH;

	public static String[] getTypes() {
		return new String[] { "lost", "forbidden", "blighted", "eldritch" };
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}