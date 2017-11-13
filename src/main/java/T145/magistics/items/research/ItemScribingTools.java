package T145.magistics.items.research;

import T145.magistics.api.research.IScribingTools;
import T145.magistics.items.MItem;

public class ItemScribingTools extends MItem implements IScribingTools {

	public ItemScribingTools() {
		super("scribing_tools", new String[0]);
		setMaxStackSize(1);
		setMaxDamage(100);
		setHasSubtypes(false);
	}
}