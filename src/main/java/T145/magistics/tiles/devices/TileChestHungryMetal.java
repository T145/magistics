package T145.magistics.tiles.devices;

import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageRecieveClientEvent;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class TileChestHungryMetal extends TileEntityIronChest {

	public TileChestHungryMetal() {
		super();
	}

	public TileChestHungryMetal(IronChestType type) {
		super(type);
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		if (id == 4) {
			if (lidAngle < data / 10F) {
				lidAngle = data / 10F;
			}
			return true;
		}
		return false;
	}

	public void sendRecieveEventPacket() {
		PacketHandler.INSTANCE.sendToAllAround(new MessageRecieveClientEvent(pos, 1, numPlayersUsing), PacketHandler.getTargetPoint(world, pos));
	}

	@Override
	public void openInventory(EntityPlayer player) {
		if (world == null) {
			return;
		}

		numPlayersUsing++;
		sendRecieveEventPacket();
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		if (world == null) {
			return;
		}

		numPlayersUsing--;
		sendRecieveEventPacket();
	}

	@Override
	public IronChestType getType() {
		return ReflectionHelper.getPrivateValue(TileEntityIronChest.class, this, "chestType");
	}

	public void fixType(IronChestType type) {
		if (type != getType()) {
			ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, type, "chestType");
		}

		chestContents = NonNullList.<ItemStack> withSize(getSizeInventory(), ItemStack.EMPTY);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		fixType(IronChestType.values()[compound.getByte("chestType")]);
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setByte("chestType", (byte) getType().ordinal());
		return super.writeToNBT(compound);
	}

	@Override
	public void update() {
		if (world != null && !world.isRemote && (int) ReflectionHelper.getPrivateValue(TileEntityIronChest.class, this, "ticksSinceSync") < 0) {
			PacketHandler.INSTANCE.sendToAllAround(new MessageRecieveClientEvent(pos, 3, ((numPlayersUsing << 3) & 0xF8) | (getFacing().ordinal() & 0x7)), PacketHandler.getTargetPoint(world, pos));
		}

		super.update();
	}

	@Override
	public void rotateAround() {
		setFacing(getFacing().rotateY());
		PacketHandler.INSTANCE.sendToAllAround(new MessageRecieveClientEvent(pos, 2, getFacing().ordinal()), PacketHandler.getTargetPoint(world, pos));
	}
}