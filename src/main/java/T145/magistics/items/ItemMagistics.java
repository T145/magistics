package T145.magistics.items;

import T145.magistics.Magistics;
import net.minecraft.item.Item;

public class ItemMagistics extends Item {

	protected String name;

	public ItemMagistics(String name) {
		this.name = name;

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Magistics.tab);
	}

	public void registerItemModel() {
		Magistics.proxy.registerItemRenderer(this, 0, name);
	}
}