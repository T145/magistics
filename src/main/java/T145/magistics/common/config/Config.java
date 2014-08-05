package T145.magistics.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ThaumcraftApi;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockStoneApparatus;
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

public class Config {
	public static Configuration config;

	public static final String itemName[] = {
		"mystic_resources", "cruel_mask", "bauble.amulet_death", "bauble.amulet_life", "bauble.belt_cleansing", "bauble.belt_vigor", "bauble.ring_souls", "arcane_seal"
	}, blockName[] = {
		"stone_apparatus"
	};

	public static Item items[] = {
		new ItemResources().setCreativeTab(Magistics.tabMagistics).setHasSubtypes(true).setMaxDamage(0).setUnlocalizedName(itemName[0]),
		new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setCreativeTab(Magistics.tabMagistics).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName(itemName[1]),
		new ItemAmuletDeath().setMaxDamage(50).setUnlocalizedName(itemName[2]),
		new ItemAmuletLife().setMaxDamage(100).setUnlocalizedName(itemName[3]),
		new ItemBeltCleansing().setMaxDamage(50).setUnlocalizedName(itemName[4]),
		new ItemBeltVigor().setMaxDamage(100).setUnlocalizedName(itemName[5]),
		new ItemRingSouls().setMaxDamage(51).setUnlocalizedName(itemName[6]),
		new ItemSeal().setCreativeTab(Magistics.tabMagistics).setMaxDamage(0).setUnlocalizedName(itemName[7])
	};

	public static Block blocks[] = {
		new BlockStoneApparatus().setBlockName(blockName[0])
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
		ModHandler.init();
	}

	public static void postInit() {
		ThaumcraftHandler.init();
	}
}