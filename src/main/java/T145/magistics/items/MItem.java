package T145.magistics.items;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
	protected List<String> variants = new ArrayList<String>();

	public MItem(String name, List<String> variants) {
		this.name = name;

		setRegistryName(new ResourceLocation(Magistics.MODID, name));
		setUnlocalizedName(name);
		setCreativeTab(Magistics.TAB);
		setNoRepair();

		if (variants.isEmpty()) {
			variants.add(name);
		} else {
			this.variants = variants;
			setHasSubtypes(true);
		}

		GameRegistry.register(this);
	}

	public MItem(String name) {
		this(name, new ArrayList<String>());
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (hasSubtypes && (stack.getMetadata() < variants.size()) && (variants.get(stack.getMetadata()) != name)) {
			return String.format(super.getUnlocalizedName() + ".%s", new Object[] { variants.get(stack.getMetadata()) });
		}
		return super.getUnlocalizedName(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (hasSubtypes) {
			for (int meta = 0; meta < variants.size(); ++meta) {
				subItems.add(new ItemStack(this, 1, meta));
			}
		} else {
			super.getSubItems(item, tab, subItems);
		}
	}

	public List<String> getVariantNames() {
		return variants;
	}

	public ModelResourceLocation getCustomModelResourceLocation(String variant) {
		if (variant.equals(name)) {
			return new ModelResourceLocation("magistics:" + name);
		}
		return new ModelResourceLocation("magistics:" + name, variant);
	}
}