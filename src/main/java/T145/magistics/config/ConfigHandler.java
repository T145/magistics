package T145.magistics.config;

import T145.magistics.Magistics;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	public static final String CATEGORY_WORLDGEN = "worldgen";

	public static boolean lowGfx = false;
	public static int auraMax = 15000;
	public static int taintRarity = 1;
	public static int[] dimensionBlacklist;

	private Configuration config;

	public Configuration get() {
		return config;
	}

	private void sync() {
		lowGfx = config.getBoolean("Low Graphics", config.CATEGORY_CLIENT, lowGfx, "Enables low graphics (better for older computers)");
		auraMax = config.get(config.CATEGORY_GENERAL, "Max Aura", auraMax).getInt();
		dimensionBlacklist = config.get(CATEGORY_WORLDGEN, "Dimension Blacklist", new int[] {}, "Add dimension ids that you don't want Magistics worldgen applied to").getIntList();
	}

	public static boolean isDimensionBlacklisted(int id) {
		int matches = 0;

		for (int dimension : dimensionBlacklist) {
			if (id == dimension) {
				++matches;
			}
		}

		return matches == 0;
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
			config.load();
			update();
		} catch (Throwable err) {
			Magistics.logger.catching(err);
		} finally {
			save();
		}
	}
}