package T145.magistics.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ThaumcraftApi;
import T145.magistics.common.Magistics;
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
import cpw.mods.fml.common.registry.GameRegistry;

public class Config {
	public static Configuration config;

	public static final String itemName[] = {
		"mystic_resources", "cruel_mask", "bauble.amulet_death", "bauble.amulet_life", "bauble.belt_cleansing", "bauble.belt_vigor", "bauble.ring_souls", "arcane_seal"
	}, blockName[] = {
		"fragile_apparatus", "metal_apparatus", "stone_apparatus"
	};

	public static Item item[] = {
		new ItemResources().setCreativeTab(Magistics.tabMagistics).setHasSubtypes(true).setMaxDamage(0).setUnlocalizedName(itemName[0]),
		new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setCreativeTab(Magistics.tabMagistics).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName(itemName[1]),
		new ItemAmuletDeath().setMaxDamage(50).setUnlocalizedName(itemName[2]),
		new ItemAmuletLife().setMaxDamage(100).setUnlocalizedName(itemName[3]),
		new ItemBeltCleansing().setMaxDamage(50).setUnlocalizedName(itemName[4]),
		new ItemBeltVigor().setMaxDamage(100).setUnlocalizedName(itemName[5]),
		new ItemRingSouls().setMaxDamage(51).setUnlocalizedName(itemName[6]),
		new ItemSeal().setCreativeTab(Magistics.tabMagistics).setMaxDamage(0).setUnlocalizedName(itemName[7])
	};

	public static Block block[] = {
		new BlockFragileApparatus().setBlockName(blockName[0]).setCreativeTab(Magistics.tabMagistics),
		new BlockMetalApparatus().setBlockName(blockName[1]).setCreativeTab(Magistics.tabMagistics),
		new BlockStoneApparatus().setBlockName(blockName[2]).setCreativeTab(Magistics.tabMagistics)
	};

	private static Class blockItem[] = {
		BlockFragileApparatusItem.class,
		BlockMetalApparatusItem.class,
		BlockStoneApparatusItem.class
	};

	public static void preInit(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();
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
		ModHandler.init();
	}

	public static void postInit() {
		ThaumcraftHandler.init();
	}
}