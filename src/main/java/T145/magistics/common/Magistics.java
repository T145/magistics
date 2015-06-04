package T145.magistics.common;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Logger;

import T145.magistics.common.config.ModConfig;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Magistics.MODID, name = Magistics.MODID, version = Magistics.VERSION, dependencies = "after:Thaumcraft")
public class Magistics {
	public static final String MODID = "Magistics", VERSION = "$version";

	@Instance(MODID)
	public static Magistics instance;

	@Metadata
	public static ModMetadata metadata;

	@SidedProxy(clientSide = "T145.magistics.client.ClientProxy", serverSide = "T145.magistics.common.CommonProxy")
	public static CommonProxy proxy;

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();

		ModConfig.preInit(e, logger);
		ModConfig.loadMetadata(metadata, MODID, VERSION);
	}

	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent e) {
		ModConfig.onConfigChanged(MODID, e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		ModConfig.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		ModConfig.postInit();
	}
}