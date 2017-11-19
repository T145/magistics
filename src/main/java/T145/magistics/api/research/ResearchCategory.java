package T145.magistics.api.research;

import net.minecraft.util.ResourceLocation;

public class ResearchCategory {

	private final String key;
	private final String name;
	private final ResourceLocation icon;
	private final ResourceLocation background;

	public ResearchCategory(String key, String name, ResourceLocation icon, ResourceLocation background) {
		this.key = key;
		this.name = name;
		this.icon = icon;
		this.background = background;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public ResourceLocation getIcon() {
		return icon;
	}

	public ResourceLocation getBackground() {
		return background;
	}
}