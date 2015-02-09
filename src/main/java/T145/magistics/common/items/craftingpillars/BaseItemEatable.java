package T145.magistics.common.items.craftingpillars;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseItemEatable extends ItemFood
{
	public BaseItemEatable(int heal, float saturation)
	{
		super(heal, saturation, false);
	}

	public BaseItemEatable(int heal)
	{
		this(heal, 0.6F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister itemIcon)
	{
		this.itemIcon = itemIcon.registerIcon("craftingpillars:" + this.getUnlocalizedName().substring(5));
	}
}
