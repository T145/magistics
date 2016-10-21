package T145.magistics.config;

import T145.magistics.Magistics;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	private Configuration config;

	public Configuration get() {
		return config;
	}

	private boolean addProperty(String category, String key, boolean value, String comment) {
		Property property = config.get(category, key, value);
		property.setComment(comment);
		return property.getBoolean();
	}

	private void sync() {}

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
			config.load();
			update();
		} catch (Throwable err) {
			Magistics.logger.catching(err);
		} finally {
			save();
		}
	}
}