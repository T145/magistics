package T145.magistics.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.blocks.BlockChestHungryMetalItem;
import T145.magistics.common.blocks.BlockSortingChestHungry;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemical;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemicalItem;
import T145.magistics.common.blocks.BlockSortingChestHungryMetal;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileSortingChestHungry;
import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;
import T145.magistics.common.tiles.TileSortingChestHungryMetal;

import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public class CommonProxy {
	public static Item itemResources;
	public static Block blockChestHungry, blockChestHungryTrapped, blockChestHungryEnder, blockChestHungryAlchemical, blockChestHungryMetal, blockChestHungryRailcraft, blockSortingChestHungry, blockSortingChestHungryAlchemical, blockSortingChestHungryMetal;

	public void registerHandlers(Magistics instance) {
		FMLCommonHandler.instance().bus().register(instance);
	}

	public void registerObjects() {
		ConfigObjects config = ConfigObjects.getInstance();
		config.addItem(itemResources = new ItemResources().setUnlocalizedName("mystic_resources"));

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
		config.register();
	}
}