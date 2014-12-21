package T145.magistics.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.config.Settings;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.ItemChestChanger;
import cpw.mods.ironchest.TileEntityIronChest;

public class TileChestHungryMetal extends TileEntityIronChest {
	public int numUsingPlayers = (Integer) ReflectionHelper.getPrivateValue(TileEntityIronChest.class, this, "numUsingPlayers");

	public TileChestHungryMetal() {}

	public TileChestHungryMetal(IronChestType type) {
		super(type);
	}

	@Override
	public TileEntityIronChest applyUpgradeItem(ItemChestChanger chestChanger) {
		if (numUsingPlayers > 0 || !chestChanger.getType().canUpgrade(getType()))
			return null;
		TileChestHungryMetal newEntity = new TileChestHungryMetal(IronChestType.values()[chestChanger.getTargetChestOrdinal(getType().ordinal())]);

		// Copy stacks and remove old stacks
		int newSize = newEntity.chestContents.length;
		System.arraycopy(chestContents, 0, newEntity.chestContents, 0, Math.min(newSize, chestContents.length));
		BlockChestHungryMetal block = (BlockChestHungryMetal) Settings.blockChestHungryMetal;
		block.dropContent(newSize, this, worldObj, xCoord, yCoord, zCoord);

		// Set facing, sort and reset syncTick
		newEntity.setFacing(getFacing());
		newEntity.sortTopStacks();
		ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, -1, "ticksSinceSync");
		return newEntity;
	}

	@Override
	public TileEntityIronChest updateFromMetadata(int l) {
		if (worldObj != null && worldObj.isRemote)
			if (l != getType().ordinal()) {
				worldObj.setTileEntity(xCoord, yCoord, zCoord, new TileChestHungryMetal(IronChestType.values()[l]));
				return (TileEntityIronChest) worldObj.getTileEntity(xCoord, yCoord, zCoord);
			}
		return this;
	}

	public void fixType(IronChestType type) {
		if (type != getType())
			ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, type, "type");
		chestContents = new ItemStack[getSizeInventory()];
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		fixType(IronChestType.values()[nbt.getByte("type")]);
		super.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setByte("type", (byte) getType().ordinal());
		super.writeToNBT(nbt);
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 2:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return super.receiveClientEvent(id, data);
		}
	}
}