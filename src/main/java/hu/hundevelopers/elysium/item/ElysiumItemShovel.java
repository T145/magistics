package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class ElysiumItemShovel extends ItemSpade
{
	public ElysiumItemShovel(ToolMaterial mat)
	{
		super(mat);
	}

	@Override
    public Item setTextureName(String name)
    {
        this.iconString = Elysium.ID + ":" + name;
        return this;
    }
}
