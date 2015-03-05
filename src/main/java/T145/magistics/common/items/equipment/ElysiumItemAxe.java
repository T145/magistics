package T145.magistics.common.items.equipment;

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
        this.iconString = "elysium:" + name;
        return this;
    }
}
