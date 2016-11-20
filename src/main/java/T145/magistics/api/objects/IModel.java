package T145.magistics.api.objects;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModel {

	@SideOnly(Side.CLIENT)
	public void registerModel();
}