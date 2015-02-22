package hu.hundevelopers.elysium.event;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class ElysiumFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.getItem() == Elysium.itemSulphur)
		{
			return 1600;
		}
		
		return 0;
	}

}
