package T145.magistics.api.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;

public interface IBlockTile extends ITileEntityProvider {

	public TileEntity getTile(int meta);
}