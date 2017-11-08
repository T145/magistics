package T145.magistics.tiles.devices;

import com.mojang.authlib.GameProfile;

import T145.magistics.blocks.devices.BlockChestHungry.HungryChestType;
import T145.magistics.lib.managers.InventoryManager;
import T145.magistics.lib.managers.PlayerManager;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageRecieveClientEvent;
import T145.magistics.tiles.base.TileInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileChestHungry extends TileInventory implements ITickable {

	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;
	private HungryChestType type;
	private EnumFacing front = EnumFacing.NORTH;
	private GameProfile owner;

	public TileChestHungry(HungryChestType type) {
		super(27);
		this.type = type;
	}

	public TileChestHungry() {
		this(HungryChestType.BASIC);
	}

	public HungryChestType getType() {
		return type;
	}

	public boolean isEnderChest() {
		return type == HungryChestType.ENDER;
	}

	public EnumFacing getFront() {
		return front;
	}

	public void setFront(EnumFacing side) {
		front = side;
	}

	public EntityPlayer getOwner() {
		return PlayerManager.getPlayerFromWorld(world, owner.getId());
	}

	public GameProfile getOwnerProfile() {
		return owner;
	}

	public void setOwner(EntityPlayer player) {
		if (player != null) {
			owner = player.getGameProfile();
		}
	}

	public boolean isOwned() {
		return owner != null;
	}

	public boolean isOwnedBy(EntityPlayer player) {
		if (isOwned()) {
			return owner.getId().equals(player.getUniqueID());
		}

		return false;
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		super.readCustomNBT(nbt);
		type = HungryChestType.valueOf(nbt.getString("Type"));
		front = EnumFacing.getFront(nbt.getInteger("Front"));
		owner = PlayerManager.profileFromNBT(nbt.getCompoundTag("Owner"));
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		super.writeCustomNBT(nbt);
		nbt.setString("Type", type.toString());
		nbt.setInteger("Front", front.getIndex());

		if (owner != null) {
			nbt.setTag("Owner", PlayerManager.profileToNBT(owner));
		}
	}

	public void sendRecieveEventPacket() {
		PacketHandler.INSTANCE.sendToAllAround(new MessageRecieveClientEvent(pos, 1, numPlayersUsing), PacketHandler.getTargetPoint(world, pos));
	}

	@Override
	public void update() {
		if (++ticksSinceSync % 20 * 4 == 0) {
			sendRecieveEventPacket();
		}

		prevLidAngle = lidAngle;
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		float f = 0.1F;

		if (numPlayersUsing > 0 && lidAngle == 0.0F) {
			double d0 = i + 0.5D;
			double d1 = k + 0.5D;
			world.playSound(null, d0, j + 0.5D, d1, isEnderChest() ? SoundEvents.BLOCK_ENDERCHEST_OPEN : SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
			float f2 = lidAngle;

			if (numPlayersUsing > 0) {
				lidAngle += 0.1F;
			} else {
				lidAngle -= 0.1F;
			}

			if (lidAngle > 1.0F) {
				lidAngle = 1.0F;
			}

			float f1 = 0.5F;

			if (lidAngle < 0.5F && f2 >= 0.5F) {
				double d3 = i + 0.5D;
				double d2 = k + 0.5D;
				world.playSound(null, d3, j + 0.5D, d2, isEnderChest() ? SoundEvents.BLOCK_ENDERCHEST_CLOSE : SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0.0F) {
				lidAngle = 0.0F;
			}
		}

		if (!world.isRemote && isEnderChest() && world.isBlockPowered(pos)) {
			syncWithEnderChest();
		}
	}

	private void syncWithEnderChest() {
		boolean movedSomething = false;

		for (int slot = 0; slot < handle.getSlots(); ++slot) {
			movedSomething = moveStackFromSlotToEnderChest(slot);
		}

		if (movedSomething) {
			world.playSound(null, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
		}
	}

	private boolean moveStackFromSlotToEnderChest(int slot) {
		if (handle.getStackInSlot(slot).isEmpty() || owner == null || owner.getId() == null) {
			return false;
		}

		EntityPlayer player = getOwner();

		if (player == null) {
			return false;
		}

		ItemStack stack = handle.extractItem(slot, 64, false);

		if (stack.isEmpty()) {
			return false;
		}

		int origSize = stack.getCount();
		IItemHandler inv = new InvWrapper(player.getInventoryEnderChest());
		stack = InventoryManager.tryInsertItemStackToInventory(inv, stack);

		if (stack.isEmpty()) {
			return true;
		}

		boolean movedItems = origSize != stack.getCount();
		handle.insertItem(slot, stack, false);

		return movedItems;
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		if (id == 1) {
			numPlayersUsing = data;
			return true;
		} else if (id == 2) {
			if (lidAngle < data / 10F) {
				lidAngle = data / 10F;
			}
			return true;
		}
		return false;
	}

	@Override
	public void invalidate() {
		updateContainingBlockInfo();
		super.invalidate();
	}

	public void openChest(EntityPlayer player) {
		if (!player.isSpectator()) {
			if (numPlayersUsing < 0) {
				numPlayersUsing = 0;
			}

			++numPlayersUsing;
			sendRecieveEventPacket();
			world.notifyNeighborsOfStateChange(pos, blockType, false);

			if (type == HungryChestType.TRAPPED) {
				world.notifyNeighborsOfStateChange(pos.down(), blockType, false);
			}
		}
	}

	public void closeChest(EntityPlayer player) {
		if (!player.isSpectator()) {
			--numPlayersUsing;
			sendRecieveEventPacket();
			world.notifyNeighborsOfStateChange(pos, blockType, false);

			if (type == HungryChestType.TRAPPED) {
				world.notifyNeighborsOfStateChange(pos.down(), blockType, false);
			}
		}
	}
}