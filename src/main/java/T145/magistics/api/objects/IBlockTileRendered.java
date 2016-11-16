package T145.magistics.api.objects;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockTileRendered extends IBlockTiled {

	@SideOnly(Side.CLIENT)
	public void registerRenderer();
}