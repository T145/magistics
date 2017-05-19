package T145.magistics.api.magic;

import net.minecraft.util.EnumFacing;

public interface IQuintManager {

	boolean canConnect(EnumFacing facing);

	int getSuction();

	void setSuction(int suction);
}