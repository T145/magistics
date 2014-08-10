package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.wands.IWandable;
import T145.magistics.common.blocks.BlockChestHungryMod;

public class TileChestHungryMod extends TileEntity implements IInventory, IWandable {
	public float lidAngle, prevLidAngle;
	public int numPlayersUsing, ticksSinceSync;
	public ForgeDirection orientation;
	public ItemStack chestContents[] = new ItemStack[getSizeInventory()];
	private BlockChestHungryMod.Types types;

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (++ticksSinceSync % 20 * 4 == 0)
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, super.getBlockType(), 1, numPlayersUsing);

		prevLidAngle = lidAngle;

		if (numPlayersUsing > 0 && lidAngle == 0.0F)
			worldObj.playSoundEffect((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);

		if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
			if (numPlayersUsing > 0)
				lidAngle += 0.1F;
			else
				lidAngle -= 0.1F;

			if (lidAngle > 1.0F)
				lidAngle = 1.0F;
			if (lidAngle < 0.5F && lidAngle >= 0.5F)
				worldObj.playSoundEffect((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			if (lidAngle < 0.0F)
				lidAngle = 0.0F;
		}
	}

	@Override
	public boolean receiveClientEvent(int eventID, int recievedData) {
		switch (eventID) {
		case 0:
			numPlayersUsing = recievedData;
			return true;
		case 1:
			if (lidAngle < recievedData / 10.0F)
				lidAngle = recievedData / 10.0F;
			return true;
		default:
			return super.receiveClientEvent(eventID, recievedData);
		}
	}

	@Override
	public void invalidate() {
		updateContainingBlockInfo();
		super.invalidate();
	}

	@Override
	public void onUsingWandTick(ItemStack is, EntityPlayer player, int arg2) {}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack is, EntityPlayer player) {
		return null;
	}

	@Override
	public int onWandRightClick(World world, ItemStack is, EntityPlayer player, int i, int j, int k, int side, int meta) {
		orientation = ForgeDirection.getOrientation(side);
		world.playSound(i + 0.5, j + 0.5, k + 0.5, "thaumcraft:tool", 0.3F, 1.9F + world.rand.nextFloat() * 0.2F, false);
		world.markBlockForUpdate(i, j, k);
		player.swingItem();
		return 0;
	}

	@Override
	public void onWandStoppedUsing(ItemStack is, World world, EntityPlayer player, int arg3) {}

	@Override
	public int getSizeInventory() {
		switch (super.getBlockMetadata()) {
		case 4:
			return 54;
		case 5:
			return 81;
		case 6:
			return 108;
		case 7:
			return 45;
		case 8:
			return 72;
		case 9:
			return 108;
		case 10:
			return 108;
		case 11:
			return 1;
		default:
			return 27;
		}
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return chestContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot_from, int slot_to) {
		if (chestContents[slot_from] != null) {
			ItemStack is;

			if (chestContents[slot_from].stackSize <= slot_to) {
				is = chestContents[slot_from];
				chestContents[slot_from] = null;
				markDirty();
				return is;
			} else {
				is = chestContents[slot_from].splitStack(slot_to);

				if (chestContents[slot_from].stackSize == 0)
					chestContents[slot_from] = null;

				markDirty();
				return is;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (chestContents[slot] != null) {
			ItemStack is = chestContents[slot];
			chestContents[slot] = null;
			return is;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is) {
		chestContents[slot] = is;

		if (is != null && is.stackSize > getInventoryStackLimit())
			is.stackSize = getInventoryStackLimit();

		markDirty();
	}

	@Override
	public String getInventoryName() {
		return "Hungry " + types.values()[super.getBlockMetadata()] + " Chest";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbtlist = nbt.getTagList("Items", 10);

		for (int i = 0; i < nbtlist.tagCount(); ++i) {
			NBTTagCompound slotMeta = nbtlist.getCompoundTagAt(i);
			int j = slotMeta.getByte("Slot") & 255;

			if (j >= 0 && j < chestContents.length)
				chestContents[j] = ItemStack.loadItemStackFromNBT(slotMeta);
		}

		orientation = ForgeDirection.getOrientation(nbt.getInteger("orientation"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbtlist = new NBTTagList();

		for (int i = 0; i < chestContents.length; ++i)
			if (chestContents[i] != null) {
				NBTTagCompound slotMeta = new NBTTagCompound();
				slotMeta.setByte("Slot", (byte) i);
				chestContents[i].writeToNBT(slotMeta);
				nbtlist.appendTag(slotMeta);
			}

		nbt.setTag("Items", nbtlist);
		nbt.setInteger("orientation", orientation.ordinal());
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
		++numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, super.getBlockType(), 1, numPlayersUsing);
	}

	@Override
	public void closeInventory() {
		--numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, super.getBlockType(), 1, numPlayersUsing);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return super.getBlockMetadata() == types.dirt.ordinal() ? is.getItem() == Item.getItemFromBlock(Blocks.dirt) ? true : false : true;
	}
}