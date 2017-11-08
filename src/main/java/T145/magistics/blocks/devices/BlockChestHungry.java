package T145.magistics.blocks.devices;

import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.blocks.MBlock;
import T145.magistics.blocks.devices.BlockChestHungry.HungryChestType;
import T145.magistics.lib.managers.InventoryManager;
import T145.magistics.tiles.devices.TileChestHungry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

public class BlockChestHungry extends MBlock<HungryChestType> {

	public BlockChestHungry() {
		super("chest_hungry", Material.WOOD, HungryChestType.class);
		setHardness(2.5F);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungry(HungryChestType.values()[meta]);
	}

	public boolean isEnderChest(IBlockState state) {
		return state.getValue(variant) == HungryChestType.ENDER;
	}

	public boolean isEnderChest() {
		return isEnderChest(getDefaultState());
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return Blocks.ENDER_CHEST.getBoundingBox(state, source, pos);
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
	public boolean hasCustomBreakingProgress(IBlockState state) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (isEnderChest(state)) {
			Blocks.ENDER_CHEST.randomDisplayTick(state, world, pos, rand);
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return isEnderChest(state) ? Item.getItemFromBlock(Blocks.OBSIDIAN) : super.getItemDropped(state, rand, fortune);
	}

	@Override
	public int quantityDropped(Random random) {
		return isEnderChest() ? 8 : 1;
	}

	@Override
	public boolean canSilkHarvest() {
		return isEnderChest();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hand.equals(EnumHand.MAIN_HAND) && world.getTileEntity(pos) instanceof TileChestHungry) {
			player.openGui(Magistics.MODID, 1, world, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);

		TileChestHungry chest = (TileChestHungry) world.getTileEntity(pos);

		if (chest != null) {
			EntityPlayer player = (EntityPlayer) placer;

			if (player != null && isEnderChest(state)) {
				chest.setOwner(player);
			}

			chest.setFront(EnumFacing.getDirectionFromEntityLiving(pos, placer));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		TileChestHungry chest = (TileChestHungry) world.getTileEntity(pos);

		if (chest != null) {
			ChestStomach.gobbleEntityItem(chest.getItemHandler(), entity, world, pos, this, 2, 2);
		}
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return state.getValue(variant) == HungryChestType.TRAPPED;
	}

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (!state.canProvidePower()) {
			return 0;
		} else {
			int i = 0;
			TileEntity tile = blockAccess.getTileEntity(pos);

			if (tile instanceof TileChestHungry) {
				i = ((TileChestHungry) tile).numPlayersUsing;
			}

			return MathHelper.clamp(i, 0, 15);
		}
	}

	@Override
	public int getStrongPower(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.UP ? state.getWeakPower(blockAccess, pos, side) : 0;
	}

	private boolean isBlocked(World world, BlockPos pos) {
		return isBelowSolidBlock(world, pos) || isOcelotSittingOnChest(world, pos);
	}

	private boolean isBelowSolidBlock(World world, BlockPos pos) {
		return world.getBlockState(pos.up()).isSideSolid(world, pos.up(), EnumFacing.DOWN);
	}

	private boolean isOcelotSittingOnChest(World world, BlockPos pos) {
		for (Entity entity : world.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB((double) pos.getX(), (double) (pos.getY() + 1), (double) pos.getZ(), (double) (pos.getX() + 1), (double) (pos.getY() + 2), (double) (pos.getZ() + 1)))) {
			EntityOcelot ocelot = (EntityOcelot) entity;

			if (ocelot.isSitting()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		return InventoryManager.calcRedstone(world.getTileEntity(pos));
	}

	public static enum HungryChestType implements IStringSerializable {

		BASIC, TRAPPED, ENDER;

		@Override
		public String getName() {
			return name().toLowerCase();
		}
	}

	public static class ChestStomach {

		private ChestStomach() {}

		public static void gobbleEntityItem(IItemHandler dest, Entity entity, World world, BlockPos pos, Block eventReciever, int eventID, int eventParam) {
			if (entity instanceof EntityItem && !entity.isDead) {
				EntityItem item = (EntityItem) entity;
				ItemStack stack = item.getItem();
				ItemStack leftovers = InventoryManager.tryInsertItemStackToInventory(dest, stack);

				if (leftovers == null || leftovers.getCount() != stack.getCount()) {
					entity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.25F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
					world.addBlockEvent(pos, eventReciever, eventID, eventParam);
				}

				if (leftovers != null) {
					item.setItem(leftovers);
				} else {
					entity.setDead();
				}
			}
		}
	}
}