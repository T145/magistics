package T145.magistics.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import T145.magistics.Magistics;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	public static final String CATEGORY_OVERRIDES = "thaumcraft_overrides";
	public static final String CATEGORY_BLOCKS = "blocks";
	public static final String CATEGORY_ITEMS = "items";

	private Configuration config;

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
		noFluidsInTCTabs = addProperty(CATEGORY_OVERRIDES, "noFluidsInTCTabs", true, "Disables fluid blocks (not buckets) in the TC creative tab (or tabs if tab content sorting is enabled).");
		noTaintInTCTabs = addProperty(CATEGORY_OVERRIDES, "noTaintInTCTabs", true, "Disables taint in the TC creative tab (or tabs if tab content sorting is enabled.");
		sortTCTabsContent = addProperty(CATEGORY_OVERRIDES, "sortTCTabsContent", true, "Sorts the TC creative tab into separate tabs that correspond to the Thaumonomicon categories.");
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