package T145.magistics.common.tiles;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;

public class TileChestHungryEnder extends TileEntityEnderChest implements IWandable {
	public String owner = "";
	public ArrayList<String> accessList = new ArrayList<String>();
	public boolean safeToRemove = false;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readCustomNBT(nbt);
		owner = nbt.getString("owner");
		NBTTagList tags = nbt.getTagList("access", 10);
		int tagCount = tags.tagCount();
		accessList = new ArrayList<String>();

		for (int i = 0; i < tagCount; i++) {
			NBTTagCompound tag = tags.getCompoundTagAt(i);
			accessList.add(tag.getString("name"));
		}
	}

	public void readCustomNBT(NBTTagCompound nbt) {
		owner = nbt.getString("owner");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeCustomNBT(nbt);
		NBTTagList tags = new NBTTagList();
		int tagCount = tags.tagCount();
		for (tagCount = 0; tagCount < accessList.size(); tagCount++) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("name", (String) accessList.get(tagCount));
			tags.appendTag(tag);
		}
		nbt.setTag("access", tags);
	}

	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setString("owner", owner);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeCustomNBT(nbt);
		return (Packet) new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readCustomNBT(packet.func_148857_g());
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
}