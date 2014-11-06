package T145.magistics.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.ThaumcraftApi;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.config.external.ModHandler;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.items.relics.ItemEldritchKeystone;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagisticsConfig extends MagisticsLogger {
	public static Configuration config;

	public static boolean colored_names, debug, low_gfx;

	public static final String itemName[] = {
		"mystic_resources", "cruel_mask", "eldritch_keystone", "dawnstone"
	}, baubleName[] = {
		"bauble.amulet_dismay", "bauble.amulet_life", "bauble.belt_cleansing", "bauble.belt_vigor"
	}, blockName[] = {
		"ender_hungry_chest", "metal_hungry_chest", "infusion_workbench"
	};

	public static Item items[] = {
		new ItemResources().setHasSubtypes(true).setUnlocalizedName(itemName[0]),
		new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName(itemName[1]),
		new ItemEldritchKeystone().setHasSubtypes(true).setUnlocalizedName(itemName[2]),
		new ItemDawnstone().setUnlocalizedName(itemName[3]),
		new ItemAmuletDismay().setMaxDamage(50).setUnlocalizedName(baubleName[0]),
		new ItemAmuletLife().setMaxDamage(60).setUnlocalizedName(baubleName[1]),
		new ItemBeltCleansing().setMaxDamage(50).setUnlocalizedName(baubleName[2]),
		new ItemBeltVigor().setMaxDamage(100).setUnlocalizedName(baubleName[3])
	};

	public static Block blocks[] = {
		new BlockChestHungryEnder().setBlockName(blockName[0]).setHardness(22.5F).setLightLevel(0.5F).setResistance(1000F).setStepSound(Block.soundTypePiston),
		new BlockChestHungryMetal().setBlockName(blockName[1]).setHardness(3.0F)
	};

	public static Class tiles[] = {
		TileChestHungryEnder.class,
		TileChestHungryMetal.class
	};

	public static void preInit(File configFile) {
		config = new Configuration(configFile);
		sync();
	}

	public static void sync() {
		try {
			config.load();
			colored_names = config.getBoolean("colored_names", config.CATEGORY_GENERAL, true, "Toggles whether or not blocks have colored names like in Thaumcraft 2.");
			debug = config.getBoolean("debug", config.CATEGORY_GENERAL, true, "Toggles the log output of the mod. Great for development, though some people may like logs more silent than others.");
			low_gfx = config.getBoolean("low_gfx", config.CATEGORY_GENERAL, false, "Toggles or lessens graphical effects created by some blocks & items; great for low-end computers.");
		} catch (Exception e) {
			error("An error has occurred while loading configuration properties!", e);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public static void init() {
		for (Item item : items)
			GameRegistry.registerItem(item.setCreativeTab(Magistics.tabMagistics), item.getUnlocalizedName());
		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, tile.getSimpleName());
		GameRegistry.registerBlock(blocks[0], blockName[0]);
		ModHandler.init();
	}
}