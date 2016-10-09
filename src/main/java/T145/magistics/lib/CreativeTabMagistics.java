package T145.magistics.lib;

import T145.magistics.Magistics;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabMagistics extends CreativeTabCustom {

	public CreativeTabMagistics() {
		super(Magistics.MODID.toLowerCase(), Item.getItemFromBlock(Blocks.BOOKSHELF));
		setBackgroundImageName("magistics.png");
		setNoTitle();
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
}