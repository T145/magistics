package T145.magistics.common;

import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Magistics.modid, name = Magistics.modid, version = Magistics.version, guiFactory = "T145.magistics.client.GuiFactory", dependencies = "after:Thaumcraft")
public class Magistics {
	public static final String modid = "Magistics", version = "0.7.6";

	@SidedProxy(clientSide = "T145.magistics.client.ClientProxy", serverSide = "T145.magistics.common.CommonProxy")
	public static CommonProxy proxy;

	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent e) {
		proxy.changeConfig(e, modid);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.loadConfig(e.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		ConfigObjects.registerObjects();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit();
		ConfigObjects.registerRenderers();
	}
}