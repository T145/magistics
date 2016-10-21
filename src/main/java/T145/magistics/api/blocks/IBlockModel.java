package T145.magistics.api.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockModel {

	@SideOnly(Side.CLIENT)
	public void registerModel();
}