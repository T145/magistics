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

		int dim = event.getWorld().provider.getDimension();
		AuraManager.addAuraWorld(dim);
		Magistics.LOG.info("Loading aura world: " + dim);
	}

	@SubscribeEvent
	public static void worldUnload(WorldEvent.Unload event) {
		if (event.getWorld().isRemote) {
			return;
		}

		int dim = event.getWorld().provider.getDimension();
		AuraManager.removeAuraWorld(dim);
		Magistics.LOG.info("Unloading aura world: " + dim);
	}

	@SubscribeEvent
	public void worldTick(TickEvent.WorldTickEvent event) {
		if (event.side == Side.CLIENT) {
			return;
		}

		int dim = event.world.provider.getDimension();

		if (event.phase == TickEvent.Phase.START && !auraThreads.containsKey(dim) && AuraManager.getAuraWorld(dim) != null) {
			AuraThread auraThread = new AuraThread(dim);
			new Thread(auraThread).start();
			auraThreads.put(dim, auraThread);
		}
	}

	static {
		Magistics.LOG.info("Registered terrain gen bus!");
		MinecraftForge.TERRAIN_GEN_BUS.register(new AuraEvents());
	}
}