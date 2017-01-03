package T145.magistics.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import T145.magistics.Magistics;
import T145.magistics.api.enums.EnumCrucible;
import T145.magistics.api.objects.IModel;
import T145.magistics.api.objects.ITile;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.entities.EntityVisSlime;
import T145.magistics.tiles.TileCrucible;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrucible extends MBlock<EnumCrucible> implements IModel, ITile {

	public static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0D, 0D, 0D, 1D, BlockRenderer.W5, 1D);
	public static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, BlockRenderer.W2);
	public static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0D, 0D, BlockRenderer.W14, 1D, 1D, 1D);
	public static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(BlockRenderer.W14, 0D, 0D, 1D, 1D, 1D);
	public static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0D, 0D, 0D, BlockRenderer.W2, 1D, 1D);

	public BlockCrucible(String name) {
		super(name, Material.IRON, EnumCrucible.class);

		setSoundType(SoundType.METAL);
		setHardness(3F);
		setResistance(17F);

		GameRegistry.registerTileEntity(TileCrucible.class, TileCrucible.class.getSimpleName());
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity) {
		if (getMetaFromState(state) < 3) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
		}
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
			Magistics.proxy.greenFlameFX(world, pos.getX() + 0.2F + rand.nextFloat() * 0.6F, pos.getY() + 0.1F, pos.getZ() + 0.2F + rand.nextFloat() * 0.6F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderer() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (EnumCrucible type : EnumCrucible.values()) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), type.getClientName()));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!world.isRemote && entity.getEntityBoundingBox().minY <= pos.getY() + 0.7D) {
			if (entity instanceof EntityItem) {
				EntityItem item = (EntityItem) entity;

				item.motionX += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.05F;
				item.motionY += world.rand.nextFloat() * 0.1F;
				item.motionZ += (world.rand.nextFloat() - world.rand.nextFloat()) * 0.05F;

				item.setPickupDelay(10);
			} else if (entity instanceof EntityLiving && !(entity instanceof EntityVisSlime)) {
				entity.attackEntityFrom(DamageSource.magic, 1);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.4F, 2F + world.rand.nextFloat() * 0.4F);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		TileCrucible crucible = new TileCrucible();
		crucible.setTier(meta);
		return crucible;
	}
}