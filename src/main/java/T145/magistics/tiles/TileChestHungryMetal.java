package T145.magistics.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import T145.magistics.blocks.BlockChestHungryMetal;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.ItemChestChanger;
import cpw.mods.ironchest.TileEntityIronChest;

public class TileChestHungryMetal extends TileEntityIronChest {
	int numUsingPlayers = (Integer) ReflectionHelper.getPrivateValue(TileEntityIronChest.class, this, "numUsingPlayers");

	public TileChestHungryMetal() {}

	public TileChestHungryMetal(IronChestType type) {
		super(type);
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		if (id == 1) {
			numUsingPlayers = data;
			return true;
		}

		if (id == 2) {
			if (lidAngle < data / 10F) {
				lidAngle = data / 10F;
			}
			return true;
		}

		return tileEntityInvalid;
	}

	@Override
	public TileEntityIronChest applyUpgradeItem(ItemChestChanger itemChestChanger) {
		if (numUsingPlayers > 0 || !itemChestChanger.getType().canUpgrade(getType()))
			return null;

		TileChestHungryMetal chest = new TileChestHungryMetal(IronChestType.values()[itemChestChanger.getTargetChestOrdinal(getType().ordinal())]);

		int invSize = chest.chestContents.length;
		System.arraycopy(chestContents, 0, chest.chestContents, 0, Math.min(invSize, chestContents.length));
		((BlockIronChest) BlockChestHungryMetal.INSTANCE).dropContent(invSize, this, worldObj, xCoord, yCoord, zCoord);

		chest.setFacing(getFacing());
		chest.sortTopStacks();
		ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, -1, "ticksSinceSync");

		return chest;
	}

	@Override
	public TileEntityIronChest updateFromMetadata(int l) {
		if (worldObj != null && worldObj.isRemote) {
			if (l != getType().ordinal()) {
				worldObj.setTileEntity(xCoord, yCoord, zCoord, new TileChestHungryMetal(IronChestType.values()[l]));
				return (TileEntityIronChest) worldObj.getTileEntity(xCoord, yCoord, zCoord);
			}
		}

		return this;
	}

	public void fixType(IronChestType type) {
		if (type != getType()) {
			ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, type, "type");
		}

		chestContents = new ItemStack[getSizeInventory()];
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		fixType(IronChestType.values()[compound.getByte("type")]);
		super.readFromNBT(compound);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setByte("type", (byte) getType().ordinal());
		super.writeToNBT(compound);
	}
}