package T145.magistics.common.items.base;

import T145.magistics.common.Magistics;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item {

	protected final String BASE_NAME;
	protected String[] VARIANTS;

	public ItemBase(String name, String... variants) {
		setRegistryName(new ResourceLocation(Magistics.ID, name));
		setUnlocalizedName(Magistics.ID + '.' + name);
		setCreativeTab(Magistics.TAB);
		//setNoRepair();
		setHasSubtypes(variants.length > 1);

		BASE_NAME = name;

		if (variants.length == 0) {
			VARIANTS = new String[] { name };
		} else {
			VARIANTS = variants;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (hasSubtypes && stack.getMetadata() < VARIANTS.length && VARIANTS[stack.getMetadata()] != BASE_NAME) {
			return super.getUnlocalizedName() + "." + VARIANTS[stack.getMetadata()];
		}
		return super.getUnlocalizedName(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (!hasSubtypes) {
			super.getSubItems(tab, items);
		} else {
			for (int meta = 0; meta < VARIANTS.length; ++meta) {
				items.add(new ItemStack(this, 1, meta));
			}
		}
	}
}