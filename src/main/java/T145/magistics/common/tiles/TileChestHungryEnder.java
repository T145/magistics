package T145.magistics.common.tiles;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;

public class TileChestHungryEnder extends TileEntityEnderChest implements IWandable, ISidedInventory {
	public String owner = "";
	public ArrayList<String> accessList = new ArrayList<String>();
	public boolean safeToRemove = false;

	public InventoryEnderChest getEnderInventory() {
		EntityPlayer master = worldObj.getPlayerEntityByName(owner);
		return master == null ? null : master.getInventoryEnderChest();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		owner = nbt.getString("owner");
		NBTTagList tags = nbt.getTagList("access", 10);
		int tagCount = tags.tagCount();
		accessList = new ArrayList<String>();

		for (int i = 0; i < tagCount; i++) {
			NBTTagCompound tag = tags.getCompoundTagAt(i);
			accessList.add(tag.getString("name"));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("owner", owner);
		NBTTagList tags = new NBTTagList();
		int tagCount = tags.tagCount();
		for (tagCount = 0; tagCount < accessList.size(); tagCount++) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("name", (String) accessList.get(tagCount));
			tags.appendTag(tag);
		}
		nbt.setTag("access", tags);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("owner", owner);
		return (Packet) new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		owner = packet.func_148857_g().getString("owner");
	}

	@Override
	public boolean receiveClientEvent(int eventID, int recievedData) {
		switch (eventID) {
		case 1:
			field_145973_j = recievedData;
			return true;
		case 2:
			if (field_145972_a < recievedData / 10F)
				field_145972_a = recievedData / 10F;
			return true;
		default:
			return tileEntityInvalid;
		}
	}

	@Override
	public void func_145969_a() {
		++field_145973_j;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, field_145973_j);
	}

	@Override
	public void func_145970_b() {
		--field_145973_j;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, field_145973_j);
	}

	@Override
	public int onWandRightClick(World world, ItemStack wand, EntityPlayer user, int i, int j, int k, int side, int meta) {
		if (user.isSneaking() && user.getCommandSenderName().equals(owner)) {
			world.setBlockMetadataWithNotify(i, j, k, side, 2);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			user.worldObj.playSound(i + 0.5, j + 0.5, k + 0.5, "thaumcraft:tool", 0.3F, 1.9F + user.worldObj.rand.nextFloat() * 0.2F, false);
			user.swingItem();
			markDirty();
		}
		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wand, EntityPlayer player) {
		return null;
	}

	@Override
	public void onUsingWandTick(ItemStack wand, EntityPlayer player, int count) {}

	@Override
	public void onWandStoppedUsing(ItemStack wand, World world, EntityPlayer player, int count) {}

	@Override
	public int getSizeInventory() {
		return getEnderInventory().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return getEnderInventory().getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slotFrom, int slotTo) {
		return getEnderInventory().decrStackSize(slotFrom, slotTo);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return getEnderInventory().getStackInSlotOnClosing(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is) {
		getEnderInventory().setInventorySlotContents(slot, is);
	}

	@Override
	public String getInventoryName() {
		return getEnderInventory().getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return getEnderInventory().hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getCommandSenderName() == owner ? true : false;
	}

	@Override
	public void openInventory() {
		getEnderInventory().openInventory();
	}

	@Override
	public void closeInventory() {
		getEnderInventory().closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return getEnderInventory().isItemValidForSlot(slot, is);
	}

	public static int[] createSlotArray(int first, int count) {
		int[] slots = new int[count];
		for (int k = first; k < first + count; k++)
			slots[k - first] = k;
		return slots;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return createSlotArray(0, 10);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return true;
	}
}