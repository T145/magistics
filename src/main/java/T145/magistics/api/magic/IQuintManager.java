package T145.magistics.api.magic;

import net.minecraft.util.EnumFacing;

public interface IQuintManager {

	public boolean canConnect(EnumFacing facing);

	public int getSuction();

	public void setSuction(int suction);
}