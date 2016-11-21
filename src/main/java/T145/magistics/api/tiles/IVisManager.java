package T145.magistics.api.tiles;

import net.minecraft.util.EnumFacing;

public interface IVisManager {

	public boolean getConnectable(EnumFacing facing);

	public int getVisSuction();
	public int getMiasmaSuction();
	public int getSuction();

	public void setVisSuction(int pressure);
	public void setMiasmaSuction(int pressure);
	public void setSuction(int pressure);
}