package T145.magistics.items;

import T145.magistics.Magistics;
import net.minecraft.item.Item;

public class ItemTintedNitor extends Item {
	public static final Item INSTANCE = new ItemTintedNitor();

	public ItemTintedNitor() {
		super();
		setCreativeTab(Magistics.tabMagistics);
		setUnlocalizedName("tinted_nitor");
		setTextureName("magistics:tinted_nitor");
	}
}