package T145.magistics.items;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.objects.IModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShard extends Item implements IModel, IItemColor {

	public static final int[] COLORS = { 16777086, 16727041, 37119, 40960, 16711935, 9699539 };
	private final String name;
	private final boolean hasEffect;

	public static enum ItemType implements IStringSerializable {

		AIR, FIRE, WATER, EARTH, LIGHT, DARK;

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		public String getClientName() {
			return "variant=" + getName();
		}

		public static ItemType byMetadata(int meta) {
			return values()[MathHelper.clamp_int(meta, 0, meta)];
		}
	}

	public ItemShard(String name, boolean hasEffect) {
		this.name = name;
		this.hasEffect = hasEffect;

		setRegistryName(new ResourceLocation(Magistics.MODID, name));

		setCreativeTab(Magistics.TAB);
		setUnlocalizedName(name);
		setHasSubtypes(true);

		GameRegistry.register(this);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + ItemType.byMetadata(stack.getMetadata()).getName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
		for (ItemType type : ItemType.values()) {
			subItems.add(new ItemStack(item, 1, type.ordinal()));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (ItemType type : ItemType.values()) {
			ModelLoader.setCustomModelResourceLocation(this, type.ordinal(), new ModelResourceLocation(getRegistryName(), name));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		return COLORS[stack.getItemDamage()];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return hasEffect;
	}
}