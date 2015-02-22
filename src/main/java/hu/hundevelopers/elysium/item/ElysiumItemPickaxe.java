package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class ElysiumItemPickaxe extends ItemPickaxe
{

	public ElysiumItemPickaxe(ToolMaterial mat) {
		super(mat);
	}

	@Override
    public Item setTextureName(String name)
    {
        this.iconString = Elysium.ID + ":" + name;
        return this;
    }
}
