package T145.magistics.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.config.external.ModHandler;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDeath;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.baubles.ItemRingSouls;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagisticsConfig {
	public static Configuration config;

	public static boolean colored_names, debug, low_gfx;

	public static final String itemName[] = {
		"mystic_resources", "cruel_mask", "bauble.amulet_death", "bauble.amulet_life", "bauble.belt_cleansing", "bauble.belt_vigor", "bauble.ring_souls"
	}, blockName[] = {
		"ender_hungry_chest"
	};

	public static Item items[] = {
		new ItemResources().setCreativeTab(Magistics.tabMagistics).setHasSubtypes(true).setMaxDamage(0).setUnlocalizedName(itemName[0]),
		new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setCreativeTab(Magistics.tabMagistics).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName(itemName[1]),
		new ItemAmuletDeath().setCreativeTab(Magistics.tabMagistics).setMaxDamage(50).setUnlocalizedName(itemName[2]),
		new ItemAmuletLife().setCreativeTab(Magistics.tabMagistics).setMaxDamage(100).setUnlocalizedName(itemName[3]),
		new ItemBeltCleansing().setCreativeTab(Magistics.tabMagistics).setMaxDamage(50).setUnlocalizedName(itemName[4]),
		new ItemBeltVigor().setCreativeTab(Magistics.tabMagistics).setMaxDamage(100).setUnlocalizedName(itemName[5]),
		new ItemRingSouls().setCreativeTab(Magistics.tabMagistics).setMaxDamage(51).setUnlocalizedName(itemName[6]),
	};

	public static Block blocks[] = {
		new BlockChestHungryEnder().setBlockName(blockName[0]).setCreativeTab(Magistics.tabMagistics).setHardness(22.5F).setLightLevel(0.5F).setResistance(1000F).setStepSound(Block.soundTypePiston)
	};

	public static Class tiles[] = {
		TileChestHungryEnder.class
	};

	public static void preInit(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();
			colored_names = config.getBoolean("colored_names", config.CATEGORY_GENERAL, false, "Toggles whether or not blocks have colored names like in Thaumcraft 2.");
			debug = config.getBoolean("debug", config.CATEGORY_GENERAL, true, "Toggles the log output of the mod. Great for development, though some people may like logs more silent than others.");
			low_gfx = config.getBoolean("low_gfx", config.CATEGORY_GENERAL, false, "Toggles or lessens graphical effects created by some blocks & items; great for low-end computers.");
		} catch (Exception e) {
			Magistics.logger.log(Level.ERROR, "An error has occurred while loading configuration properties!", e);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public static void init() {
		for (Item item : items)
			GameRegistry.registerItem(item, item.getUnlocalizedName());
		for (Class tileEntity : tiles)
			GameRegistry.registerTileEntity(tileEntity, tileEntity.getSimpleName());
		GameRegistry.registerBlock(blocks[0], blockName[0]);
	}

	public static void postInit() {
		ModHandler.init();

		ThaumcraftApi.registerComplexObjectTag(new ItemStack(MagisticsConfig.blocks[0], 1, 32767), new AspectList().merge(Aspect.EXCHANGE, 2).merge(Aspect.TRAVEL, 2).merge(Aspect.VOID, 4));

		ResearchCategories.registerCategory(Magistics.modid.toUpperCase(), new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTENDER", new ItemStack(MagisticsConfig.blocks[0]), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));

		new ResearchItem("HUNGRYCHESTENDER", Magistics.modid.toUpperCase(), new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3).add(Aspect.EXCHANGE, 2).add(Aspect.TRAVEL, 2).add(Aspect.VOID, 4), -1, 0, 1, new ItemStack(MagisticsConfig.blocks[0])).setPages(new ResearchPage("tc.research_page.HUNGRYCHESTENDER.1"), new ResearchPage(ConfigResearch.recipes.get("HungryEnderChest").toString())).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
		ThaumcraftApi.addWarpToResearch("HUNGRYCHESTENDER", 1);
	}
}