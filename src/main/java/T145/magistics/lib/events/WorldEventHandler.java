package T145.magistics.lib.events;

import T145.magistics.Magistics;
import T145.magistics.lib.aura.AuraChunk;
import T145.magistics.lib.aura.AuraHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.event.world.ChunkDataEvent;
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

	@SubscribeEvent
	public void chunkSave(ChunkDataEvent.Save event) {
		int dimension = event.getWorld().provider.getDimension();
		ChunkPos pos = event.getChunk().getChunkCoordIntPair();
		AuraChunk chunk = AuraHandler.getAuraChunk(pos, dimension);
		NBTTagCompound tag = new NBTTagCompound();

		event.getData().setTag(Magistics.NAME, tag);

		if (chunk != null) {
			tag.setFloat("Vis", chunk.getVis());
			tag.setFloat("Miasma", chunk.getMiasma());

			if (event.getChunk().unloaded) {
				AuraHandler.removeAuraChunk(pos, dimension);
			}
		}
	}

	@SubscribeEvent
	public void chunkLoad(ChunkDataEvent.Load event) {
		int dimension = event.getWorld().provider.getDimension();
		ChunkPos pos = event.getChunk().getChunkCoordIntPair();
		NBTTagCompound tag = event.getData().getCompoundTag(Magistics.NAME);

		if (tag.hasKey("Vis") && tag.hasKey("Miasma")) {
			AuraHandler.addAuraChunk(event.getChunk(), tag.getFloat("Vis"), tag.getFloat("Miasma"));
		} else {
			AuraHandler.generateAura(event.getChunk(), event.getWorld().rand);
		}
	}
}