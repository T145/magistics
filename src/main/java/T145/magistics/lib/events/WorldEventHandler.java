package T145.magistics.lib.events;

import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {

	@SubscribeEvent
	public void worldLoad(WorldEvent.Load event) {}

	@SubscribeEvent
	public void worldUnload(WorldEvent.Unload event) {}

	@SubscribeEvent
	public void chunkSave(ChunkDataEvent.Save event) {}

	@SubscribeEvent
	public void chunkLoad(ChunkDataEvent.Load event) {}
}