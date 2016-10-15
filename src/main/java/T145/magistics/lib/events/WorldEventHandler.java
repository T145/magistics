package T145.magistics.lib.events;

import T145.magistics.lib.aura.AuraHandler;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {
	@SubscribeEvent
	public void worldLoad(WorldEvent.Load event) {
		if (!event.getWorld().isRemote) {
			AuraHandler.addAuraWorld(event.getWorld().provider.getDimension());
		}
	}

	@SubscribeEvent
	public void worldUnload(WorldEvent.Unload event) {
		if (!event.getWorld().isRemote) {
			AuraHandler.removeAuraWorld(event.getWorld().provider.getDimension());
		}
	}
}