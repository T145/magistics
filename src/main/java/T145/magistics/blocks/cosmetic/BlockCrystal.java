package T145.magistics.blocks.cosmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import T145.magistics.api.logic.IFacing;
import T145.magistics.api.variants.Aspect;
import T145.magistics.blocks.MBlock;
import T145.magistics.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystal extends MBlock {

	public static final PropertyDirection FACING = IFacing.DIRECTIONAL_FACING;
	private final Aspect aspect;

	public BlockCrystal(Aspect aspect) {
		super("crystal_" + aspect.getName(), Material.GLASS);
		this.aspect = aspect;
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		setHardness(1.5F);
		setResistance(3F);
		setLightLevel(0.5F);
		setSoundType(SoundType.GLASS);
		setHarvestLevel("pickaxe", 0);
	}

	public Aspect getAspect() {
		return aspect;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override // ONLY WORKS FOR getItemDropped()
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return MathHelper.clamp(quantityDropped(random) + random.nextInt(fortune + 1), 2, 8);
	}

	@Override // ONLY WORKS FOR getItemDropped()
	public int quantityDropped(Random random) {
		return 2 + random.nextInt(3);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemById(0);
	}

	public ItemStack makeCrystal(Aspect aspect, int stackSize) {
		if (aspect == null) {
			return null;
		}
		return new ItemStack(ModItems.crystalShard, stackSize, aspect.ordinal() + 1);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		items.add(makeCrystal(aspect, quantityDropped(RANDOM)));
		return items;
	}
}