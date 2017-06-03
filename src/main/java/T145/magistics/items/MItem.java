package T145.magistics.items;

import T145.magistics.Magistics;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MItem extends Item {

	protected final String name;
	protected final String[] variants;

	public MItem(String name, String[] variants) {
		this.name = name;
		this.variants = variants;

		setRegistryName(new ResourceLocation(Magistics.MODID, name));
		setUnlocalizedName(name);
		setCreativeTab(Magistics.TAB);
		setNoRepair();

		if (variants.length == 0) {
			variants = new String[] { name };
		} else {
			setHasSubtypes(true);
		}

		GameRegistry.register(this);
	}

	public MItem(String name) {
		this(name, new String[0]);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (hasSubtypes && stack.getMetadata() < variants.length && variants[stack.getMetadata()] != name) {
			return String.format(super.getUnlocalizedName() + ".%s", new Object[] { variants[stack.getMetadata()] });
		}
		return super.getUnlocalizedName(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (hasSubtypes) {
			for (int meta = 0; meta < variants.length; ++meta) {
				subItems.add(new ItemStack(this, 1, meta));
			}
		} else {
			super.getSubItems(item, tab, subItems);
		}
	}
}