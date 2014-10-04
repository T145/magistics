package T145.magistics.common.config.external;

import net.minecraft.block.Block;
import thaumcraft.common.config.ConfigBlocks;
import T145.magistics.common.blocks.BlockChestHungryMetalItem;
import T145.magistics.common.config.MagisticsConfig;
import T145.magistics.common.config.MagisticsLogger;
import T145.magistics.common.config.external.fmp.PartFactory;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModHandler {
	public static void init() {
		if (Loader.isModLoaded("ForgeMultipart")) {
			MagisticsLogger.log("ForgeMultipart detected; compatibility loaded.");
			new PartFactory().init();
			addMultiparts(ConfigBlocks.blockCosmeticOpaque, 0, 1);
			addMultiparts(ConfigBlocks.blockCosmeticSolid, 0);
			addMultiparts(ConfigBlocks.blockCosmeticSolid, 2, 7);
			addMultiparts(ConfigBlocks.blockWoodenDevice, 6, 7);
			addMultiparts(MagisticsConfig.blocks[2], 0, 1);
		}
		/*if (Loader.isModLoaded("BuildCraft|Transport")) {
			MagisticsLogger.log("Buildcraft detected; compatibility loaded.");
			addFacades(MagisticsConfig.blocks[2], 0, 1);
		}*/
		if (Loader.isModLoaded("IronChest")) {
			MagisticsLogger.log("IronChest detected; support loaded.");
			GameRegistry.registerBlock(MagisticsConfig.blocks[1], BlockChestHungryMetalItem.class, MagisticsConfig.blockName[1]);
		}
	}

	public static void addMultiparts(Block block, int meta_start, int meta_end) {
		for (int meta = meta_start; meta <= meta_end; meta++)
			addMultiparts(block, meta);
	}

	public static void addMultiparts(Block block, int meta) {
		MagisticsLogger.log("Registering Multiparts for " + block.getUnlocalizedName() + "." + meta);
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, meta), block.getUnlocalizedName() + (meta == 0 ? "" : "_" + meta));
	}

	public static void addFacades(Block block, int meta_start, int meta_end) {
		for (int meta = meta_start; meta <= meta_end; meta++)
			addFacade(block, meta);
	}

	public static void addFacade(Block block, int meta) {
		MagisticsLogger.log("Adding facades for " + block.getUnlocalizedName() + "." + meta);
		FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", block + "@" + meta);
	}
}