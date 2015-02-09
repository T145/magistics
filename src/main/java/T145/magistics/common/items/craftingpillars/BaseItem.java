package T145.magistics.common.items.craftingpillars;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseItem extends Item
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister itemIcon)
	{
		this.itemIcon = itemIcon.registerIcon("craftingpillars:" + this.getUnlocalizedName().substring(5));
	}
}
