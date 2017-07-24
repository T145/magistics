package T145.magistics.blocks.cosmetic;

import T145.magistics.api.variants.Aspect;
import T145.magistics.blocks.MBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties.PropertyAdapter;

public class BlockCrystal extends MBlock {

	public static final PropertyInteger SIZE = PropertyInteger.create("size", 0, 3);
	public static final PropertyInteger GENERATION = PropertyInteger.create("gen", 1, 4);

	public static final IUnlistedProperty<Boolean> NORTH = new PropertyAdapter<Boolean>(PropertyBool.create("north"));
	public static final IUnlistedProperty<Boolean> EAST = new PropertyAdapter<Boolean>(PropertyBool.create("east"));
	public static final IUnlistedProperty<Boolean> SOUTH = new PropertyAdapter<Boolean>(PropertyBool.create("south"));
	public static final IUnlistedProperty<Boolean> WEST = new PropertyAdapter<Boolean>(PropertyBool.create("west"));
	public static final IUnlistedProperty<Boolean> UP = new PropertyAdapter<Boolean>(PropertyBool.create("up"));
	public static final IUnlistedProperty<Boolean> DOWN = new PropertyAdapter<Boolean>(PropertyBool.create("down"));

	private final Aspect aspect;

	public Aspect getAspect() {
		return aspect;
	}

	public BlockCrystal(Aspect aspect) {
		super("crystal_" + aspect.getName(), Material.GLASS);
		this.aspect = aspect;
		this.setDefaultState(getDefaultState().withProperty(SIZE, 0).withProperty(GENERATION, 1));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		IProperty[] listedProperties = { SIZE, GENERATION };
		IUnlistedProperty[] unlistedProperties = { UP, DOWN, NORTH, EAST, WEST, SOUTH };
		return new ExtendedBlockState(this, listedProperties, unlistedProperties);
	}

	private boolean drawAt(IBlockAccess world, BlockPos pos, EnumFacing side) {
		IBlockState state = world.getBlockState(pos);
		return state.getMaterial() == Material.ROCK && state.getBlock().isSideSolid(state, world, pos, side.getOpposite());
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		if (state instanceof IExtendedBlockState) {
			return ((IExtendedBlockState) state).withProperty(UP, drawAt(world, pos.up(), EnumFacing.UP))
					.withProperty(DOWN, drawAt(world, pos.down(), EnumFacing.DOWN))
					.withProperty(NORTH, drawAt(world, pos.north(), EnumFacing.NORTH))
					.withProperty(EAST, drawAt(world, pos.east(), EnumFacing.EAST))
					.withProperty(SOUTH, drawAt(world, pos.south(), EnumFacing.SOUTH))
					.withProperty(WEST, drawAt(world, pos.west(), EnumFacing.WEST));
		}

		return state;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(SIZE, meta & 0x3).withProperty(GENERATION, 1 + (meta >> 2));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		meta |= state.getValue(SIZE);
		meta |= state.getValue(GENERATION) - 1 << 2;
		return meta;
	}

	public int getGrowth(IBlockState state) {
		return getMetaFromState(state) & 0x3;
	}

	public int getGeneration(IBlockState state) {
		return 1 + (getMetaFromState(state) >> 2);
	}
}