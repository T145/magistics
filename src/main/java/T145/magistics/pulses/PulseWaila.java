package T145.magistics.pulses;

import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import T145.magistics.pulses.core.ServerPulse;
import T145.magistics.pulses.internal.WailaDataProvider;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Pulse(id = "Waila", modsRequired = "Waila")
public class PulseWaila extends ServerPulse {
	@Handler
	public void preInit(FMLPreInitializationEvent event) {}

	@Handler
	public void init(FMLInitializationEvent event) {
		FMLInterModComms.sendMessage("Waila", "register", WailaDataProvider.class.getCanonicalName() + ".registerProvider");
	}

	@Handler
	public void postInit(FMLPostInitializationEvent event) {}
}