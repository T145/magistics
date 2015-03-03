package T145.magistics.common.lib.events;

import net.minecraft.item.ItemStack;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.common.IFuelHandler;

public class ElysiumFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.getItem() == ConfigObjects.itemSulphur)
		{
			return 1600;
		}
		
		return 0;
	}

}
