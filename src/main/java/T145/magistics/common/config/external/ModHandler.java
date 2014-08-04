package T145.magistics.common.config.external;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.Level;

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
			Magistics.logger.log(Level.INFO, "ForgeMultipart detected; compatibility loaded.");
			new PartFactory().init();
			registerMultiparts(ConfigBlocks.blockCosmeticSolid, 2, 5);
			registerMultiparts(ConfigBlocks.blockCosmeticSolid, 7);
			registerMultiparts(ConfigBlocks.blockCosmeticSolid, 9, 10);
			registerMultiparts(ConfigBlocks.blockCosmeticOpaque, 0, 1);
		}
		if (Loader.isModLoaded("BuildCraft|Transport")) {
			Magistics.logger.log(Level.INFO, "Buildcraft detected; compatibility loaded.");
		}
	}

	private static void registerMultiparts(Block b, int meta_start, int meta_end) {
		for (int meta = meta_start; meta <= meta_end; meta++)
			registerMultiparts(b, meta);
	}

	private static void registerMultiparts(Block b, int meta) {
		Magistics.logger.log(Level.INFO, "Registering Multiparts for " + b.getUnlocalizedName() + "." + meta);
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(b, meta), b.getUnlocalizedName() + (meta == 0 ? "" : "_" + meta));
	}

	public static void addFacade(Block block, int meta) {
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", new ItemStack(block, 1, meta));
	}
}