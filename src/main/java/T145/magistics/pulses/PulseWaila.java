package T145.magistics.pulses;

import T145.magistics.pulses.core.ServerPulse;
import T145.magistics.pulses.internal.WailaDataProvider;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PulseWaila extends ServerPulse {
	@Override
	public String getModId() {
		return "Waila";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		FMLInterModComms.sendMessage("Waila", "register", WailaDataProvider.class.getCanonicalName() + ".registerProvider");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {}
}