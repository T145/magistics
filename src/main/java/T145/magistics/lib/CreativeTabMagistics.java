package T145.magistics.lib;

import net.minecraft.item.ItemStack;
import T145.magistics.Magistics;
import T145.magistics.blocks.BlockInfuser;

public class CreativeTabMagistics extends CreativeTabCustom {
	public CreativeTabMagistics() {
		super(Magistics.MODID.toLowerCase(), new ItemStack(BlockInfuser.INSTANCE, 1, 0));
		setBackgroundImageName("magistics.png");
		setNoTitle();
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
}