package T145.magistics.common.tiles.craftingpillars;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.IPlantable;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.world.gen.WorldGenChristmasTree;

public class TilePillarPot extends TileBase implements IInventory, ISidedInventory {
	private ItemStack[] inventory = new ItemStack[getSizeInventory()];

	public boolean showNum = false;

	public void onBlockUpdate(Random rand) {
		if (rand.nextInt(3) == 0 && getStackInSlot(0) != null && getStackInSlot(0).getItem() == Item.getItemFromBlock(ConfigObjects.blockChristmasTreeSapling)) {
			new WorldGenChristmasTree(false).generate(worldObj, rand, xCoord, yCoord, zCoord);
			decrStackSize(0, 1);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		inventory = new ItemStack[getSizeInventory()];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);
		for (int i = 0; i < nbtlist.tagCount(); i++) {
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if ((j >= 0) && (j < getSizeInventory()))
				inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}

		showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) {
			if (inventory[i] != null) {
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}
		nbt.setTag("Items", nbtlist);
		nbt.setBoolean("showNum", showNum);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);

		if (worldObj.isRemote) {
			Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
			worldObj.func_147451_t(xCoord, yCoord, zCoord);
		}
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();

		if (!worldObj.isRemote)
			Magistics.proxy.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 64);
	}

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player) {
		if (worldObj.isRemote)
			return;

		if (getStackInSlot(slot) != null) {
			EntityItem itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(decrStackSize(slot, amount));
			worldObj.spawnEntityInWorld(itemEntity);

			onInventoryChanged();
		}
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = null;

		if (inventory[slot] != null) {
			if (inventory[slot].stackSize <= amount) {
				stack = inventory[slot];
				inventory[slot] = null;
				onInventoryChanged();
			} else {
				stack = inventory[slot].splitStack(amount);

				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;

				onInventoryChanged();
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
			setInventorySlotContents(slot, null);

		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public String getInventoryName() {
		return "Pot Pillar";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack is) {
		if (is.getItem() instanceof ItemBlock)
			return Block.getBlockFromItem(is.getItem()) instanceof IPlantable;
		else
			return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return (inventory[0] == null || inventory[0].stackSize == 0) && isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return super.isUseableByPlayer(player);
	}
}