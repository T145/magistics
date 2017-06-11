package T145.magistics.config;

import T145.magistics.Magistics;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigMain {

	public static final String CATEGORY_BIOMES = "biomes";

	public static int voidDimensionId = 14;
	public static boolean allowVoidRespawn;

	public static boolean generateRoots;
	public static int taintSeverity = 1;
	public static int taintWeight = 4;
	public static int enchantedForestWeight = 2;

	private Configuration config;

	public Configuration getConfig() {
		return config;
	}

	public void load(FMLPreInitializationEvent event) {
		try {
			config = new Configuration(event.getSuggestedConfigurationFile());
			config.addCustomCategoryComment(CATEGORY_BIOMES, "Biome gen values");
			config.load();
			update();
		} catch (Exception err) {
			Magistics.LOGGER.catching(err);
		} finally {
			save();
		}
	}

	@SubscribeEvent
	public void update(OnConfigChangedEvent event) {
		if (event.getModID().equals(Magistics.MODID)) {
			update();
		}
	}

	public void update() {
		sync();
		save();
	}

	public void sync() {
		taintSeverity = config.getInt("Taint Severity", CATEGORY_BIOMES, 1, 0, 2, "How harsh the taint biomes are (0 for ok, 1 for normal, 2 for world eating)");
		taintWeight = config.getInt("Taint Weight", CATEGORY_BIOMES, 4, 1, 10, "How often taint biomes are generated");
		enchantedForestWeight = config.getInt("Enchanted Forest Weight", CATEGORY_BIOMES, 2, 1, 5, "How often enchanted forests are generated");
		generateRoots = config.getBoolean("Generate Roots", CATEGORY_BIOMES, true, "Whether or not to generate roots on certain trees");

		allowVoidRespawn = config.getBoolean("Allow Void Respawn", config.CATEGORY_GENERAL, true, "If you die in a void chest, can you respawn there?");
		voidDimensionId = config.getInt("Void Dimension Id", config.CATEGORY_GENERAL, 14, 2, 100, "ID for the void dimension");
	}

	public void save() {
		if (config.getConfigFile().isFile() && config.hasChanged()) {
			config.save();
		}
	}
}