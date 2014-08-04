package T145.magistics.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import T145.magistics.common.Magistics;

public class Config {
	public static Configuration config;

	public static void preInit(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();
		} catch (Exception e) {
			Magistics.logger.log(Level.ERROR, "An error has occurred while loading configuration properties!", e);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public static void init() {}

	public static void postInit() {}
}