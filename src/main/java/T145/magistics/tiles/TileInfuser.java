package T145.magistics.tiles;

public class TileInfuser extends TileRotatable {
	public float burnTime;

	public TileInfuser() {
	}

	public boolean hasConnectedSide(int side) {
		return false;
	}
}