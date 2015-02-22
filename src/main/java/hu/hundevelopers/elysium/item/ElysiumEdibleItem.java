package hu.hundevelopers.elysium.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ElysiumEdibleItem extends ItemFood
{

	public ElysiumEdibleItem(int heal)
	{
		super(heal, false);
	}

	@Override
    public Item setTextureName(String name)
    {
        this.iconString = "elysium:" + name;
        return this;
    }
}
