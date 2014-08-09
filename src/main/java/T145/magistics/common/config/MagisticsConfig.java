package T145.magistics.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ThaumcraftApi;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockChestHungryMod;
import T145.magistics.common.blocks.BlockChestHungryModItem;
import T145.magistics.common.blocks.BlockFragileApparatus;
import T145.magistics.common.blocks.BlockFragileApparatusItem;
import T145.magistics.common.blocks.BlockMetalApparatus;
import T145.magistics.common.blocks.BlockMetalApparatusItem;
import T145.magistics.common.blocks.BlockStoneApparatus;
import T145.magistics.common.blocks.BlockStoneApparatusItem;
import T145.magistics.common.config.external.ModHandler;
import T145.magistics.common.config.external.ThaumcraftHandler;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDeath;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.baubles.ItemRingSouls;
import T145.magistics.common.items.relics.ItemSeal;
import T145.magistics.common.tiles.TileChestHungryMod;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagisticsConfig {
	public static Configuration config;

	public static boolean colored_names;

	public static CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid.toLowerCase()) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.anvil);
		}
	};

	public static final String itemName[] = {
		"mystic_resources", "cruel_mask", "bauble.amulet_death", "bauble.amulet_life", "bauble.belt_cleansing", "bauble.belt_vigor", "bauble.ring_souls", "arcane_seal"
	}, blockName[] = {
		"hungry_chest_mod", "fragile_apparatus", "metal_apparatus", "stone_apparatus"
	};

	public static Item item[] = {
		new ItemResources().setCreativeTab(tabMagistics).setHasSubtypes(true).setMaxDamage(0).setUnlocalizedName(itemName[0]),
		new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setCreativeTab(tabMagistics).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName(itemName[1]),
		new ItemAmuletDeath().setCreativeTab(tabMagistics).setMaxDamage(50).setUnlocalizedName(itemName[2]),
		new ItemAmuletLife().setCreativeTab(tabMagistics).setMaxDamage(100).setUnlocalizedName(itemName[3]),
		new ItemBeltCleansing().setCreativeTab(tabMagistics).setMaxDamage(50).setUnlocalizedName(itemName[4]),
		new ItemBeltVigor().setCreativeTab(tabMagistics).setMaxDamage(100).setUnlocalizedName(itemName[5]),
		new ItemRingSouls().setCreativeTab(tabMagistics).setMaxDamage(51).setUnlocalizedName(itemName[6]),
		new ItemSeal().setCreativeTab(tabMagistics).setMaxDamage(0).setUnlocalizedName(itemName[7])
	};

	public static Block block[] = {
		new BlockChestHungryMod().setBlockName(blockName[0]).setCreativeTab(tabMagistics),
		new BlockFragileApparatus().setBlockName(blockName[1]).setCreativeTab(tabMagistics),
		new BlockMetalApparatus().setBlockName(blockName[2]).setCreativeTab(tabMagistics),
		new BlockStoneApparatus().setBlockName(blockName[3]).setCreativeTab(tabMagistics)
	};

	private static Class blockItem[] = {
		BlockChestHungryModItem.class,
		BlockFragileApparatusItem.class,
		BlockMetalApparatusItem.class,
		BlockStoneApparatusItem.class
	};

	public static Class tile[] = {
		TileChestHungryMod.class
	};

	public static void preInit(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();
			colored_names = config.get(config.CATEGORY_GENERAL, "colored_names", false, "Toggles whether or not the blocks have colored names like in Thaumcraft 2.").getBoolean(colored_names);
		} catch (Exception e) {
			Magistics.logger.log(Level.ERROR, "An error has occurred while loading configuration properties!", e);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public static void init() {
		for (int i = 0; i <= item.length; i++)
			GameRegistry.registerItem(item[i], itemName[i]);
		for (int j = 0; j <= block.length; j++)
			GameRegistry.registerBlock(block[j], blockItem[j], blockName[j]);
		for (int k = 0; k <= tile.length; k++)
			GameRegistry.registerTileEntity(tile[k], "magistics." + tile[k].getName());
		ModHandler.init();
	}

	public static void postInit() {
		ThaumcraftHandler.init();
	}
}