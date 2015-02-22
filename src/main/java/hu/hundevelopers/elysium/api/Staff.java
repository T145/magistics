package hu.hundevelopers.elysium.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Staff
{
	private static Map<Block, Float> throwableBlocks = new HashMap<Block, Float>();

	/**
	 * Getting damage from block when colliding to mobs
	 * @param block
	 * @return
	 */
	public static float getDamageForBlock(Block block)
	{
		Float damage = throwableBlocks.get(block);
		return damage == null ? 0 : damage;
	}
	
	/**
	 * Registering throwable blocks with damage value (Earth Staff)
	 * @param block
	 * @param damage - how much does it hurt
	 */
	public static void registerThrowableBlock(Block block, float damage)
	{
		throwableBlocks.put(block, damage);
	}
	
	public static Block getBlockHolding(ItemStack staff)
    {
    	if(staff.stackTagCompound == null) staff.stackTagCompound = new NBTTagCompound();
    	
    	NBTTagList nbtlist = staff.stackTagCompound.getTagList("Items", 10);
		NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(0);
		
		ItemStack holding = ItemStack.loadItemStackFromNBT(nbtslot);
		
		if(holding == null) return null;
		
		Block block = Block.getBlockFromItem(holding.getItem());
		
		return block;
    }
    
    @SuppressWarnings("unused")
	public static boolean setBlockHolding(ItemStack staff, Block block)
    {
    	if(block != null && getDamageForBlock(block) == 0) return false;
    	NBTTagCompound nbt = staff.stackTagCompound;
		NBTTagList nbtlist = new NBTTagList();
		
		ItemStack projectile = new ItemStack(block);
		
		if(projectile != null)
		{
			NBTTagCompound nbtslot = new NBTTagCompound();
			nbtslot.setByte("Slot", (byte) 0);
			projectile.writeToNBT(nbtslot);
			nbtlist.appendTag(nbtslot);
			
			nbt.setTag("Items", nbtlist);
			return true;
		}
		return false;
    }
}
