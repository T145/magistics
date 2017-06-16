package T145.magistics.world;

import java.util.concurrent.ConcurrentHashMap;

import T145.magistics.Magistics;
import T145.magistics.world.aura.AuraManager;
import T145.magistics.world.aura.AuraThread;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Magistics.MODID)
public class WorldServerEventBus {

	private static final WorldServerEventBus INSTANCE = new WorldServerEventBus();

	public static ConcurrentHashMap<Integer, AuraThread> auraThreads = new ConcurrentHashMap();

	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load event) {
		if (event.getWorld().isRemote) {
			return;
		}

		int dimID = event.getWorld().provider.getDimension();
		AuraManager.addAuraWorld(dimID);
		Magistics.LOGGER.info("Loading aura world: " + dimID);
	}

	@SubscribeEvent
	public static void worldUnload(WorldEvent.Unload event) {
		if (event.getWorld().isRemote) {
			return;
		}

		int dimID = event.getWorld().provider.getDimension();
		AuraManager.removeAuraWorld(dimID);
		Magistics.LOGGER.info("Unloading aura world: " + dimID);
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
		MinecraftForge.TERRAIN_GEN_BUS.register(INSTANCE);
	}
}