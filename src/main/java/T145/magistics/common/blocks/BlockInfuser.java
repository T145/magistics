package T145.magistics.common.blocks;

import static T145.magistics.client.lib.Render.W1;
import static T145.magistics.client.lib.Render.W4;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import T145.magistics.api.core.IStateMapProvider;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.common.blocks.base.BlockBase;
import T145.magistics.common.lib.InventoryIO;
import T145.magistics.common.tiles.TileInfuser;
import T145.magistics.core.Magistics;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfuser extends BlockBase implements IStateMapProvider, ITileEntityProvider {

	public static final PropertyEnum<InfuserType> VARIANT = PropertyEnum.create("variant", InfuserType.class);
	private static final EnumMap<EnumFacing, IProperty<Boolean>> CONNECTIONS = new EnumMap<>(EnumFacing.class);

	static {
		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			CONNECTIONS.put(facing, PropertyBool.create(facing.getName()));
		}
	}

	public static final AxisAlignedBB INFUSER_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - W4, 1D);

	public BlockInfuser() {
		super("infuser", Material.ROCK, false);
		setSoundType(SoundType.STONE);
		setHardness(2F);
		setResistance(15F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileInfuser(InfuserType.values()[meta]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public StateMap getStateMap() {
		StateMap.Builder builder = new StateMap.Builder();
		builder.withName(VARIANT);
		builder.withSuffix("_infuser");
		return builder.build();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		List<IProperty> props = new ArrayList<IProperty>();
		props.add(VARIANT);
		props.addAll(CONNECTIONS.values());
		return new BlockStateContainer(this, props.toArray(new IProperty[props.size()]));
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState().withProperty(VARIANT, InfuserType.values()[meta]);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 1));
	}

	public boolean isConnected(final IBlockState ownState, final EnumFacing side) {
		return ownState.getValue(CONNECTIONS.get(side));
	}

	private boolean canConnectTo(final IBlockState ownState, final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return QuintHelper.getConnectedHandler(world, pos, side) != null;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		for (final EnumFacing facing : EnumFacing.HORIZONTALS) {
			state = state.withProperty(CONNECTIONS.get(facing), canConnectTo(state, world, pos, facing));
		}
		return state;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return INFUSER_AABB;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hand.equals(EnumHand.MAIN_HAND) && world.getTileEntity(pos) instanceof TileInfuser) {
			player.openGui(Magistics.ID, 0, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileInfuser infuser = (TileInfuser) world.getTileEntity(pos);

		if (infuser != null) {
			infuser.setFront(EnumFacing.getDirectionFromEntityLiving(pos, placer));
		}
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		return InventoryIO.calcRedstone(world.getTileEntity(pos));
	}

	public static enum InfuserType implements IStringSerializable {

		LIGHT(new ResourceLocation(Magistics.ID, "textures/gui/gui_infuser.png"), new ResourceLocation(Magistics.ID, "textures/blocks/infuser/symbol.png")),
		DARK(new ResourceLocation(Magistics.ID, "textures/gui/gui_infuser_dark.png"), new ResourceLocation(Magistics.ID, "textures/blocks/infuser/dark_symbol.png"));

		private final ResourceLocation guiResource;
		private final ResourceLocation symbolResource;

		InfuserType(ResourceLocation guiResource, ResourceLocation symbolResource) {
			this.guiResource = guiResource;
			this.symbolResource = symbolResource;
		}

		public boolean isDark() {
			return this == DARK;
		}

		public int getInventorySize() {
			return isDark() ? 6 : 8;
		}

		public ResourceLocation getGuiResource() {
			return guiResource;
		}

		public ResourceLocation getSymbolResource() {
			return symbolResource;
		}

		@Override
		public String getName() {
			return name().toLowerCase();
		}
	}
}