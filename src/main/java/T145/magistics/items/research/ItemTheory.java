package T145.magistics.items.research;

import T145.magistics.api.variants.items.ResearchType;
import T145.magistics.items.MItem;

public class ItemTheory extends MItem {

	public ItemTheory() {
		super("research_theory", ResearchType.getTypes());
	}
}