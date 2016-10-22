package T145.magistics.load;

import T145.magistics.api.crafting.CrucibleRecipes;
import T145.magistics.blocks.BlockCrucible;
import T145.magistics.blocks.BlockInfuser;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static BlockCrucible blockCrucible;
	public static BlockInfuser blockInfuser;

	public static void preInit(FMLPreInitializationEvent event) {
		blockCrucible = new BlockCrucible("crucible");
		blockInfuser = new BlockInfuser("infuser");
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		blockCrucible.registerModel();
		blockCrucible.registerRenderer();

		blockInfuser.registerModel();
		blockInfuser.registerRenderer();
	}

	public static void init(FMLInitializationEvent event) {}

	public static void postInit(FMLPostInitializationEvent event) {
		CrucibleRecipes.register();
	}
}