package T145.magistics.pulses;

import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import T145.magistics.Magistics;
import T145.magistics.pulses.core.CorePulse;
import T145.magistics.pulses.internal.WailaDataProvider;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Pulse(id = "Waila", description = "Toggles Waila compatibility.", modsRequired = "Waila")
public class PulseWaila extends CorePulse {
	@Override
	@Handler
	public void preInit(FMLPreInitializationEvent event) {}

	@Override
	@Handler
	public void init(FMLInitializationEvent event) {
		Magistics.logger.info("Getting some Waila tonight!");
		FMLInterModComms.sendMessage("Waila", "register", WailaDataProvider.class.getCanonicalName() + ".registerProvider");
	}

	@Override
	@Handler
	public void postInit(FMLPostInitializationEvent event) {}

	@Override
	public void registerRenderInformation() {}
}