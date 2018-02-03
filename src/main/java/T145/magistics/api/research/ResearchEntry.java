package T145.magistics.api.research;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.util.ResourceLocation;

public class ResearchEntry {

	private final String id;
	private final ResearchCategory category;
	private final int displayRow;
	private final int displayColumn;
	private final ResourceLocation icon;
	private final Map<String, ResearchEntry> dependencies = new HashMap<>();

	public ResearchEntry(String id, ResearchCategory category, int displayRow, int displayColumn, ResourceLocation icon, ResearchEntry... dependencies) {
		this.id = id;
		this.category = category;
		this.displayRow = displayRow;
		this.displayColumn = displayColumn;
		this.icon = icon;

		for (ResearchEntry dependency : dependencies) {
			this.dependencies.put(dependency.getId(), dependency);
		}

		category.addEntry(this);
	}

	public String getId() {
		return id;
	}

	public ResearchCategory getCategory() {
		return category;
	}

	public Map<String, ResearchEntry> getDependencies() {
		return dependencies;
	}
}