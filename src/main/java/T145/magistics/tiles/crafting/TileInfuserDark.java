package T145.magistics.tiles.crafting;

public class TileInfuserDark extends TileInfuser {

	@Override
	public boolean isDark() {
		return true;
	}

	@Override
	public String getName() {
		return "tile.infuser.dark.name";
	}

	@Override
	public String getGuiID() {
		return "magistics:infuserDark";
	}

	@Override
	public int getSizeInventory() {
		return 6;
	}
}