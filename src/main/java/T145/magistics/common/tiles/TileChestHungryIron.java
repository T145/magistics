package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;
import T145.magistics.common.blocks.BlockChestHungryIron;
import T145.magistics.common.config.Settings;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.ItemChestChanger;
import cpw.mods.ironchest.TileEntityIronChest;

public class TileChestHungryIron extends TileEntityIronChest implements IWandable {
	int playersUsing = (Integer) ReflectionHelper.getPrivateValue(TileEntityIronChest.class, this, "numUsingPlayers");

	public TileChestHungryIron(IronChestType type) {
		super(type);
	}

	@Override
	public TileEntityIronChest applyUpgradeItem(ItemChestChanger itemChestChanger) {
		if (playersUsing > 0 || !itemChestChanger.getType().canUpgrade(getType()))
			return null;
		TileChestHungryIron newEntity = new TileChestHungryIron(IronChestType.values()[itemChestChanger.getTargetChestOrdinal(getType().ordinal())]);

		// Copy stacks and remove old stacks
		int newSize = newEntity.chestContents.length;
		System.arraycopy(chestContents, 0, newEntity.chestContents, 0, Math.min(newSize, chestContents.length));
		BlockChestHungryIron block = (BlockChestHungryIron) Settings.blockChestHungryIron;
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
				worldObj.setTileEntity(xCoord, yCoord, zCoord, new TileChestHungryIron(IronChestType.values()[l]));
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
	public void readFromNBT(NBTTagCompound compound) {
		fixType(IronChestType.values()[compound.getByte("type")]);
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
			return false;
		}
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int meta) {
		if (player.isSneaking() && playersUsing == 0) {
			setFacing(side);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			player.worldObj.playSound(x + 0.5, y + 0.5, z + 0.5, "thaumcraft:tool", 0.3F, 1.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
			player.swingItem();
			markDirty();
		}
		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
		return null;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
}
