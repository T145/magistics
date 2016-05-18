package T145.magistics.plugins.core;

import java.util.ArrayList;

import T145.magistics.Magistics;
import T145.magistics.plugins.PluginAE2;
import T145.magistics.plugins.PluginEE3;
import T145.magistics.plugins.PluginEnderStorage;
import T145.magistics.plugins.PluginIronChest;
import T145.magistics.plugins.PluginProjectE;
import T145.magistics.plugins.PluginRailcraft;
import T145.magistics.plugins.PluginRefinedRelocation;
import T145.magistics.plugins.PluginThaumcraft;
import T145.magistics.plugins.PluginThaumicTinkerer;
import T145.magistics.plugins.PluginThermalExpansion;
import T145.magistics.plugins.PluginWaila;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PluginHandler {
	private static ArrayList<Plugin> plugins = new ArrayList();

	static {
		addPlugin(new PluginAE2(Magistics.configHandler.enableAE2));
		addPlugin(new PluginEE3(Magistics.configHandler.enableEE3));
		addPlugin(new PluginEnderStorage(Magistics.configHandler.enableIronChest));
		addPlugin(new PluginIronChest(Magistics.configHandler.enableIronChest));
		addPlugin(new PluginProjectE(Magistics.configHandler.enableProjectE));
		addPlugin(new PluginRailcraft(Magistics.configHandler.enableRailcraft));
		addPlugin(new PluginRefinedRelocation(Magistics.configHandler.enableRefinedRelocation));
		addPlugin(new PluginThaumcraft());
		addPlugin(new PluginThaumicTinkerer(Magistics.configHandler.enableThaumicTinkerer));
		addPlugin(new PluginThermalExpansion(Magistics.configHandler.enableThermalExpansion));
		addPlugin(new PluginWaila(Magistics.configHandler.enableWaila));
	}

	public static void addPlugin(Plugin plugin) {
		if (Loader.isModLoaded(plugin.getModId()) && plugin.isActive()) {
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