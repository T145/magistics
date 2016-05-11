package T145.magistics.plugins;

import java.util.ArrayList;

import T145.magistics.Magistics;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PluginHandler {
	static abstract class Plugin {
		private final String name;

		public Plugin(String modid) {
			name = modid;
		}

		public String getModId() {
			return name;
		}

		public abstract void preInit();
		public abstract void init();
		public abstract void postInit();
	}

	private static ArrayList<Plugin> plugins = new ArrayList();

	static {
		addPlugin(new PluginAE2());
		addPlugin(new PluginEE3());
		addPlugin(new PluginEnderStorage());
		addPlugin(new PluginIronChest());
		addPlugin(new PluginProjectE());
		addPlugin(new PluginRailcraft());
		addPlugin(new PluginRefinedRelocation());
		addPlugin(new PluginThaumcraft());
		addPlugin(new PluginThermalExpansion());
		addPlugin(new PluginWaila());
	}

	public static void addPlugin(Plugin plugin) {
		if (Loader.isModLoaded(plugin.getModId())) {
			plugins.add(plugin);
		}
	}

	public static void preInit(FMLPreInitializationEvent event) {
		Magistics.logger.info("PreInitializing plugins...");

		if (plugins.isEmpty()) {
			Magistics.logger.info("Woops... looks like you need to install Thaumcraft!");
		} else {
			for (Plugin plugin : plugins) {
				plugin.preInit();
				Magistics.logger.info("PreInitialized plugin: " + plugin.getModId());
			}
		}
	}

	public static void init(FMLInitializationEvent event) {
		Magistics.logger.info("Initializing plugins...");

		for (Plugin plugin : plugins) {
			plugin.init();
			Magistics.logger.info("Initialized plugin: " + plugin.getModId());
		}
	}

	public static void postInit(FMLPostInitializationEvent event) {
		Magistics.logger.info("PostInitializing plugins...");

		for (Plugin plugin : plugins) {
			plugin.postInit();
			Magistics.logger.info("PostInitialized plugin: " + plugin.getModId());
		}
	}
}