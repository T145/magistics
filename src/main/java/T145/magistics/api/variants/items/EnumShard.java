package T145.magistics.api.variants.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.IStringSerializable;

public enum EnumShard implements IStringSerializable {

	DULL, AIR, FIRE, WATER, EARTH, MAGIC, VOID;

	public static List<String> getTypes() {
		List<String> types = new ArrayList<String>();

		types.add("dull");
		types.add("air");
		types.add("fire");
		types.add("water");
		types.add("earth");
		types.add("magic");
		types.add("void");

		return types;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}