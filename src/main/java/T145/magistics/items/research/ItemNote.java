package T145.magistics.items.research;

import T145.magistics.api.variants.items.ResearchType;
import T145.magistics.items.MItem;

public class ItemNote extends MItem {

	public ItemNote() {
		super("research_note", ResearchType.getTypes());
	}
}