package T145.magistics.pulses.core;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class CorePulse extends ServerPulse {
	public abstract void registerRenderInformation();
}