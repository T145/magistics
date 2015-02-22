package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class ElysiumItemAxe extends ItemAxe
{

	public ElysiumItemAxe(ToolMaterial mat)
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
