package T145.magistics.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegistrationUtil {
	public static <T extends Item> T registerItem(T item, String name) {
		GameRegistry.registerItem(item, name);
		return item;
	}

	public static <T extends Item> T registerItem(T item) {
		return registerItem(item, item.getClass().getSimpleName());
	}

	public static <T extends Item> T registerItem(String modid, T item) {
		return registerItem(modid, item, item.getClass().getSimpleName());
	}

	public static <T extends Item> T registerItem(String modid, T item, String name) {
		Injector modController = new Injector(new Injector(Loader.instance(), Loader.class).getField("modController"), LoadController.class);
		Object old = modController.getField("activeContainer");
		modController.setField("activeContainer", Loader.instance().getIndexedModList().get(modid));

		GameRegistry.registerItem(item, name);

		modController.setField("activeContainer", old);
		return item;
	}

	public static <T extends Block> T registerBlock(T block, String name) {
		GameRegistry.registerBlock(block, name);
		return block;
	}

	public static <T extends Block> T registerBlock(T block) {
		return registerBlock(block, block.getClass().getSimpleName());
	}

	public static <T extends Block> T registerBlock(String modid, T block) {
		return registerBlock(modid, block, block.getClass().getSimpleName());
	}

	public static <T extends Block> T registerBlock(String modid, T block, String name) {
		Injector modController = new Injector(new Injector(Loader.instance(), Loader.class).getField("modController"), LoadController.class);
		Object old = modController.getField("activeContainer");
		modController.setField("activeContainer", Loader.instance().getIndexedModList().get(modid));

		GameRegistry.registerBlock(block, name);

		modController.setField("activeContainer", old);
		return block;
	}
}