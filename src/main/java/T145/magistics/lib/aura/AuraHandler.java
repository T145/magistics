package T145.magistics.lib.aura;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class AuraHandler {

	private static ConcurrentHashMap<Integer, AuraWorld> auras = new ConcurrentHashMap();
	public static ConcurrentHashMap<Integer, CopyOnWriteArrayList<ChunkPos>> dirtyChunks = new ConcurrentHashMap();
	public static ConcurrentHashMap<Integer, BlockPos> taintTrigger = new ConcurrentHashMap();
	
	
}