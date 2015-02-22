package hu.hundevelopers.elysium.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;

public class ElysiumItemHoe extends ItemHoe
{

	public ElysiumItemHoe(ToolMaterial mat) {
		super(mat);
	}

	@Override
    public Item setTextureName(String name)
    {
        this.iconString = "elysium:" + name;
        return this;
    }
}
