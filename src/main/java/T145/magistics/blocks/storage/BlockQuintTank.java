package T145.magistics.blocks.storage;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.blocks.MBlock;
import T145.magistics.blocks.storage.BlockQuintTank.TankType;
import T145.magistics.client.lib.Render;
import T145.magistics.tiles.storage.TileQuintTank;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockQuintTank extends MBlock<TankType> {

	public static final AxisAlignedBB TANK_AABB = new AxisAlignedBB(Render.W1, 0D, Render.W1, 1D - Render.W1, 1D, 1D - Render.W1);
	public static final PropertyBool JOINED_ABOVE = PropertyBool.create("joined_above");
	public static final PropertyBool JOINED_BELOW = PropertyBool.create("joined_below");
	public static final PropertyBool INBETWEEN = PropertyBool.create("inbetween");

	public BlockQuintTank() {
		super("tank", Material.GLASS, TankType.class);
		setSoundType(SoundType.GLASS);
		setHardness(1F);
	}

	@SideOnly(Side.CLIENT)
	public StateMap getStateMap() {
		StateMap.Builder builder = new StateMap.Builder();
		builder.withName(variant);
		builder.withSuffix("_tank");
		return builder.build();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		BlockStateContainer variantContainer = super.createBlockState();
		List<IProperty> properties = new ArrayList<>();

		if (hasVariants()) {
			properties.addAll(variantContainer.getProperties());
		}

		properties.add(JOINED_ABOVE);
		properties.add(JOINED_BELOW);
		properties.add(INBETWEEN);

		return properties.isEmpty() ? super.createBlockState() : new BlockStateContainer(this, properties.toArray(new IProperty[properties.size()]));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileQuintTank) {
			TileQuintTank tank = (TileQuintTank) tile;
			boolean joinedAbove = tank.canConnectToTank(EnumFacing.UP);
			boolean joinedBelow = tank.canConnectToTank(EnumFacing.DOWN);
			boolean inbetween = joinedAbove && joinedBelow;

			state = state.withProperty(JOINED_ABOVE, joinedAbove).withProperty(JOINED_BELOW, joinedBelow).withProperty(INBETWEEN, inbetween);
		}

		return state;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileQuintTank(meta == 1);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return TANK_AABB;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side.getAxis() != EnumFacing.Axis.Y || !(world.getBlockState(pos.offset(side)).getBlock() instanceof BlockQuintTank);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileQuintTank) {
			TileQuintTank tank = (TileQuintTank) tile;
			return (int) (tank.getQuints() * 15 / tank.getMaxQuints());
		}

		return 0;
	}

	public static enum TankType implements IStringSerializable {

		BASE, REINFORCED;

		@Override
		public String getName() {
			return name().toLowerCase();
		}
	}
}