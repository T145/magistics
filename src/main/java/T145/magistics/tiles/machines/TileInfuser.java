package T145.magistics.tiles.machines;

import T145.magistics.api.magic.IQuintessenceManager;
import T145.magistics.tiles.TileMagistics;
import net.minecraft.util.EnumFacing;

public class TileInfuser extends TileMagistics implements IQuintessenceManager {

	public float cookTime;

	@Override
	public float getQuintessence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setQuintessence(float amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCorrupt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasConnection(EnumFacing facing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSuction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSuction(int pressure) {
		// TODO Auto-generated method stub

	}

	public boolean isCrafting() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getDiskAngle() {
		// TODO Auto-generated method stub
		return 0;
	}
}