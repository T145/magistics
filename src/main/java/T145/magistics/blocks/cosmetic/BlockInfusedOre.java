package T145.magistics.blocks.cosmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import T145.magistics.api.variants.items.EnumShard;
import T145.magistics.blocks.MBlock;
import T145.magistics.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfusedOre extends MBlock<EnumShard> {

	private Random rand = new Random();

	public BlockInfusedOre(String name) {
		super(name, Material.ROCK, EnumShard.class);

		setSoundType(SoundType.STONE);
		setResistance(5F);
		setHardness(1.5F);
		setTickRandomly(true);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = new ArrayList<ItemStack>();

		for (int i = 0; i < 1 + rand.nextInt(2 + fortune); ++i) {
			drops.add(new ItemStack(ModItems.crystalShard, 1, getMetaFromState(state)));
		}

		return drops;
	}

	@Override
	public boolean canSilkHarvest() {
		return true;
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return MathHelper.getInt(rand, 0, 3);
	}
}