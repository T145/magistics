package T145.magistics.common.tiles;

import net.minecraft.tileentity.TileEntity;

public class TileInfuser extends TileEntity {
	public float angle, sucked;

	public boolean isSideConnected(int side) {
		return false;
	}
}