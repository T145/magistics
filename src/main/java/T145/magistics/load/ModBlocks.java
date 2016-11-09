package T145.magistics.load;

import T145.magistics.api.crafting.CrucibleRecipes;
import T145.magistics.api.crafting.InfuserRecipes;
import T145.magistics.blocks.BlockCrucible;
import T145.magistics.blocks.BlockInfusedOre;
import T145.magistics.blocks.BlockInfuser;
import T145.magistics.blocks.BlockLeaves;
import T145.magistics.blocks.BlockLogs;
import T145.magistics.blocks.BlockPlanks;
import T145.magistics.blocks.BlockSaplings;
import T145.magistics.lib.world.WorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static BlockCrucible blockCrucible;
	public static BlockInfuser blockInfuser;

	public static BlockInfusedOre blockOre;
	public static BlockInfusedOre blockNetherOre;
	public static BlockInfusedOre blockEndOre;

	public static BlockLogs blockLogs;
	public static BlockPlanks blockPlanks;
	public static BlockLeaves blockLeaves;
	public static BlockSaplings blockSaplings;

	public static void preInit(FMLPreInitializationEvent event) {
		blockCrucible = new BlockCrucible("crucible");
		blockInfuser = new BlockInfuser("infuser");

		blockOre = new BlockInfusedOre("ore_overworld");
		blockNetherOre = new BlockInfusedOre("ore_nether");
		blockEndOre = new BlockInfusedOre("ore_end");

		blockLogs = new BlockLogs("log");
		blockPlanks = new BlockPlanks("planks");
		blockLeaves = new BlockLeaves("leaves");
		blockSaplings = new BlockSaplings("saplings");
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		blockCrucible.registerModel();
		blockCrucible.registerRenderer();

		blockInfuser.registerModel();
		blockInfuser.registerRenderer();

		blockOre.registerModel();
		blockNetherOre.registerModel();
		blockEndOre.registerModel();

		blockLogs.registerModel();
		blockPlanks.registerModel();
		blockLeaves.registerModel();
		blockSaplings.registerModel();
	}

	public static void init(FMLInitializationEvent event) {
		WorldGenerator.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		CrucibleRecipes.register();
		InfuserRecipes.register();
	}
}