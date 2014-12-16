package T145.magistics.common;

import T145.magistics.common.config.Settings;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Magistics.modid, name = Magistics.modid, version = Magistics.version, guiFactory = "T145.magistics.client.GuiFactory", dependencies = "after:Thaumcraft")
public class Magistics {
	public static final String modid = "Magistics", version = "0.7.0";

	@Instance(modid)
	public static Magistics instance;

	@SidedProxy(clientSide = "T145.magistics.client.ClientProxy", serverSide = "T145.magistics.common.CommonProxy")
	public static CommonProxy proxy;

	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent e) {
		Settings.onConfigChange(e, modid);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Settings.preInit(e.getSuggestedConfigurationFile());
		proxy.registerRenderInformation();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		Settings.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		Settings.postInit();
	}
}