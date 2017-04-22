package T145.magistics.tiles.storage;

public class TileTankReinforced extends TileTank {

	@Override
	public boolean isReinforced() {
		return false;
	}

	@Override
	public float getMaxQuintessence() {
		return 1000F;
	}
}