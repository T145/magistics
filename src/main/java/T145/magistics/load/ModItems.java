package T145.magistics.load;

import T145.magistics.items.ItemMagistics;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemMagistics) {
			((ItemMagistics) item).registerItemModel();
		}

		return item;
	}

	public static void init() {
		
	}
}