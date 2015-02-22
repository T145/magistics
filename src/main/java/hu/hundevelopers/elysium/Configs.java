package hu.hundevelopers.elysium;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Configs {

	public static final int MAX_DRAGON_IN_END = 1;

	public static final int BIOME_PLAIN = 125;
	public static final int BIOME_FOREST = 126;
	public static final int BIOME_PLAIN_CORRUPT = 127;
	public static final int ELYSIUM_DEEP_OCEAN = 128;
	public static final int BIOME_OCEAN = 129;
	public static final int BIOME_RIVER = 130;
	public static final int BIOME_BEACH = 131;
	public static final int BIOME_DESERT = 132;

	//Settings
	public static final int maxportaldistance = 64;
	public static final byte ticksbeforeportalcheck = 5;
	public static final byte ticksbeforeportalteleport = 20 * 5;

	public static final byte labyrinthBottom = 5;
	public static final byte labyrinthTop = labyrinthBottom + 4;

	public static final Block labyrinthWall = Blocks.quartz_block;
	public static final Block labyrinthLamp = Elysium.blockEnergyCrystal;

	public static final int mazeRoomRarity = 6;
	
	public static final boolean customGui = true;
	public static final boolean isMobCrystals = true;




}
