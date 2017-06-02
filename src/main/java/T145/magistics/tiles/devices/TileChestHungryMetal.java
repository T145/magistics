package T145.magistics.tiles.devices;

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
		return super.receiveClientEvent(id, data);
	}

	@Override
	public void openInventory(EntityPlayer player) {
		if (world == null) {
			return;
		}

		numPlayersUsing++;
		world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		if (world == null) {
			return;
		}

		numPlayersUsing--;
		world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
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
			world.addBlockEvent(pos, blockType, 3, ((numPlayersUsing << 3) & 0xF8) | (getFacing().ordinal() & 0x7));
		}

		super.update();
	}

	@Override
	public void rotateAround() {
		setFacing(getFacing().rotateY());
		world.addBlockEvent(pos, blockType, 2, getFacing().ordinal());
	}
}