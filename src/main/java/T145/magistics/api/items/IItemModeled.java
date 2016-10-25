package T145.magistics.api.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemModeled {

	@SideOnly(Side.CLIENT)
	public void registerModel();
}