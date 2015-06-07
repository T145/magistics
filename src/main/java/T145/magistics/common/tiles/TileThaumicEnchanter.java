package T145.magistics.common.tiles;

import net.minecraft.item.Item;
import thaumcraft.api.TileThaumcraft;

public class TileThaumicEnchanter extends TileThaumcraft {
	private Item book;

	public void setBook(Item spell) {
		book = spell;
	}

	public Item getBook() {
		return book;
	}
}