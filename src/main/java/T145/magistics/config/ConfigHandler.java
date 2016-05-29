package T145.magistics.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import T145.magistics.Magistics;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	public static final String CATEGORY_PlUGINS = "plugins";
	public static final String CATEGORY_OVERRIDES = "thaumcraft_overrides";

	public static final String CATEGORY_BLOCKS = "Blocks";
	public static final String CATEGORY_ITEMS = "Items";

	private Configuration config;

	public static boolean enableAE2;
	public static boolean enableEE3;
	public static boolean enableEnderStorage;
	public static boolean enableIronChest;
	public static boolean enableProjectE;
	public static boolean enableRailcraft;
	public static boolean enableRefinedRelocation;
	public static boolean enableThaumicTinkerer;
	public static boolean enableThermalExpansion;
	public static boolean enableWaila;

	public static boolean noFluidsInTCTabs;
	public static boolean noTaintInTCTabs;
	public static boolean sortTCTabsContent;
	public static boolean fixTCBlockNames;

	public Configuration getConfig() {
		return config;
	}

	private boolean addProperty(String category, String key, boolean value, String comment) {
		Property property = config.get(category, key, value);
		property.comment = comment;
		return property.getBoolean();
	}

	private void syncConfig() {
		enableAE2 = addProperty(CATEGORY_PlUGINS, "enable_ae2", true, "Toggle the Applied Energistics 2 plugin");
		enableEE3 = addProperty(CATEGORY_PlUGINS, "enable_ee3", true, "Toggle the Equivalent Exchange 3 plugin");
		enableEnderStorage = addProperty(CATEGORY_PlUGINS, "enable_ender_storage", true, "Toggle the EnderStorage plugin");
		enableIronChest = addProperty(CATEGORY_PlUGINS, "enable_iron_chests", true, "Toggle the Iron Chest plugin");
		enableProjectE = addProperty(CATEGORY_PlUGINS, "enable_projecte", true, "Toggle the Project E plugin");
		enableRailcraft = addProperty(CATEGORY_PlUGINS, "enable_railcraft", true, "Toggle the Railcraft plugin");
		enableRefinedRelocation = addProperty(CATEGORY_PlUGINS, "enable_refined_relocation", true, "Toggle the Refined Relocation plugin");
		enableThaumicTinkerer = addProperty(CATEGORY_PlUGINS, "enable_thaumic_tinkerer", true, "Toggle the Thaumic Tinkerer plugin");
		enableThermalExpansion = addProperty(CATEGORY_PlUGINS, "enable_thermal_expansion", true, "Toggle the Thermal Expansion plugin");
		enableWaila = addProperty(CATEGORY_PlUGINS, "enable_waila", true, "Toggle the Waila plugin");

		noFluidsInTCTabs = addProperty(CATEGORY_OVERRIDES, "noFluidsInTCTabs", true, "Disables fluid blocks in the TC tab (or tabs if tab content sorting is enabled).");
		noTaintInTCTabs = addProperty(CATEGORY_OVERRIDES, "noTaintInTCTabs", true, "Disables taint in the TC tab (or tabs if tab content sorting is enabled.");
		sortTCTabsContent = addProperty(CATEGORY_OVERRIDES, "sortTCTabsContent", true, "Sorts the TC tab into separate tabs that correspond to the Thaumonomicon categories.");
		fixTCBlockNames = addProperty(CATEGORY_OVERRIDES, "fixTCBlockNames", true, "Makes some TC block names like vanilla ones; e.g. Thaumium Block becomes Block of Thaumium");
	}

	private void saveConfig() {
		if (config.getConfigFile().isFile() && config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public void updateConfig(OnConfigChangedEvent event) {
		if (event.modID.equals(Magistics.MODID)) {
			syncConfig();
			saveConfig();
		}
	}

	public ConfigHandler(FMLPreInitializationEvent event) {
		try {
			config = new Configuration(event.getSuggestedConfigurationFile());
			config.addCustomCategoryComment(CATEGORY_PlUGINS, "NOTE: Requires full game restart to take effect!!!");
			config.addCustomCategoryComment(CATEGORY_OVERRIDES, "Things that Magistics changes in regular Thaumcraft");
			config.load();
			syncConfig();
			saveConfig();
		} catch (Throwable err) {
			Magistics.logger.catching(err);
		} finally {
			saveConfig();
		}
	}
}