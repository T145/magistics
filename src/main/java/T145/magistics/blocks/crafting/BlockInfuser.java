package T145.magistics.blocks.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

import T145.magistics.Magistics;
import T145.magistics.blocks.MBlock;
import T145.magistics.blocks.crafting.BlockInfuser.InfuserType;
import T145.magistics.client.lib.Render;
import T145.magistics.lib.managers.InventoryManager;
import T145.magistics.tiles.crafting.TileInfuser;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfuser extends MBlock<InfuserType> {

	public static final ImmutableList<IProperty<Boolean>> CONNECTIONS = ImmutableList.copyOf(Stream.of(EnumFacing.HORIZONTALS).map(facing -> PropertyBool.create(facing.getName())).collect(Collectors.toList()));
	public static final AxisAlignedBB INFUSER_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - Render.W1, 1D);

	public BlockInfuser() {
		super("infuser", Material.ROCK, InfuserType.class);
		setSoundType(SoundType.STONE);
		setHardness(2F);
		setResistance(15F);
	}

	@SideOnly(Side.CLIENT)
	public StateMap getStateMap() {
		StateMap.Builder builder = new StateMap.Builder();
		builder.withName(variant);
		builder.withSuffix("_infuser");
		return builder.build();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		BlockStateContainer variantContainer = super.createBlockState();
		List<IProperty> properties = new ArrayList<IProperty>();

		if (hasVariants()) {
			properties.addAll(variantContainer.getProperties());
		}

		properties.addAll(CONNECTIONS);

		return properties.isEmpty() ? super.createBlockState() : new BlockStateContainer(this, properties.toArray(new IProperty[properties.size()]));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileInfuser) {
			TileInfuser infuser = (TileInfuser) tile;

			for (EnumFacing facing : EnumFacing.HORIZONTALS) {
				state = state.withProperty(CONNECTIONS.get(facing.getHorizontalIndex()), infuser.isConnected(facing));
			}
		}

		return state;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileInfuser(meta == 1);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return INFUSER_AABB;
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hand.equals(EnumHand.MAIN_HAND) && world.getTileEntity(pos) instanceof TileInfuser) {
			player.openGui(Magistics.MODID, 0, world, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		return InventoryManager.calcRedstone(world.getTileEntity(pos));
	}

	public static enum InfuserType implements IStringSerializable {

		LIGHT, DARK;

		@Override
		public String getName() {
			return name().toLowerCase();
		}
	}
}