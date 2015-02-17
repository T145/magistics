package T145.magistics.common.blocks.pillars;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.api.sentry.SentryBehaviorRegistry;
import T145.magistics.common.Magistics;
import T145.magistics.common.tiles.pillars.TilePillarSentry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockPillarSentry extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarSentry(Material mat) {
		super(mat);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TilePillarSentry pillarTile = (TilePillarSentry) world.getTileEntity(x, y, z);

		if (hitY < 1F && !player.isSneaking()) {
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}

		if (hitY == 1F) {
			if (player.isSneaking())
				pillarTile.dropItemFromSlot(0, 1, player);
			else if (player.getCurrentEquippedItem() != null) {
				ItemStack equipped = player.getCurrentEquippedItem();
				ItemStack itemstack = pillarTile.getStackInSlot(0);
				if (itemstack == null) {
					if (SentryBehaviorRegistry.getBehavior(equipped.getItem()) != null) {
						ItemStack in = equipped.copy();
						in.stackSize = 1;
						pillarTile.setOwner(player);

						pillarTile.setInventorySlotContents(0, in);

						if (!player.capabilities.isCreativeMode)
							equipped.stackSize--;
					} else
						Magistics.proxy.warn("[Sentry API] There is no known behavior available for that item!");
				} else if (itemstack.isItemEqual(equipped) && itemstack.stackSize < itemstack.getMaxStackSize()) {
					if (!player.capabilities.isCreativeMode)
						equipped.stackSize--;

					pillarTile.decrStackSize(0, -1);
					pillarTile.setOwner(player);

					pillarTile.onInventoryChanged();
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		if (!world.isRemote) {
			TilePillarSentry workTile = (TilePillarSentry) world.getTileEntity(x, y, z);

			if (workTile.getStackInSlot(0) != null) {
				EntityItem itemDropped = new EntityItem(world, x + 0.1875D, y + 1D, z + 0.1875D, workTile.getStackInSlot(0));
				itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

				if (workTile.getStackInSlot(0).hasTagCompound())
					itemDropped.getEntityItem().setTagCompound((NBTTagCompound) workTile.getStackInSlot(0).getTagCompound().copy());

				world.spawnEntityInWorld(itemDropped);
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityWither)
			return false;
		else if (entity instanceof EntityDragon)
			return false;

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entity, ItemStack is) {
		TileEntity tile = world.getTileEntity(i, j, k);

		if (tile instanceof TilePillarSentry && entity instanceof EntityPlayer) {
			TilePillarSentry sentry = (TilePillarSentry) world.getTileEntity(i, j, k);
			EntityPlayer player = (EntityPlayer) entity;
			sentry.setOwner(player);
		}
		world.setBlockMetadataWithNotify(i, j, k, determineOrientation(world, i, j, k, entity), 0);
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity) {
		return MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TilePillarSentry();
	}
}