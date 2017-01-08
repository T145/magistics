package T145.magistics.config;

import T145.magistics.Magistics;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {

	public static final String CATEGORY_WORLDGEN = "worldgen";
	public static final String CATEGORY_BIOMES = "biomes";

	public static int auraMax = 15000;
	public static int[] dimensionBlacklist;

	public static int taintSeverity = 1;
	public static int taintWeight = 4;
	public static int enchantedForestWeight = 2;
	public static boolean generateRoots = true;

	private Configuration config;

	public Configuration get() {
		return config;
	}

	public void load(FMLPreInitializationEvent event) {
		try {
			config = new Configuration(event.getSuggestedConfigurationFile());
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

	private void sync() {
		auraMax = config.get(config.CATEGORY_GENERAL, "Max Aura", auraMax).getInt();
		dimensionBlacklist = config.get(CATEGORY_WORLDGEN, "Dimension Blacklist", new int[] {}, "Add dimension ids that you don't want Magistics worldgen applied to").getIntList();

		taintSeverity = config.getInt("Taint Severity", CATEGORY_BIOMES, 1, 0, 2, "How harsh the taint biomes are (0 for ok, 1 for normal, 2 for world eating)");
		taintWeight = config.getInt("Taint Weight", CATEGORY_BIOMES, 4, 1, 10, "How often taint biomes are generated");
		enchantedForestWeight = config.getInt("Enchanted Forest Weight", CATEGORY_BIOMES, 2, 1, 5, "How often enchanted forests are generated");
		generateRoots = config.getBoolean("Generate Roots", CATEGORY_BIOMES, true, "Whether or not to generate roots on certain trees");
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
}