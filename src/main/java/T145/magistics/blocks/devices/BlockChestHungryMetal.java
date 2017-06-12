package T145.magistics.blocks.devices;

import T145.magistics.blocks.MBlock;
import T145.magistics.lib.managers.InventoryManager;
import T145.magistics.tiles.devices.TileChestHungryMetal;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChestHungryMetal extends MBlock<IronChestType> {

	public BlockChestHungryMetal() {
		super("chest_hungry_metal", Material.IRON, IronChestType.class);
		setHardness(3.0F);
		setSoundType(SoundType.METAL);
	}

	public static String getSimpleChestName(IronChestType type) {
		return type.name().toLowerCase().replaceAll("[0-9]", "").replaceAll("chest", "");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryMetal(IronChestType.VALUES[meta]);
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
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (IronChestType type : IronChestType.VALUES) {
			if (type.isValidForCreativeMode()) {
				list.add(new ItemStack(itemIn, 1, type.ordinal()));
			}
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return IronChest.ironChestBlock.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		world.notifyBlockUpdate(pos, state, state, 3);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		IronChest.ironChestBlock.onBlockPlacedBy(world, pos, state, placer, stack);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		IronChest.ironChestBlock.breakBlock(world, pos, state);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileChestHungryMetal && entity instanceof EntityItem && !entity.isDead) {
			TileChestHungryMetal chest = (TileChestHungryMetal) tile;
			EntityItem item = (EntityItem) entity;
			ItemStack stack = item.getEntityItem();
			ItemStack leftovers = InventoryManager.tryInsertItemStackToInventory(InventoryManager.getInventory(world, pos, chest.getFacing()), stack);

			if (leftovers == null || leftovers.getCount() != stack.getCount()) {
				entity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.25F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
				world.addBlockEvent(pos, this, 4, 2);
			}

			if (leftovers != null) {
				item.setEntityItemStack(leftovers);
			} else {
				entity.setDead();
			}

			chest.markDirty();
		}
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
		return IronChest.ironChestBlock.getExplosionResistance(world, pos, exploder, explosion);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		return Container.calcRedstone(world.getTileEntity(pos));
	}
}