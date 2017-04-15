package T145.magistics.tiles.machines;

import T145.magistics.api.magic.IQuintessenceManager;
import T145.magistics.tiles.TileMagisticsInventory;
import net.minecraft.util.EnumFacing;

public class TileInfuser extends TileMagisticsInventory implements IQuintessenceManager {

	public TileInfuser() {
		super(8);
	}

	public TileInfuser(int invSize) {
		super(invSize);
	}

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
}