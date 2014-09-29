package T145.magistics.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.config.MagisticsConfig;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.ItemChestChanger;
import cpw.mods.ironchest.TileEntityIronChest;

public class TileChestHungryMetal extends TileEntityIronChest {
	public int numPlayersUsing = (Integer) ReflectionHelper.getPrivateValue(TileEntityIronChest.class, this, "numUsingPlayers");

	public TileChestHungryMetal(IronChestType type) {
		super(type);
	}

	@Override
	public TileEntityIronChest applyUpgradeItem(ItemChestChanger itemChestChanger) {
		if (numPlayersUsing > 0 || !itemChestChanger.getType().canUpgrade(getType()))
			return null;
		TileChestHungryMetal newEntity = new TileChestHungryMetal(IronChestType.values()[itemChestChanger.getTargetChestOrdinal(getType().ordinal())]);

		int newSize = newEntity.chestContents.length;
		System.arraycopy(chestContents, 0, newEntity.chestContents, 0, Math.min(newSize, chestContents.length));
		BlockChestHungryMetal block = (BlockChestHungryMetal) MagisticsConfig.blocks[1];
		block.dropContent(newSize, this, worldObj, xCoord, yCoord, zCoord);
		newEntity.setFacing(getFacing());
		newEntity.sortTopStacks();
		ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, -1, "ticksSinceSync");
		return newEntity;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldRenderInPass(int pass) {
		return pass == 0 || pass == 1;
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

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		IronChestType type = IronChestType.values()[compound.getByte("type")];
		if (type != getType())
			ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, type, "type");
		chestContents = new ItemStack[getSizeInventory()];
		super.readFromNBT(compound);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setByte("type", (byte) getType().ordinal());
		super.writeToNBT(compound);
	}

	@Override
	public boolean receiveClientEvent(int eventID, int recievedData) {
		switch (eventID) {
		case 2:
			if (lidAngle < recievedData / 10.0F)
				lidAngle = recievedData / 10.0F;
			return true;
		default:
			return tileEntityInvalid;
		}
	}
}