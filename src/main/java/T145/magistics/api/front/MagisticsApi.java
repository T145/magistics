package T145.magistics.api.front;

import java.util.HashSet;
import java.util.Set;

import T145.magistics.api.research.ResearchCategory;

public class MagisticsApi {

	private static final Set<ResearchCategory> RESEARCH_REGISTRY = new HashSet<>();

	public static void registerResearch(ResearchCategory category) {
		RESEARCH_REGISTRY.add(category);
	}

	public Set<ResearchCategory> getResearch() {
		return RESEARCH_REGISTRY;
	}
}