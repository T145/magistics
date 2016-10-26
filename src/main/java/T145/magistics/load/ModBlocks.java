package T145.magistics.load;

import T145.magistics.api.crafting.CrucibleRecipes;
import T145.magistics.blocks.BlockCrucible;
import T145.magistics.blocks.BlockInfusedOre;
import T145.magistics.blocks.BlockInfuser;
import T145.magistics.lib.world.WorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static BlockCrucible blockCrucible;
	public static BlockInfuser blockInfuser;

	private static WorldGenerator worldGenerator;
	public static BlockInfusedOre blockOre;
	public static BlockInfusedOre blockNetherOre;
	public static BlockInfusedOre blockEndOre;

	public static void preInit(FMLPreInitializationEvent event) {
		blockCrucible = new BlockCrucible("crucible");
		blockInfuser = new BlockInfuser("infuser");

		blockOre = new BlockInfusedOre("ore_overworld");
		blockNetherOre = new BlockInfusedOre("ore_nether");
		blockEndOre = new BlockInfusedOre("ore_end");

		worldGenerator = new WorldGenerator();
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
	}

	public static void init(FMLInitializationEvent event) {
		worldGenerator.load();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		CrucibleRecipes.register();
	}
}