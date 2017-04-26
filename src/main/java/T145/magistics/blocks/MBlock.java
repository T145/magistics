package T145.magistics.blocks;

import T145.magistics.Magistics;
import T145.magistics.api.variants.IVariant;
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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MBlock<T extends Enum<T> & IVariant> extends Block implements ITileEntityProvider {

	public final PropertyEnum<T> VARIANT;
	public final T[] VARIANT_VALUES;
	private static IProperty[] tempVariants;

	private class MBlockItem extends ItemBlock {

		public MBlockItem(Block block) {
			super(block);
			setHasSubtypes(true);
			setMaxDamage(0);
		}

		@Override
		public int getMetadata(int meta) {
			return meta;
		}

		@Override
		public String getUnlocalizedName(ItemStack stack) {
			return super.getUnlocalizedName() + "." + VARIANT_VALUES[stack.getMetadata()].getName();
		}
	}

	public MBlock(String name, Material material, Class variants) {
		super(createProperties(material, variants));

		if (variants == Object.class || variants == null) {
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

		if (VARIANT != null && VARIANT_VALUES != null) {
			GameRegistry.register(new MBlockItem(this), getRegistryName());

			for (T variant : VARIANT_VALUES) {
				TileEntity tile = createNewTileEntity(null, variant.ordinal());
				Class tileClass = tile.getClass();

				if (tile != null) {
					isBlockContainer = true;
					GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
				}
			}
		} else {
			TileEntity tile = createNewTileEntity(null, 0);
			Class tileClass = tile.getClass();

			if (isBlockContainer = tile != null) {
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
		if (VARIANT != null) {
			for (T variant : VARIANT_VALUES) {
				list.add(new ItemStack(item, 1, variant.ordinal()));
			}
		} else {
			list.add(new ItemStack(item));
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

	@Override
	public abstract TileEntity createNewTileEntity(World world, int meta);

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return isBlockContainer;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile != null && tile instanceof MTileInventory) {
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