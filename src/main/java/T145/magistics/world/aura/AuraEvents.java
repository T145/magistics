package T145.magistics.world.aura;

import java.util.concurrent.ConcurrentHashMap;

import T145.magistics.Magistics;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Magistics.MODID)
public class AuraEvents {

	public static ConcurrentHashMap<Integer, AuraThread> auraThreads = new ConcurrentHashMap();

	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load event) {
		if (event.getWorld().isRemote) {
			return;
		}

		int dimID = event.getWorld().provider.getDimension();
		AuraManager.addAuraWorld(dimID);
		Magistics.LOG.info("Loading aura world: " + dimID);
	}

	@SubscribeEvent
	public static void worldUnload(WorldEvent.Unload event) {
		if (event.getWorld().isRemote) {
			return;
		}

		int dimID = event.getWorld().provider.getDimension();
		AuraManager.removeAuraWorld(dimID);
		Magistics.LOG.info("Unloading aura world: " + dimID);
	}

	@SubscribeEvent
	public void worldTick(TickEvent.WorldTickEvent event) {
		if (event.side == Side.CLIENT) {
			return;
		}

		int dimID = event.world.provider.getDimension();

		if (event.phase == TickEvent.Phase.START && !auraThreads.containsKey(dimID) && AuraManager.getAuraWorld(dimID) != null) {
			AuraThread auraThread = new AuraThread(dimID);
			new Thread(auraThread).start();
			auraThreads.put(dimID, auraThread);
		}
	}

	static {
		Magistics.LOG.info("Registered terrain gen bus!");
		MinecraftForge.TERRAIN_GEN_BUS.register(new AuraEvents());
	}
}