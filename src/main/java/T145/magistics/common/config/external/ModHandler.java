package T145.magistics.common.config.external;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchCategories;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockChestHungryMetalItem;
import T145.magistics.common.config.MagisticsConfig;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModHandler extends MagisticsConfig {
	public static void init() {
		if (Loader.isModLoaded("IronChest")) {
			log("IronChest detected; support loaded.");
			GameRegistry.registerBlock(blocks[1], BlockChestHungryMetalItem.class, blockName[1]);
		}
	}

	public static void postInit() { // TODO: Add more Thaumcraft stuff
		ResearchCategories.registerCategory(Magistics.modid.toUpperCase(), new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
	}

	public static void addFacades(String blockName, int metaStart, int metaEnd) {
		for (int meta = metaStart; meta <= metaEnd; meta++)
			addFacade(blockName, meta);
	}

	public static void addFacade(String blockName, int meta) {
		log("Adding facades for " + blockName + "." + meta);
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", blockName + "@" + meta);
	}
}