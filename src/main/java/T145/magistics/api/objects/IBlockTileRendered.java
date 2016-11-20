package T145.magistics.api.objects;

import net.minecraft.block.ITileEntityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockTileRendered extends ITileEntityProvider {

	@SideOnly(Side.CLIENT)
	public void registerRenderer();
}