package T145.magistics.common.config;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import thaumcraft.common.config.Config;
import T145.magistics.client.renderers.block.BlockCrystalStorageRenderer;
import T145.magistics.client.renderers.block.BlockMysticFarmlandRenderer;
import T145.magistics.common.blocks.BlockCrystalStorageBasic;
import T145.magistics.common.blocks.BlockCrystalStorageBrick;
import T145.magistics.common.blocks.BlockCrystalStorageBrickEngineeringDark;
import T145.magistics.common.blocks.BlockCrystalStorageBrickEngineeringLight;
import T145.magistics.common.blocks.BlockCrystalStorageItem;
import T145.magistics.common.blocks.BlockCrystalStoragePlate;
import T145.magistics.common.blocks.BlockCrystalStoragePlatform;
import T145.magistics.common.blocks.BlockCrystalStorageShield;
import T145.magistics.common.blocks.BlockCrystalStorageStructure;
import T145.magistics.common.blocks.BlockMysticFarmland;
import T145.magistics.common.lib.ModObjects;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {
	public static final String CATEGORY_BLOCKS = "Blocks", CATEGORY_ITEMS = "Items";

	public static int growthStackHeight, maxGrowthHeight;

	public static void sync(Configuration config) {
		growthStackHeight = config.getInt("stackHeight", "Blocks", 16, 1, 255, "Set the stack size for the full growth buff.");
		maxGrowthHeight = config.getInt("maxGrowthHeight", "Blocks", 4, 1, 32, "Set the max growth height of cacti/reeds.");
	}

	public static void onConfigChanged(Configuration config, String modid, OnConfigChangedEvent e) {
		if (e.modID.equals(modid)) {
			sync(config);

			if (config != null && config.hasChanged())
				config.save();
		}
	}

	public static void loadMetadata(ModMetadata meta, String modid, String version) {
		meta.autogenerated = false;
		meta.modId = meta.name = modid;
		meta.version = version;
		meta.description = "Adding some logistics to Thaumcraft!";
		meta.url = "https://github.com/T145/magistics";
		meta.updateUrl = "https://github.com/T145/magistics/releases";
		meta.authorList.add("T145");
		meta.credits = "Texture team is awesome, and so are the fans!";
		meta.logoFile = "logo.png";
	}

	public static void preInit(Configuration config, FMLPreInitializationEvent e, Logger logger) {
		try {
			config = new Configuration(e.getSuggestedConfigurationFile());
			config.copyCategoryProps(Config.config, new String[] { "enchantments", "monster_spawning", "world_generation", "world_regeneration" });
			config.addCustomCategoryComment(CATEGORY_BLOCKS, "Blocks added by Magistics");
			config.addCustomCategoryComment(CATEGORY_ITEMS, "Items added by Magistics");
			config.load();
			sync(config);
			config.save();
		} catch (Exception ex) {
			logger.log(Level.ERROR, "A fatal error has occurred while reading configuration properties!");
		} finally {
			if (config != null)
				config.save();
		}
	}

	public static Block
	blockCrystalStorage = new BlockCrystalStorageBasic(),
	blockCrystalStorageBrick = new BlockCrystalStorageBrick(),
	blockCrystalStoragePlate = new BlockCrystalStoragePlate(),
	blockCrystalStoragePlatform = new BlockCrystalStoragePlatform(),
	blockCrystalStorageShield = new BlockCrystalStorageShield(),
	blockCrystalStorageStructure = new BlockCrystalStorageStructure(),
	blockCrystalStorageBrickEngineeringLight = new BlockCrystalStorageBrickEngineeringLight(),
	blockCrystalStorageBrickEngineeringDark = new BlockCrystalStorageBrickEngineeringDark(),
	blockMysticFarmland = new BlockMysticFarmland();

	public static void init() {
		ModObjects reg = new ModObjects();
		reg.addBlock(blockCrystalStorage, BlockCrystalStorageItem.class);
		reg.addBlock(blockCrystalStorageBrick, BlockCrystalStorageItem.class);
		reg.addBlock(blockCrystalStoragePlate, BlockCrystalStorageItem.class);
		reg.addBlock(blockCrystalStoragePlatform, BlockCrystalStorageItem.class);
		reg.addBlock(blockCrystalStorageShield, BlockCrystalStorageItem.class);
		reg.addBlock(blockCrystalStorageStructure, BlockCrystalStorageItem.class);
		reg.addBlock(blockCrystalStorageBrickEngineeringLight, BlockCrystalStorageItem.class);
		reg.addBlock(blockCrystalStorageBrickEngineeringDark, BlockCrystalStorageItem.class);
		reg.addBlock(blockMysticFarmland);
		reg.registerObjects();

		reg.addBlockRenderer(blockCrystalStorage, new BlockCrystalStorageRenderer(blockCrystalStorage.getRenderType()));
		reg.addBlockRenderer(blockCrystalStorageBrick, new BlockCrystalStorageRenderer(blockCrystalStorageBrick.getRenderType()));
		reg.addBlockRenderer(blockCrystalStoragePlate, new BlockCrystalStorageRenderer(blockCrystalStoragePlate.getRenderType()));
		reg.addBlockRenderer(blockCrystalStoragePlatform, new BlockCrystalStorageRenderer(blockCrystalStoragePlatform.getRenderType()));
		reg.addBlockRenderer(blockCrystalStorageShield, new BlockCrystalStorageRenderer(blockCrystalStorageShield.getRenderType()));
		reg.addBlockRenderer(blockCrystalStorageStructure, new BlockCrystalStorageRenderer(blockCrystalStorageStructure.getRenderType()));
		reg.addBlockRenderer(blockCrystalStorageBrickEngineeringLight, new BlockCrystalStorageRenderer(blockCrystalStorageBrickEngineeringLight.getRenderType()));
		reg.addBlockRenderer(blockCrystalStorageBrickEngineeringDark, new BlockCrystalStorageRenderer(blockCrystalStorageBrickEngineeringDark.getRenderType()));
		reg.addBlockRenderer(blockMysticFarmland, new BlockMysticFarmlandRenderer());
		reg.registerRenderers();
	}

	public static void postInit() {
	}
}