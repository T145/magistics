package T145.magistics.api.items.baubles;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Baubles
{
	public static Map<Item, IMod> mods = new HashMap<Item, IMod>();

	public static void addModifier(Item item, IMod mod)
	{
		mods.put(item, mod);
	}
	
	public static IMod getModifier(Item item)
	{
		return mods.get(item);
	}

	public static IMod getModifier(ItemStack item)
	{
		return getModifier(item.getItem());
	}
	
	public static ItemStack getGemInRing(int slot, ItemStack ring)
	{

		if(ring == null) return null;
		if(ring.getItem() == null) return null;
		if(ring.stackTagCompound == null) return null;
		
		ItemStack mod = null;
		NBTTagList nbtlist = ring.stackTagCompound.getTagList("Items", 10);
		for(int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if((j >= 0) && (j < 4))
			{
				mod = ItemStack.loadItemStackFromNBT(nbtslot);
	        	if(j == slot) return mod;
			}
		}
		
		return null;
	}

	public static boolean isGemInRing(Item gem, ItemStack ring)
	{
		if(ring == null) return false;
		if(ring.getItem() == null) return false;
		if(gem == null) return false;
		
		for(int i = 0; i < 4; i++)
		{
			ItemStack item = getGemInRing(i, ring);
			if(item != null && item.getItem().equals(gem))
			{
				return true;
			}
		}
		return false;
	}
	
}
