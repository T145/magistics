package T145.magistics.common;

import T145.magistics.net.UniversalProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Magistics.modid, name = Magistics.modid, version = Magistics.version)
public class Magistics {
	public static final String modid = "Magistics", version = "0.4.3", proxyPath = "T145.magistics.net.UniversalProxy";

	@Instance(modid)
	public static Magistics instance;

	@SidedProxy(clientSide = proxyPath, serverSide = proxyPath)
	public static UniversalProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}
}