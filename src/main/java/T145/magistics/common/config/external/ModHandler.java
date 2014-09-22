package T145.magistics.common.config.external;

import net.minecraft.block.Block;
import thaumcraft.common.config.ConfigBlocks;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.external.fmp.PartFactory;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;

public class ModHandler {
	public static void init() {
		if (Loader.isModLoaded("ForgeMultipart")) {
			Magistics.log("ForgeMultipart detected; compatibility loaded.");
			new PartFactory().init();
			addMultiparts(ConfigBlocks.blockCosmeticOpaque, 0, 1);
			addMultiparts(ConfigBlocks.blockCosmeticSolid, 0);
			addMultiparts(ConfigBlocks.blockCosmeticSolid, 2, 7);
			addMultiparts(ConfigBlocks.blockWoodenDevice, 6, 7);
		}
		if (Loader.isModLoaded("BuildCraft|Transport")) {
			addFacades(ConfigBlocks.blockCosmeticOpaque, 0, 1);
			addFacade(ConfigBlocks.blockCosmeticSolid, 0);
			addFacades(ConfigBlocks.blockCosmeticSolid, 2, 7);
			addFacades(ConfigBlocks.blockWoodenDevice, 6, 7);
		}
	}

	public static void addMultiparts(Block block, int meta_start, int meta_end) {
		for (int meta = meta_start; meta <= meta_end; meta++)
			addMultiparts(block, meta);
	}

	public static void addMultiparts(Block block, int meta) {
		Magistics.log("Registering Multiparts for " + block.getUnlocalizedName() + "." + meta);
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, meta), block.getUnlocalizedName() + (meta == 0 ? "" : "_" + meta));
	}

	public static void addFacades(Block block, int meta_start, int meta_end) {
		for (int meta = meta_start; meta <= meta_end; meta++)
			addFacade(block, meta);
	}

	public static void addFacade(Block block, int meta) {
		Magistics.log("Registering facades for " + block.getUnlocalizedName() + "." + meta);
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", block + "@" + meta);
	}
}