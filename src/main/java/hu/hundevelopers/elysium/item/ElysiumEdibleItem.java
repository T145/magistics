package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ElysiumEdibleItem extends ItemFood
{

	public ElysiumEdibleItem(int heal)
	{
		super(heal, false);
		this.setCreativeTab(Elysium.tabElysium);
	}

	@Override
    public Item setTextureName(String name)
    {
        this.iconString = Elysium.ID + ":" + name;
        return this;
    }
}
