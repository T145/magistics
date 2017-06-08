package T145.magistics.config;

import T145.magistics.Magistics;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigMain {
	
	public static int voidDimensionId = 14;

	private Configuration config;

	public void load(FMLPreInitializationEvent event) {
		try {
			config = new Configuration(event.getSuggestedConfigurationFile());
			// config.addCustomCategoryComment
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
		voidDimensionId = config.getInt("Void Dimension Id", config.CATEGORY_GENERAL, 14, 2, 100, "ID for the void dimension");
	}

	public void save() {
		if (config.getConfigFile().isFile() && config.hasChanged()) {
			config.save();
		}
	}
}