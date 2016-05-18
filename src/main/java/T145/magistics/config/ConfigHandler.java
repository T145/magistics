package T145.magistics.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import T145.magistics.Magistics;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	public static final String CATEGORY_PlUGINS = "Plugins";
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

	public Configuration getConfig() {
		return config;
	}

	private void syncConfig() {
		Property enableAppEngSupport = config.get(CATEGORY_PlUGINS, "enable_ae2", true);
		enableAppEngSupport.comment = "Toggle the Applied Energistics 2 plugin";
		enableAE2 = enableAppEngSupport.getBoolean();

		Property enableEE3Support = config.get(CATEGORY_PlUGINS, "enable_ee3", true);
		enableEE3Support.comment = "Toggle the Equivalent Exchange 3 plugin";
		enableEE3 = enableEE3Support.getBoolean();

		Property enableEnderStorageSupport = config.get(CATEGORY_PlUGINS, "enable_ender_storage", true);
		enableEnderStorageSupport.comment = "Toggle the Applied Energistics 2 plugin";
		enableEnderStorage = enableEnderStorageSupport.getBoolean();

		Property enableIronChestSupport = config.get(CATEGORY_PlUGINS, "enable_iron_chests", true);
		enableIronChestSupport.comment = "Toggle the Iron Chest plugin";
		enableIronChest = enableIronChestSupport.getBoolean();

		Property enableProjectESupport = config.get(CATEGORY_PlUGINS, "enable_projecte", true);
		enableProjectESupport.comment = "Toggle the Project E plugin";
		enableProjectE = enableProjectESupport.getBoolean();

		Property enableRailcraftSupport = config.get(CATEGORY_PlUGINS, "enable_railcraft", true);
		enableRailcraftSupport.comment = "Toggle the Railcraft plugin";
		enableRailcraft = enableRailcraftSupport.getBoolean();

		Property enableRefinedRelocationSupport = config.get(CATEGORY_PlUGINS, "enable_refined_relocation", true);
		enableRefinedRelocationSupport.comment = "Toggle the Refined Relocation plugin";
		enableRefinedRelocation = enableRefinedRelocationSupport.getBoolean();

		Property enableThaumicTinkererSupport = config.get(CATEGORY_PlUGINS, "enable_thaumic_tinkerer", true);
		enableThaumicTinkererSupport.comment = "Toggle the Thaumic Tinkerer plugin";
		enableThaumicTinkerer = enableThaumicTinkererSupport.getBoolean();

		Property enableThermalExpansionSupport = config.get(CATEGORY_PlUGINS, "enable_thermal_expansion", true);
		enableThermalExpansionSupport.comment = "Toggle the Thermal Expansion plugin";
		enableThermalExpansion = enableThermalExpansionSupport.getBoolean();

		Property enableWailaSupport = config.get(CATEGORY_PlUGINS, "enable_waila", true);
		enableWailaSupport.comment = "Toggle the Waila plugin";
		enableWaila = enableWailaSupport.getBoolean();
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