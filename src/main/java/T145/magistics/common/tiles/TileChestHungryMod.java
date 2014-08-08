package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.wands.IWandable;

public class TileChestHungryMod extends TileEntity implements IInventory, IWandable {
	public ForgeDirection orientation;
	public int numPlayersUsing;

	public TileChestHungryMod(int meta) {
		meta = super.getBlockMetadata();
	}

	@Override
	public void onUsingWandTick(ItemStack arg0, EntityPlayer arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack onWandRightClick(World arg0, ItemStack arg1,
			EntityPlayer arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onWandRightClick(World arg0, ItemStack arg1, EntityPlayer arg2,
			int arg3, int arg4, int arg5, int arg6, int arg7) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onWandStoppedUsing(ItemStack arg0, World arg1,
			EntityPlayer arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return false;
	}
}