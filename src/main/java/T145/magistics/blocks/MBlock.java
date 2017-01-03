package T145.magistics.blocks;

import java.util.List;

import T145.magistics.Magistics;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MBlock<T extends Enum<T> & IStringSerializable> extends Block {

	public final PropertyEnum<T> VARIANT;
	public final T[] VARIANT_VALUES;
	private static IProperty[] tempVariants;

	public MBlock(String name, Material material, Class variants) {
		super(createProperties(material, variants));

		if (variants == Object.class) {
			VARIANT = null;
			VARIANT_VALUES = null;
		} else {
			VARIANT = PropertyEnum.create("variant", variants);
			VARIANT_VALUES = VARIANT.getValueClass().getEnumConstants();

			setDefaultState(blockState.getBaseState().withProperty(VARIANT, VARIANT_VALUES[0]));
		}

		setRegistryName(new ResourceLocation(Magistics.MODID, name));
		setUnlocalizedName(name);
		setCreativeTab(Magistics.TAB);

		GameRegistry.register(this);

		if (variants != null) {
			GameRegistry.register(new MBlockItem(this, variants), getRegistryName());
		}
	}

	public MBlock(String name, Material material) {
		this(name, material, Object.class);
	}

	protected static Material createProperties(Material material, Class variants) {
		tempVariants = null;

		if (variants != Object.class) {
			tempVariants = new IProperty[] { PropertyEnum.create("variant", variants) };
		}

		return material;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if (VARIANT != null) {
			for (T variant : VARIANT_VALUES) {
				list.add(new ItemStack(item, 1, variant.ordinal()));
			}
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (VARIANT == null) {
			return super.getStateFromMeta(meta);
		}

		if (meta < VARIANT_VALUES.length) {
			return getDefaultState().withProperty(VARIANT, VARIANT_VALUES[meta]);
		}

		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return VARIANT == null ? super.getMetaFromState(state) : state.getValue(VARIANT).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (VARIANT == null) {
			if (tempVariants == null) {
				return super.createBlockState();
			}

			return new BlockStateContainer(this, tempVariants);
		}

		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}
}