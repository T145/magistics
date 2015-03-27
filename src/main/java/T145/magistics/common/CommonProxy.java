package T145.magistics.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.blocks.BlockChestHungryMetalItem;
import T145.magistics.common.blocks.BlockCrystalStorage;
import T145.magistics.common.blocks.BlockCrystalStorageEngineering;
import T145.magistics.common.blocks.BlockCrystalStorageEngineeringItem;
import T145.magistics.common.blocks.BlockCrystalStorageItem;
import T145.magistics.common.blocks.BlockCrystalStorageReinforced;
import T145.magistics.common.blocks.BlockCrystalStorageReinforcedItem;
import T145.magistics.common.blocks.BlockCrystalStorageStructure;
import T145.magistics.common.blocks.BlockCrystalStorageStructureItem;
import T145.magistics.common.blocks.BlockDarknessDetector;
import T145.magistics.common.blocks.BlockHungryStrongbox;
import T145.magistics.common.blocks.BlockLightDetector;
import T145.magistics.common.blocks.BlockSortingChestHungry;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemical;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemicalItem;
import T145.magistics.common.blocks.BlockSortingChestHungryMetal;
import T145.magistics.common.blocks.BlockTintedNitor;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.items.ItemMagisticsRecord;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.ItemTintedNitor;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileHungryStrongbox;
import T145.magistics.common.tiles.TileHungryStrongboxCreative;
import T145.magistics.common.tiles.TileSortingChestHungry;
import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;
import T145.magistics.common.tiles.TileSortingChestHungryMetal;
import T145.magistics.common.tiles.TileTintedNitor;
import cofh.thermalexpansion.block.strongbox.ItemBlockStrongbox;

