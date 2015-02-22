package hu.hundevelopers.elysium.item;

import net.minecraft.item.Item;

public class ElysiumItem extends Item
{
	@Override
    public Item setTextureName(String name)
    {
        this.iconString = "elysium:" + name;
        return this;
    }
}
