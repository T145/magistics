package T145.magistics.blocks;

import javax.annotation.Nullable;

import T145.magistics.Magistics;
import T145.magistics.lib.managers.InventoryManager;
import T145.magistics.tiles.MTileInventory;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MBlock<T extends Enum<T> & IStringSerializable> extends Block implements ITileEntityProvider {

	public final PropertyEnum<T> variant;
	public final T[] variantValues;
	protected static IProperty[] tempVariants;

	public MBlock(String name, Material material, Class variants) {
		super(createProperties(material, variants));

		if (variants == Object.class || variants == null) {
			variant = null;
			variantValues = null;
		} else {
			variant = PropertyEnum.create("variant", variants);
			variantValues = variant.getValueClass().getEnumConstants();

			setDefaultState(blockState.getBaseState().withProperty(variant, variantValues[0]));
		}

		setRegistryName(new ResourceLocation(Magistics.MODID, name));
		setUnlocalizedName(name);
		setCreativeTab(Magistics.TAB);

		GameRegistry.register(this);

		if (variant != null && variantValues != null) {
			GameRegistry.register(new MBlockItem(this, variant.getValueClass()), getRegistryName());

			for (T variant : variantValues) {
				TileEntity tile = createNewTileEntity(null, variant.ordinal());

				if (tile != null) {
					isBlockContainer = true;
					Class tileClass = tile.getClass();
					GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
				}
			}
		} else {
			GameRegistry.register(new MBlockItem(this), getRegistryName());

			TileEntity tile = createNewTileEntity(null, 0);

			if (isBlockContainer = tile != null) {
				Class tileClass = tile.getClass();
				GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
			}
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
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		if (variant != null) {
			for (T variant : variantValues) {
				list.add(new ItemStack(item, 1, variant.ordinal()));
			}
		} else {
			super.getSubBlocks(item, tab, list);
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (variant == null) {
			return super.getStateFromMeta(meta);
		}

		if (meta < variantValues.length) {
			return getDefaultState().withProperty(variant, variantValues[meta]);
		}

		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return variant == null ? 0 : state.getValue(variant).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (variant == null) {
			if (tempVariants == null) {
				return super.createBlockState();
			}

			return new BlockStateContainer(this, tempVariants);
		}

		return new BlockStateContainer(this, new IProperty[] { variant });
	}

	public IProperty[] getVariants() {
		return new IProperty[] { variant == null ? null : variant };
	}

	public boolean hasVariants() {
		return getVariants() != null;
	}

	public String getVariantName(IBlockState state) {
		if (variant == null) {
			String name = state.getBlock().getUnlocalizedName();
			return name.substring(name.indexOf(".") + 1);
		}
		return state.getValue(variant).getName();
	}

	public boolean isVariant(IBlockState state, IStringSerializable type) {
		if (variant == null) {
			return false;
		}
		return state.getValue(variant) == type;
	}

	@Override
	public abstract TileEntity createNewTileEntity(World world, int meta);

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return isBlockContainer;
	}

	@Override
	@Nullable
	public TileEntity createTileEntity(World world, IBlockState state) {
		if (isBlockContainer) {
			return ((ITileEntityProvider) this).createNewTileEntity(world, getMetaFromState(state));
		}
		return null;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof MTileInventory) {
			InventoryManager.dropInventory((MTileInventory) tile, world, state, pos);
		}

		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param) {
		super.eventReceived(state, world, pos, id, param);
		TileEntity tile = world.getTileEntity(pos);
		return tile == null ? false : tile.receiveClientEvent(id, param);
	}
}