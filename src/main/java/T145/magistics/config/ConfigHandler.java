package T145.magistics.config;

import T145.magistics.Magistics;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	public static final String CATEGORY_WORLDGEN = "worldgen";
	public static final String CATEGORY_BIOMES = "biomes";

	public static boolean lowGfx = false;
	public static int auraMax = 15000;
	public static int[] dimensionBlacklist;

	public static int taintSeverity = 1;
	public static int taintWeight = 4;

	private Configuration config;

	public Configuration get() {
		return config;
	}

	private void sync() {
		lowGfx = config.getBoolean("Low Graphics", config.CATEGORY_CLIENT, lowGfx, "Enables low graphics (better for older computers)");
		auraMax = config.get(config.CATEGORY_GENERAL, "Max Aura", auraMax).getInt();
		dimensionBlacklist = config.get(CATEGORY_WORLDGEN, "Dimension Blacklist", new int[] {}, "Add dimension ids that you don't want Magistics worldgen applied to").getIntList();

		taintSeverity = config.getInt("Taint Severity", CATEGORY_BIOMES, 1, 0, 2, "How harsh the taint biomes are (0 for ok, 1 for normal, 2 for world eating)");
		taintWeight = config.getInt("Taint Weight", CATEGORY_BIOMES, 4, 1, 10, "How often taint biomes are generated");
	}

	public static boolean isDimensionWhitelisted(int id) {
		if (dimensionBlacklist.length > 0) {
			for (int i = 0; i < dimensionBlacklist.length; ++i) {
				if (dimensionBlacklist[i] == id) {
					return false;
				}
			}
		}

		return true;
	}

	private void save() {
		if (config.getConfigFile().isFile() && config.hasChanged()) {
			config.save();
		}
	}

	public void update() {
		sync();
		save();
	}

	@SubscribeEvent
	public void update(OnConfigChangedEvent event) {
		if (event.getModID().equals(Magistics.MODID)) {
			update();
		}
	}

	public void preInit(FMLPreInitializationEvent event) {
		try {
			config = new Configuration(event.getSuggestedConfigurationFile());
			config.addCustomCategoryComment(config.CATEGORY_CLIENT, "Settings that improve client performance");
			config.addCustomCategoryComment(config.CATEGORY_GENERAL, "The basic mod settings");
			config.addCustomCategoryComment(CATEGORY_WORLDGEN, "Configure world generation");
			config.addCustomCategoryComment(CATEGORY_BIOMES, "Biome gen configurations");
			config.load();
			update();
		} catch (Throwable err) {
			Magistics.logger.catching(err);
		} finally {
			save();
		}
	}
}