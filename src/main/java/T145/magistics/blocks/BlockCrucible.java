package T145.magistics.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import T145.magistics.Magistics;
import T145.magistics.api.blocks.IBlockMagistics;
import T145.magistics.tiles.TileCrucible;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrucible extends BlockMagistics implements ITileEntityProvider {

	private int delay = 0;

	public static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0D, 0D, 0D, 1.0D, 0.3125D, 1.0D);
	public static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1.0D, 1.0D, 0.125D);
	public static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0D, 0D, 0.875D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0D, 0D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.125D, 1.0D, 1.0D);

	public BlockCrucible() {
		super(Material.IRON, CrucibleType.class, SoundType.METAL);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
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
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		TileCrucible crucible = (TileCrucible) world.getTileEntity(pos);

		if (crucible.hasWorldObj() && crucible.getBlockMetadata() < 3) {
			Magistics.proxy.createGreenFlameFX(world, pos.getX() + 0.2F + rand.nextFloat() * 0.6F, pos.getY() + 0.1F, pos.getZ() + 0.2F + rand.nextFloat() * 0.6F);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityItem && entity.posY <= pos.getY() + 0.7D) {
			EntityItem item = (EntityItem) entity;

			item.motionX += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.05F;
			item.motionY += world.rand.nextFloat() * 0.1F;
			item.motionZ += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.05F;

			item.setPickupDelay(10);
			item.lifespan = 0;
		}

		delay += 1;

		if (delay < 5) {
			return;
		}

		delay = 0;

		if (entity instanceof EntityLiving) {
			entity.attackEntityFrom(DamageSource.magic, 1);
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.4F, 2.0F + world.rand.nextFloat() * 0.4F);
		}
	}

	@Override
	public boolean isPassable(IBlockAccess world, BlockPos pos) {
		TileCrucible crucible = (TileCrucible) world.getTileEntity(pos);

		if (crucible.hasWorldObj() && crucible.getBlockMetadata() < 3) {
			return true;
		}

		return false;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	/*
	 * public int getComparatorputOverride(IBlockState blockState, World world,
	 * BlockPos pos) { return visLevels; }
	 */

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileCrucible(CrucibleType.values()[meta]);
	}

	public static enum CrucibleType implements IBlockMagistics {
		BASIC, EYES, THAUMIUM, SOULS;

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		@Override
		public String toString() {
			return getName();
		}
	}
}