package T145.magistics.common.config.external;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockChestHungryMetalItem;
import T145.magistics.common.config.MagisticsConfig;
import T145.magistics.common.config.MagisticsLogger;
import T145.magistics.common.lib.ResearchPages;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.ironchest.IronChest;

public class ModHandler {
	public static void init() {
		if (Loader.isModLoaded("BuildCraft|Transport")) {
			MagisticsLogger.log("Buildcraft detected; compatibility loaded.");
			//addFacades(MagisticsConfig.blocks[2].getUnlocalizedName(), 0, 1);
		}
		if (Loader.isModLoaded("IronChest")) {
			MagisticsLogger.log("IronChest detected; support loaded.");
			GameRegistry.registerBlock(MagisticsConfig.blocks[1], BlockChestHungryMetalItem.class, MagisticsConfig.blockName[1]);

			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 0), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.METAL, 4));
			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 1), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.METAL, 4).add(Aspect.GREED, 4));
			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 2), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.GREED, 2).add(Aspect.CRYSTAL, 4));
			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 3), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.METAL, 4).add(Aspect.EXCHANGE, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 4), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.METAL, 4).add(Aspect.GREED, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 5), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.GREED, 4).add(Aspect.CRYSTAL, 4).add(Aspect.ORDER, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 6), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.EARTH, 4).add(Aspect.FIRE, 4).add(Aspect.DARKNESS, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(IronChest.ironChestBlock, 1, 7), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.EARTH, 6));

			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 0), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.METAL, 4));
			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 1), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.METAL, 4).add(Aspect.GREED, 4));
			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 2), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.GREED, 2).add(Aspect.CRYSTAL, 4));
			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 3), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.METAL, 4).add(Aspect.EXCHANGE, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 4), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.METAL, 4).add(Aspect.GREED, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 5), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.GREED, 4).add(Aspect.CRYSTAL, 4).add(Aspect.ORDER, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 6), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.EARTH, 4).add(Aspect.FIRE, 4).add(Aspect.DARKNESS, 2));
			ThaumcraftApi.registerObjectTag(new ItemStack(MagisticsConfig.blocks[1], 1, 7), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 3).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.EARTH, 6));

			ConfigResearch.recipes.put("HungryCopperChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 3), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "WWW", "WTW", "WWW", 'W', "ingotCopper", 'T', ConfigBlocks.blockChestHungry));
			ConfigResearch.recipes.put("HungryIronChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 0), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "WWW", "WTW", "WWW", 'W', "ingotIron", 'T', ConfigBlocks.blockChestHungry));
			ConfigResearch.recipes.put("HungryIronChestAlt", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 0), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "BCB", "ABA", 'A', "ingotIron", 'B', "blockGlass", 'C', new ItemStack(MagisticsConfig.blocks[1], 1, 3)));
			ConfigResearch.recipes.put("HungrySilverChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 4), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "AAA", "ABA", "AAA", 'A', "ingotSilver", 'B', new ItemStack(MagisticsConfig.blocks[1], 1, 3)));
			ConfigResearch.recipes.put("HungrySilverChestAlt", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 4), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "BCB", "ABA", 'A', "ingotSilver", 'B', "blockGlass", 'C', new ItemStack(MagisticsConfig.blocks[1], 1, 0)));
			ConfigResearch.recipes.put("HungryGoldChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 1), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "AAA", "ABA", "AAA", 'A', "ingotGold", 'B', new ItemStack(MagisticsConfig.blocks[1], 1, 0)));
			ConfigResearch.recipes.put("HungryGoldChestAlt", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 1), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "BCB", "ABA", 'A', "ingotGold", 'B', "blockGlass", 'C', new ItemStack(MagisticsConfig.blocks[1], 1, 4)));
			ConfigResearch.recipes.put("HungryDiamondChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 2), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "AAA", "ABA", "CCC", 'A', "blockGlass", 'B', new ItemStack(MagisticsConfig.blocks[1], 1, 4), 'C', "gemDiamond"));
			ConfigResearch.recipes.put("HungryDiamondChestAlt", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 2), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "AAA", "BCB", "AAA", 'A', "blockGlass", 'B', "gemDiamond", 'C', new ItemStack(MagisticsConfig.blocks[1], 1, 1)));
			ConfigResearch.recipes.put("HungryCrystalChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 5), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "AAA", "ABA", "AAA", 'A', "blockGlass", 'B', new ItemStack(MagisticsConfig.blocks[1], 1, 2)));
			ConfigResearch.recipes.put("HungryObsidianChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 6), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "AAA", "ABA", "AAA", 'A', "blockObsidian", 'B', new ItemStack(MagisticsConfig.blocks[1], 1, 5)));
			ConfigResearch.recipes.put("HungryDirtChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTIRON", new ItemStack(MagisticsConfig.blocks[1], 1, 5), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "AAA", "ABA", "AAA", 'A', "blockDirt", 'B', ConfigBlocks.blockChestHungry));
		}
	}

	public static void postInit() {
		ResearchCategories.registerCategory(Magistics.modid.toUpperCase(), new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockChestHungry), new AspectList().add(Aspect.VOID, 2).add(Aspect.MOTION, 2).add(Aspect.AIR, 2).add(Aspect.HUNGER, 2).add(Aspect.TREE, 6));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigBlocks.blockArcaneFurnace), new AspectList().add(Aspect.FIRE, 8).add(Aspect.CRAFT, 4).add(Aspect.AURA, 4).add(Aspect.ENTROPY, 2));

		ThaumcraftApi.registerComplexObjectTag(new ItemStack(MagisticsConfig.blocks[0], 1, 32767), new AspectList().merge(Aspect.EXCHANGE, 2).merge(Aspect.TRAVEL, 2).merge(Aspect.VOID, 4));

		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYCHESTENDER", new ItemStack(MagisticsConfig.blocks[0]), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));

		new ResearchItem("HUNGRYCHESTENDER", Magistics.modid.toUpperCase(), new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3).add(Aspect.EXCHANGE, 2).add(Aspect.TRAVEL, 2).add(Aspect.VOID, 4), -1, 0, 2, new ItemStack(MagisticsConfig.blocks[0])).setPages(new ResearchPage("tc.research_page.HUNGRYCHESTENDER.1"), new ResearchPages().arcaneRecipe("HungryEnderChest")).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
		ThaumcraftApi.addWarpToResearch("HUNGRYCHESTENDER", 1);

		if (Loader.isModLoaded("IronChest"))
			new ResearchItem("HUNGRYCHESTIRON", Magistics.modid.toUpperCase(), new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), 1, 1, 1, new ItemStack(MagisticsConfig.blocks[1], 1, 32767)).setPages(new ResearchPage("tc.research_page.HUNGRYCHESTIRON.1"), new ResearchPages().arcaneRecipe("HungryIronChest"), new ResearchPages().arcaneRecipe("HungryIronChestAlt"), new ResearchPages().arcaneRecipe("HungryGoldChest"), new ResearchPages().arcaneRecipe("HungryGoldChestAlt"), new ResearchPages().arcaneRecipe("HungryDiamondChest"), new ResearchPages().arcaneRecipe("HungryDiamondChestAlt"), new ResearchPages().arcaneRecipe("HungryCopperChest"), new ResearchPages().arcaneRecipe("HungrySilverChest"), new ResearchPages().arcaneRecipe("HungrySilverChestAlt"), new ResearchPages().arcaneRecipe("HungryCrystalChest"), new ResearchPages().arcaneRecipe("HungryObsidianChest"), new ResearchPages().arcaneRecipe("HungryDirtChest")).setSecondary().registerResearchItem();
	}

	public static void addFacades(String blockName, int meta_start, int meta_end) {
		for (int meta = meta_start; meta <= meta_end; meta++)
			addFacade(blockName, meta);
	}

	public static void addFacade(String blockName, int meta) {
		MagisticsLogger.log("Adding facades for " + blockName + "." + meta);
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", blockName + "@" + meta);
	}
}