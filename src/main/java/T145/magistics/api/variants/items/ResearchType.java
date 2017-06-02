package T145.magistics.api.variants.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.IStringSerializable;

public enum ResearchType implements IStringSerializable {

	LOST, FORBIDDEN, BLIGHTED, ELDRITCH;

	public static List<String> getTypes() {
		List<String> types = new ArrayList<String>();

		types.add("lost");
		types.add("forbidden");
		types.add("blighted");
		types.add("eldritch");

		return types;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}