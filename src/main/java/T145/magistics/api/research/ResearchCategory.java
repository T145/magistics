package T145.magistics.api.research;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;

public class ResearchCategory {

	private final String id;
	private final ResourceLocation icon;
	private final ResourceLocation background;
	private final Map<String, ResearchEntry> entries = new HashMap<>();

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

	void addEntry(ResearchEntry entry) {
		entries.put(entry.getId(), entry);
	}

	public ResearchEntry getEntry(String entryId) {
		return entries.get(entryId);
	}
}