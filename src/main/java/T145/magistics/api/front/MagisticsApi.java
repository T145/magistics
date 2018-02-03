package T145.magistics.api.front;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import T145.magistics.api.research.ResearchCategory;
import T145.magistics.api.research.ResearchEntry;

public class MagisticsApi {

	private static final Map<String, ResearchCategory> RESEARCH_REGISTRY = new HashMap<>();

	private MagisticsApi() {}

	public static void registerResearch(ResearchCategory category) {
		RESEARCH_REGISTRY.put(category.getId(), category);
	}

	@Nullable
	public static ResearchCategory getCategory(String categoryId) {
		return RESEARCH_REGISTRY.get(categoryId);
	}

	@Nullable
	public static ResearchEntry getEntry(String parentId, String entryId) {
		ResearchCategory parent = RESEARCH_REGISTRY.get(parentId);
		return parent == null ? null : parent.getEntry(entryId);
	}
}