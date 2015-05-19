package T145.magistics.common;

import org.apache.logging.log4j.Logger;

import T145.magistics.common.config.ModObjects;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Magistics.MODID)
public class Magistics {
	public static final String MODID = "Magistics";
	
	@Instance(MODID)
	public static Magistics instance;
	
	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		ModObjects.registerObjects();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		
	}
}