package T145.magistics.tiles;

public class TileInfuserDark extends TileInfuser {
	@Override
	public boolean isDark() {
		return true;
	}

	public int getDarkCookProgressScaled(int time) {
		return 0;
	}

	@Override
	public String getInventoryName() {
		return "Dark Infuser";
	}
}