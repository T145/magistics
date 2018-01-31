package T145.magistics.api.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;

public class ResearchCategory {

	private final List<ResearchEntry> entries = new ArrayList<>();

	private final String id;
	private final ResourceLocation icon;
	private final ResourceLocation background;

	public ResearchCategory(String id,  ResourceLocation icon, ResourceLocation background) {
		this.id = id;
		this.icon = icon;
		this.background = background;
	}

	public String getId() {
		return id;
	}

	public ResourceLocation getIcon() {
		return icon;
	}

	public ResourceLocation getBackground() {
		return background;
	}

	public void addEntry(ResearchEntry entry) {
		entries.add(entry);
	}
}