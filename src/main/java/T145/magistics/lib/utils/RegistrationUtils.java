package T145.magistics.lib.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistrationUtils {
	public static <T extends Item> T registerItem(T item, String name) {
		GameRegistry.register(item);
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

		GameRegistry.register(item);

		modController.setField("activeContainer", old);
		return item;
	}

	public static <T extends Block> T registerBlock(T block, String name) {
		GameRegistry.register(block);
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

		GameRegistry.register(block);

		modController.setField("activeContainer", old);
		return block;
	}
}