import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public class CommonProxy {
	public static Item itemResources, itemDawnstone, itemTintedNitor, record_chamber, record_deception, record_ghost, record_gloom, record_halls, record_moonlight, record_plant, record_portal, record_queen, record_serpentine, record_unlight, record_auricom;
	public static Block blockChestHungry, blockChestHungryTrapped, blockChestHungryEnder, blockChestHungryAlchemical, blockChestHungryMetal, blockChestHungryRailcraft, blockSortingChestHungry, blockSortingChestHungryAlchemical, blockSortingChestHungryMetal, blockHungryStrongbox;
	public static Block crystalStorage, crystalStructure, crystalStorageReinforced, crystalStorageEngineering, lightDetector, darknessDetector, tintedNitor;

	public void registerHandlers(Magistics instance) {
		FMLCommonHandler.instance().bus().register(instance);
	}

	public void registerObjects() {
		ConfigObjects config = ConfigObjects.getInstance();
		config.addItem(itemResources = new ItemResources().setUnlocalizedName("mystic_resources"));
		config.addItem(itemDawnstone = new ItemDawnstone().setTextureName("magistics:dawnstone").setUnlocalizedName("dawnstone"));
		config.addItem(itemTintedNitor = new ItemTintedNitor().setTextureName("magistics:nitor_tinted").setUnlocalizedName("tinted_nitor"));

		config.addItem(record_chamber = new ItemMagisticsRecord("chamber").setUnlocalizedName("record_chamber").setTextureName("magistics:record/chamber"));
		config.addItem(record_deception = new ItemMagisticsRecord("deception").setUnlocalizedName("record_deception").setTextureName("magistics:record/deception"));
		config.addItem(record_ghost = new ItemMagisticsRecord("ghost").setUnlocalizedName("record_ghost").setTextureName("magistics:record/ghost"));
		config.addItem(record_gloom = new ItemMagisticsRecord("gloom").setUnlocalizedName("record_gloom").setTextureName("magistics:record/gloom"));
		config.addItem(record_halls = new ItemMagisticsRecord("halls").setUnlocalizedName("record_halls").setTextureName("magistics:record/halls"));
		config.addItem(record_moonlight = new ItemMagisticsRecord("moonlight").setUnlocalizedName("record_moonlight").setTextureName("magistics:record/moonlight"));
		config.addItem(record_plant = new ItemMagisticsRecord("plant").setUnlocalizedName("record_plant").setTextureName("magistics:record/plant"));
		config.addItem(record_portal = new ItemMagisticsRecord("portal").setUnlocalizedName("record_portal").setTextureName("magistics:record/portal"));
		config.addItem(record_queen = new ItemMagisticsRecord("queen").setUnlocalizedName("record_queen").setTextureName("magistics:record/queen"));
		config.addItem(record_serpentine = new ItemMagisticsRecord("serpentine").setUnlocalizedName("record_serpentine").setTextureName("magistics:record/serpentine"));
		config.addItem(record_unlight = new ItemMagisticsRecord("unlight").setUnlocalizedName("record_unlight").setTextureName("magistics:record/unlight"));

		config.addTile(TileTintedNitor.class);
		config.addBlock(tintedNitor = new BlockTintedNitor().setBlockTextureName("thaumcraft:blank").setStepSound(Block.soundTypeCloth));

		config.addBlock(crystalStorage = new BlockCrystalStorage().setBlockName("crystal_storage").setStepSound(Block.soundTypeGlass), BlockCrystalStorageItem.class);
		config.addBlock(crystalStorageReinforced = new BlockCrystalStorageReinforced().setBlockName("crystal_storage_reinforced").setStepSound(Block.soundTypeGlass), BlockCrystalStorageReinforcedItem.class);
		config.addBlock(crystalStorageEngineering = new BlockCrystalStorageEngineering().setBlockName("engineering_brick").setStepSound(Block.soundTypeMetal), BlockCrystalStorageEngineeringItem.class);
		config.addBlock(crystalStructure = new BlockCrystalStorageStructure().setBlockName("crystal_structure").setStepSound(Block.soundTypeGlass), BlockCrystalStorageStructureItem.class);

		config.addBlock(lightDetector = new BlockLightDetector().setBlockName("light_detector"));
		config.addBlock(darknessDetector = new BlockDarknessDetector().setBlockName("darkness_detector"));

		config.addTile(TileChestHungry.class);
		config.addBlock(blockChestHungry = new BlockChestHungry(0).setBlockName("hungry_chest"));
		config.addBlock(blockChestHungryTrapped = new BlockChestHungry(1).setBlockName("trapped_hungry_chest"));

		if (Loader.isModLoaded("EE3")) {
			config.addTile(TileChestHungryAlchemical.class);
			config.addBlock(blockChestHungryAlchemical = new BlockChestHungryAlchemical().setBlockName("hungry_alchemical_chest"), ItemBlockAlchemicalChest.class);
		}

		config.addTile(TileChestHungryEnder.class);
		config.addBlock(blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F));

		if (Loader.isModLoaded("IronChest")) {
			config.addTile(TileChestHungryMetal.class);
			config.addBlock(blockChestHungryMetal = new BlockChestHungryMetal().setBlockName("hungry_metal_chest").setHardness(3F), BlockChestHungryMetalItem.class);
		}

		if (Loader.isModLoaded("RefinedRelocation")) {
			config.addTile(TileSortingChestHungry.class);
			config.addBlock(blockSortingChestHungry = new BlockSortingChestHungry().setBlockName("sorting_hungry_chest"));

			if (Loader.isModLoaded("EE3")) {
				config.addTile(TileSortingChestHungryAlchemical.class);
				config.addBlock(blockSortingChestHungryAlchemical = new BlockSortingChestHungryAlchemical().setBlockName("sorting_hungry_alchemical_chest"), BlockSortingChestHungryAlchemicalItem.class);
			}

			if (Loader.isModLoaded("IronChest")) {
				config.addTile(TileSortingChestHungryMetal.class);
				config.addBlock(blockSortingChestHungryMetal = new BlockSortingChestHungryMetal().setBlockName("sorting_hungry_metal_chest"), BlockChestHungryMetalItem.class);
			}
		}

		if (Loader.isModLoaded("ThermalExpansion")) {
			config.addTile(TileHungryStrongbox.class);
			config.addTile(TileHungryStrongboxCreative.class);
			config.addBlock(blockHungryStrongbox = new BlockHungryStrongbox(), ItemBlockStrongbox.class);
		}

		config.register();
	}
}