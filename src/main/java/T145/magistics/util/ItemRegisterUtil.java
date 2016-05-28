package T145.magistics.util;

import net.minecraft.item.Item;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegisterUtil {
	public static <T extends Item> T registerItem(T item, String name) {
		GameRegistry.registerItem(item, name);
		return item;
	}

	public static <T extends Item> T registerItem(T item) {
		return registerItem(item, item.getClass().getSimpleName());
	}

	public static <T extends Item> T registerItem(String modId, T item) {
		return registerItem(modId, item, item.getClass().getSimpleName());
	}

	public static <T extends Item> T registerItem(String modId, T item, String name) {
		Injector modController = new Injector(new Injector(Loader.instance(), Loader.class).getField("modController"), LoadController.class);
		Object old = modController.getField("activeContainer");
		modController.setField("activeContainer", Loader.instance().getIndexedModList().get(modId));

		GameRegistry.registerItem(item, name);

		modController.setField("activeContainer", old);
		return item;
	}
}