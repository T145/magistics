package T145.magistics.common.config.external;

import net.minecraft.block.Block;
import thaumcraft.common.config.ConfigBlocks;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.external.fmp.PartFactory;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import cpw.mods.fml.common.Loader;

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
	}

	public static void addMultiparts(Block block, int meta_start, int meta_end) {
		for (int meta = meta_start; meta <= meta_end; meta++)
			addMultiparts(block, meta);
	}

	public static void addMultiparts(Block block, int meta) {
		Magistics.log("Registering Multiparts for " + block.getUnlocalizedName() + "." + meta);
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, meta), block.getUnlocalizedName() + (meta == 0 ? "" : "_" + meta));
	}
}