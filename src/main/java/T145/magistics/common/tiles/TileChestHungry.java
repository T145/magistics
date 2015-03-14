package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;

public class TileChestHungry extends TileEntityChest implements ISidedInventory, IWandable {
	public TileChestHungry() {
		func_145976_a("Hungry Chest");
	}

	public TileChestHungry(int type) {
		super(type);
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 1:
			numPlayersUsing = data;
			return true;
		case 2:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return false;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[getSizeInventory()];
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return true;
	}

	public boolean onWanded(EntityPlayer player, int side) {
		if (player.isSneaking() && numPlayersUsing == 0) {
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, side, 2);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			player.worldObj.playSound(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, "thaumcraft:tool", 0.3F, 1.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
			player.swingItem();
			markDirty();
			return true;
		} else
			return false;
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