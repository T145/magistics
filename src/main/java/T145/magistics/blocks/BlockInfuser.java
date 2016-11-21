package T145.magistics.blocks;

import java.util.List;

import javax.annotation.Nullable;

import T145.magistics.Magistics;
import T145.magistics.api.objects.IModel;
import T145.magistics.api.objects.ITile;
import T145.magistics.api.objects.IVariant;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfuser extends Block implements IModel, ITile {

	public static enum BlockType implements IVariant {

		LIGHT, DARK;

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		@Override
		public String getClientName() {
			return "variant=" + getName();
		}

		public static BlockType byMetadata(int meta) {
			return values()[MathHelper.clamp_int(meta, 0, meta)];
		}
	}

	public static final PropertyEnum<BlockType> VARIANT = PropertyEnum.<BlockType>create("variant", BlockType.class);
	protected static final AxisAlignedBB INFUSER_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - BlockRenderer.W1, 1D);

	public BlockInfuser(String name) {
		super(Material.ROCK);

		setDefaultState(blockState.getBaseState().withProperty(VARIANT, BlockType.LIGHT));
		setRegistryName(new ResourceLocation(Magistics.MODID, name));

		setCreativeTab(Magistics.TAB);
		setUnlocalizedName(name);
		setSoundType(SoundType.STONE);
		setHardness(2F);
		setResistance(15F);

		GameRegistry.register(this);
		GameRegistry.register(new BlockMagisticsItem(this, BlockType.class), getRegistryName());
		GameRegistry.registerTileEntity(TileInfuser.class, TileInfuser.class.getSimpleName());
		GameRegistry.registerTileEntity(TileInfuserDark.class, TileInfuserDark.class.getSimpleName());
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return INFUSER_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (BlockType type : BlockType.values()) {
			list.add(new ItemStack(item, 1, type.ordinal()));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderer() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuserDark.class, new RenderInfuser());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (BlockType type : BlockType.values()) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), type.getClientName()));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), "inventory," + type.getClientName()));
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileInfuser infuser = (TileInfuser) world.getTileEntity(pos);

		if (infuser != null) {
			infuser.setFacing(placer.getHorizontalFacing().getIndex());
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Magistics.MODID, 0, world, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		IInventory inv = (IInventory) world.getTileEntity(pos);

		if (inv != null) {
			InventoryHelper.dropInventoryItems(world, pos, inv);
			world.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos pos) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(pos));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return meta == 1 ? new TileInfuserDark() : new TileInfuser();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, BlockType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}
}