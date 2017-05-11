package T145.magistics.items.research;

import T145.magistics.api.variants.items.ResearchType;
import T145.magistics.items.MItem;

public class ItemFragment extends MItem {

	public ItemFragment() {
		super("research_fragment", ResearchType.getTypes());
	}
}