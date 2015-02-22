package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumItemSword extends ItemSword
{

	public ElysiumItemSword(ToolMaterial mat) {
		super(mat);
	}
	
	@Override
    public Item setTextureName(String name)
    {
        this.iconString = Elysium.ID + ":" + name;
        return this;
    }
}
