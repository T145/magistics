package T145.magistics.common.lib;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModConfig;
import cpw.mods.fml.common.event.FMLInterModComms;

public class ModInterCommRegistry {
	private static List<ItemStack> facadeBlacklist = new ArrayList<ItemStack>();

	public static void blacklistFacade(Block block) {
		facadeBlacklist.add(new ItemStack(block));
	}

	public static void blacklistFacade(ItemStack stack) {
		facadeBlacklist.add(stack);
	}

	public static void blacklistFacades() {
		if (!facadeBlacklist.isEmpty())
			for (ItemStack stack : facadeBlacklist) {
				if (ModConfig.debug)
					Magistics.logger.info("Blacklisting facade: " + stack.getDisplayName());

				FMLInterModComms.sendMessage("BuildCraft|Transport", "blacklist-facade", stack);
			}
	}
}