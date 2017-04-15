package T145.magistics.api.magic;

import net.minecraft.util.EnumFacing;

public interface IQuintessenceManager extends IQuintessenceHandler {

	public boolean isContainer();

	public boolean hasConnection(EnumFacing facing);

	public int getSuction();

	public void setSuction(int pressure);
}