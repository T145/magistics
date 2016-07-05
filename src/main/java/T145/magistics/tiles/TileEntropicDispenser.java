package T145.magistics.tiles;

import net.minecraft.block.BlockSourceImpl;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import T145.magistics.blocks.BlockEntropicDispenser;

public class TileEntropicDispenser extends TileEntityDispenser {
	private int ticksExpired = 0;
	private int ticksMax = 4;

	public String getInventoryName() {
		return hasCustomInventoryName() ? field_146020_a : "Entropic Dispenser";
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			return;
		}

		ticksExpired += 1;

		if (ticksExpired < ticksMax) {
			return;
		}

		ticksExpired = 0;
		triggerDispense(worldObj, xCoord, yCoord, zCoord);
	}

	protected void triggerDispense(World world, int x, int y, int z) {
		if ((world.getBlockMetadata(x, y, z) & 0x8) != 0) {
			return;
		}

		BlockSourceImpl dispenser = new BlockSourceImpl(world, x, y, z);
		TileEntropicDispenser tile = (TileEntropicDispenser) dispenser.getBlockTileEntity();

		if (tile != null) {
			int slotWithItem = tile.func_146017_i();

			// TODO: fill w/ items from neighboring inventories

			// dispense the items
			if (slotWithItem >= 0) {
				ItemStack target = tile.getStackInSlot(slotWithItem);
				int meta = Math.max(world.getBlockMetadata(x, y, z) - 1, 0) & 0x7;
				IInventory inv = TileEntityHopper.func_145893_b(world, x + net.minecraft.util.Facing.offsetsXForSide[meta], y + net.minecraft.util.Facing.offsetsYForSide[meta], z + net.minecraft.util.Facing.offsetsZForSide[meta]);
				ItemStack dispensed;

				if (inv != null) {
					dispensed = TileEntityHopper.func_145889_a(inv, target.copy().splitStack(1), net.minecraft.util.Facing.oppositeSide[meta]);

					if (dispensed == null) {
						dispensed = target.copy();

						if (--dispensed.stackSize == 0) {
							dispensed = null;
						}
					} else {
						dispensed = target.copy();
					}
				} else {
					dispensed = dispenseStack(dispenser, target);
					dispenser.getWorld().playAuxSFX(1000, dispenser.getXInt(), dispenser.getYInt(), dispenser.getZInt(), 0);
					dispenser.getWorld().playAuxSFX(2000, dispenser.getXInt(), dispenser.getYInt(), dispenser.getZInt(), getFrontOffset(BlockEntropicDispenser.getFrontSide(Math.max(dispenser.getBlockMetadata() - 1, 0))));

					if ((dispensed != null) && (dispensed.stackSize == 0)) {
						dispensed = null;
					}
				}

				tile.setInventorySlotContents(slotWithItem, dispensed);
			}
		}
	}

	protected ItemStack dispenseStack(IBlockSource block, ItemStack target) {
		doDispense(block.getWorld(), target.splitStack(1), BlockEntropicDispenser.getFrontSide(Math.max(block.getBlockMetadata() - 1, 0)), BlockEntropicDispenser.getPosition(block));
		return target;
	}

	public static void doDispense(World world, ItemStack target, EnumFacing facing, IPosition position) {
		double xx = position.getX();
		double yy = position.getY();
		double zz = position.getZ();

		if (facing == EnumFacing.DOWN) {
			EntityItem entityitem = new EntityItem(world, xx, yy - 0.1D, zz, target);
			entityitem.motionX = 0.0D;
			entityitem.motionY = 0.0D;
			entityitem.motionZ = 0.0D;
			world.spawnEntityInWorld(entityitem);
		} else if (facing == EnumFacing.UP) {
			EntityItem entityitem = new EntityItem(world, xx, yy + 0.1D, zz, target);
			entityitem.motionX = facing.getFrontOffsetX() * 0.3D;
			entityitem.motionY = 0.10000000298023223D;
			entityitem.motionZ = facing.getFrontOffsetZ() * 0.3D;
			world.spawnEntityInWorld(entityitem);
		} else {
			EntityItem entityitem = new EntityItem(world, xx, yy - 0.1D, zz, target);
			entityitem.motionX = facing.getFrontOffsetX() * 0.3D;
			entityitem.motionY = 0.20000000298023224D;
			entityitem.motionZ = facing.getFrontOffsetZ() * 0.3D;
			world.spawnEntityInWorld(entityitem);
		}
	}

	private int getFrontOffset(EnumFacing facing) {
		return facing.getFrontOffsetX() + 1 + (facing.getFrontOffsetZ() + 1) * 3;
	}
}