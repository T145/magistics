package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.config.Settings;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.ItemChestChanger;
import cpw.mods.ironchest.TileEntityIronChest;

public class TileChestHungryMetal extends TileEntityIronChest implements IWandable {
	public int numUsingPlayers = (Integer) ReflectionHelper.getPrivateValue(TileEntityIronChest.class, this, "numUsingPlayers");

	public TileChestHungryMetal() {}

	public TileChestHungryMetal(IronChestType type) {
		super(type);
	}

	@Override
	public TileEntityIronChest applyUpgradeItem(ItemChestChanger chestChanger) {
		if (numUsingPlayers > 0 || !chestChanger.getType().canUpgrade(getType()))
			return null;
		TileChestHungryMetal newTile = new TileChestHungryMetal(IronChestType.values()[chestChanger.getTargetChestOrdinal(getType().ordinal())]);
		int newSize = newTile.chestContents.length;
		System.arraycopy(chestContents, 0, newTile.chestContents, 0, Math.min(newSize, chestContents.length));
		BlockChestHungryMetal block = (BlockChestHungryMetal) Settings.blockChestHungryMetal;
		block.dropContent(newSize, this, worldObj, xCoord, yCoord, zCoord);
		newTile.setFacing(getFacing());
		newTile.sortTopStacks();
		ReflectionHelper.setPrivateValue(TileEntityIronChest.class, this, -1, "ticksSinceSync");
		return newTile;
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
			return false;
		}
	}

	public boolean onWanded(EntityPlayer player, int side) {
		if (player.isSneaking()) {
			setFacing(side);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			player.worldObj.playSound(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, "thaumcraft:tool", 0.3F, 1.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
			player.swingItem();
			markDirty();
		}
		return true;
	}

	@Override
	public int onWandRightClick(World world, ItemStack wand, EntityPlayer player, int i, int j, int k, int side, int meta) {
		onWanded(player, side);
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