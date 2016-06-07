package T145.magistics.lib;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import T145.magistics.Magistics;

public class CreativeTabMagistics extends CreativeTabCustom {
	public CreativeTabMagistics() {
		super(Magistics.MODID.toLowerCase(), Item.getItemFromBlock(Blocks.bookshelf));
		setBackgroundImageName("magistics.png");
		setNoTitle();
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
}