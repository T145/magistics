package T145.magistics.lib.events;

import T145.magistics.Magistics;
import T145.magistics.lib.aura.AuraChunk;
import T145.magistics.lib.aura.AuraHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEventHandler {

	public static final String KEY_VIS = "Vis";
	public static final String KEY_MIASMA = "Miasma";

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
		AuraChunk chunk = AuraHandler.getAuraChunk(event.getChunk());
		NBTTagCompound tag = new NBTTagCompound();

		event.getData().setTag(Magistics.NAME, tag);

		if (chunk != null) {
			tag.setFloat(KEY_VIS, chunk.getVis());
			tag.setFloat(KEY_MIASMA, chunk.getMiasma());

			if (event.getChunk().unloaded) {
				AuraHandler.removeAuraChunk(event.getChunk());
			}
		}
	}

	@SubscribeEvent
	public void chunkLoad(ChunkDataEvent.Load event) {
		NBTTagCompound tag = event.getData().getCompoundTag(Magistics.NAME);

		if (tag.hasKey(KEY_VIS) && tag.hasKey(KEY_MIASMA)) {
			AuraHandler.addAuraChunk(event.getChunk(), tag.getFloat(KEY_VIS), tag.getFloat(KEY_MIASMA));
		} else {
			AuraHandler.generateAura(event.getChunk(), event.getWorld().rand);
		}
	}
}