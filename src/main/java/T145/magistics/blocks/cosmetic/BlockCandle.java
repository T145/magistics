package T145.magistics.blocks.cosmetic;

import java.util.Random;

import javax.annotation.Nullable;

import T145.magistics.blocks.MBlock;
import T145.magistics.client.lib.Render;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandle extends MBlock<EnumDyeColor> {

	protected BlockCandle(String name) {
		super(name, Material.CIRCUITS, EnumDyeColor.class);
		setHardness(0.1F);
		setSoundType(SoundType.CLOTH);
		setLightLevel(0.9375F);
	}

	public BlockCandle() {
		this("candle");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public MapColor getMapColor(IBlockState state) {
		return state.getValue(variant).getMapColor();
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return world.isSideSolid(pos, EnumFacing.UP);
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		World zone = (World) world;

		if (!canPlaceBlockAt(zone, pos.down())) {
			dropBlockAsItem(zone, pos, world.getBlockState(pos), 0);
			zone.setBlockToAir(pos);
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing par5) {
		return canPlaceBlockAt(world, pos.down());
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(Render.W6, 0D, Render.W6, Render.W10, Render.W8, Render.W10);
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return null;
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
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		double x = pos.getX() + 0.5F;
		double y = pos.getY() + 0.7F;
		double z = pos.getZ() + 0.5F;

		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0D, 0D, 0D, new int[0]);
		world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0D, 0D, 0D, new int[0]);
	}
}