package T145.magistics.tiles.devices;

import com.mojang.authlib.GameProfile;

import T145.magistics.api.logic.IFacing;
import T145.magistics.api.logic.IOwned;
import T145.magistics.api.variants.blocks.EnumChestHungry;
import T145.magistics.containers.ContainerChestHungry;
import T145.magistics.lib.managers.InventoryManager;
import T145.magistics.lib.managers.PlayerManager;
import T145.magistics.tiles.MTileInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileChestHungry extends MTileInventory implements IOwned, IFacing, IInteractionObject {

	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;
	private EnumChestHungry type;
	private EnumFacing facing = EnumFacing.NORTH;
	private GameProfile owner;

	public TileChestHungry(EnumChestHungry type) {
		this.type = type;
	}

	public TileChestHungry() {
		this(EnumChestHungry.BASIC);
	}

	public EnumChestHungry getType() {
		return type;
	}

	public boolean isEnderChest() {
		return type == EnumChestHungry.ENDER;
	}

	@Override
	public String getName() {
		return "tile.chest_hungry." + type.getName() + ".name";
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName(), new Object[0]);
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerChestHungry(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "magistics:chestHungry";
	}

	@Override
	public boolean isHorizontalFacing() {
		return true;
	}

	@Override
	public EnumFacing getFacing() {
		return facing;
	}

	@Override
	public void setFacing(EnumFacing side) {
		facing = side;
	}

	@Override
	public EntityPlayer getOwner() {
		return PlayerManager.getPlayerFromWorld(world, owner.getId());
	}

	@Override
	public GameProfile getOwnerProfile() {
		return owner;
	}

	@Override
	public void setOwner(EntityPlayer player) {
		if (player != null) {
			owner = player.getGameProfile();
		}
	}

	@Override
	public boolean isOwned() {
		return owner != null;
	}

	@Override
	public boolean isOwnedBy(EntityPlayer player) {
		if (isOwned()) {
			return owner.getId().equals(player.getUniqueID());
		}

		return false;
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, boolean simulate) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, int amount, boolean simulate) {
		return true;
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		super.readPacketNBT(compound);
		type = EnumChestHungry.valueOf(compound.getString("Type"));
		facing = EnumFacing.getFront(compound.getInteger("Facing"));
		owner = PlayerManager.profileFromNBT(compound.getCompoundTag("Owner"));
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		super.writePacketNBT(compound);
		compound.setString("Type", type.toString());
		compound.setInteger("Facing", facing.getIndex());

		if (owner != null) {
			compound.setTag("Owner", PlayerManager.profileToNBT(owner));
		}
	}

	@Override
	public void update() {
		if (++ticksSinceSync % 20 * 4 == 0) {
			world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
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

		for (int slot = 0; slot < itemHandler.getSlots(); ++slot) {
			movedSomething = moveStackFromSlotToEnderChest(slot);
		}

		if (movedSomething) {
			world.playSound(null, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
		}
	}

	private boolean moveStackFromSlotToEnderChest(int slot) {
		if (itemHandler.getStackInSlot(slot).isEmpty() || owner == null || owner.getId() == null) {
			return false;
		}

		EntityPlayer player = getOwner();

		if (player == null) {
			return false;
		}

		ItemStack stack = itemHandler.extractItem(slot, 64, false);

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
		itemHandler.insertItem(slot, stack, false);

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
			world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
			world.notifyNeighborsOfStateChange(pos, blockType, false);

			if (type == EnumChestHungry.TRAPPED) {
				world.notifyNeighborsOfStateChange(pos.down(), blockType, false);
			}
		}
	}

	public void closeChest(EntityPlayer player) {
		if (!player.isSpectator()) {
			--numPlayersUsing;
			world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
			world.notifyNeighborsOfStateChange(pos, blockType, false);

			if (type == EnumChestHungry.TRAPPED) {
				world.notifyNeighborsOfStateChange(pos.down(), blockType, false);
			}
		}
	}
}