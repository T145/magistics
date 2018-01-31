package T145.magistics.api.research;

import java.util.Arrays;
import java.util.List;

import net.minecraft.util.ResourceLocation;

public class ResearchEntry {

	private final String id;
	private final ResearchCategory category;
	private final ResourceLocation icon;
	private final List<ResearchEntry> dependencies;

	public ResearchEntry(String id, ResearchCategory category, ResourceLocation icon, ResearchEntry... dependencies) {
		this.id = id;
		this.category = category;
		this.icon = icon;
		this.dependencies = Arrays.asList(dependencies);

		category.addEntry(this);
	}

	public String getId() {
		return id;
	}

	public ResearchCategory getCategory() {
		return category;
	}

	public boolean isParent() {
		return !dependencies.isEmpty();
	}

	public List<ResearchEntry> getDependencies() {
		return dependencies;
	}
}