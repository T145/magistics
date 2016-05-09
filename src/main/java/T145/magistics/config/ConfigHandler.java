package T145.magistics.config;

import net.minecraftforge.common.config.Configuration;
import T145.magistics.Magistics;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	private Configuration config;

	public static boolean debug = false;

	public Configuration getConfig() {
		return config;
	}

	private void syncConfig() {
		debug = config.getBoolean("Debug", config.CATEGORY_GENERAL, false, "Toggles advanced log output.");
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