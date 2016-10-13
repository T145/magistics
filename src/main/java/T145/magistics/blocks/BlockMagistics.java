package T145.magistics.blocks;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.blocks.IBlockMagistics;
import T145.magistics.api.blocks.IBlockTypes;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMagistics<E extends IBlockMagistics> extends Block implements IBlockTypes {

	protected static IProperty[] tempVariants;
	public final PropertyEnum TYPE;
	public final E[] variantValues;

	public BlockMagistics(Material material, Class types) {
		super(createProperties(material, types));

		if (types != Object.class) {
			TYPE = PropertyEnum.create("type", types);
			variantValues = (E[]) TYPE.getValueClass().getEnumConstants();
		} else {
			TYPE = null;
			variantValues = null;
		}

		setCreativeTab(Magistics.tab);
		setInitDefaultState();
	}

	public BlockMagistics(Material material) {
		this(material, Object.class);
	}

	public BlockMagistics(Material material, SoundType st) {
		this(material, Object.class);
		setSoundType(st);
	}

	public BlockMagistics(Material material, Class types, SoundType st) {
		this(material, types);
		setSoundType(st);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (TYPE == null) {
			if (tempVariants == null) {
				return super.createBlockState();
			}

			return new BlockStateContainer(this, tempVariants);
		}

		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	protected static Material createProperties(Material material, Class types) {
		tempVariants = null;

		if (types != Object.class) {
			tempVariants = new IProperty[] { PropertyEnum.create("type", types) };
		}

		return material;
	}

	public void setInitDefaultState() {
		if (TYPE != null) {
			setDefaultState(blockState.getBaseState().withProperty(TYPE, variantValues[0]));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if (hasTypes()) {
			IBlockState state = getBlockState().getBaseState();

			for (IProperty prop : getTypes()) {
				for (Object value : prop.getAllowedValues()) {
					list.add(new ItemStack(item, 1, damageDropped(state)));
					state = state.cycleProperty(prop);
				}
			}
		} else {
			list.add(new ItemStack(item, 1, 0));
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		/*if (TYPE == null) {
			return super.getStateFromMeta(meta);
		}*/

		if (TYPE != null && meta < variantValues.length) {
			return getDefaultState().withProperty(TYPE, variantValues[meta]);
		}

		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if (TYPE == null) {
			return super.getMetaFromState(state);
		}

		int meta = ((Enum) state.getValue(TYPE)).ordinal();
		return meta;
	}

	@Override
	public IProperty[] getTypes() {
		return new IProperty[] { TYPE };
	}

	@Override
	public boolean hasTypes() {
		return getTypes() != null;
	}

	@Override
	public String getTypeName(IBlockState state) {
		if (TYPE == null) {
			String unlocalizedName = state.getBlock().getUnlocalizedName();
			return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
		}

		IBlockMagistics type = (IBlockMagistics) state.getValue(TYPE);
		return type.getName();
	}
}