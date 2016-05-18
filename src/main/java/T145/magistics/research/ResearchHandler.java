package T145.magistics.research;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.Magistics;

public class ResearchHandler {
	public static void registerResearch() {
		ResourceLocation tab = new ResourceLocation("magistics", "textures/gui/thaumonomicon/tab.png");
		ResourceLocation background = new ResourceLocation("magistics", "textures/gui/thaumonomicon/bg.png");
		ResearchCategories.registerCategory(Magistics.MODID, tab, background);
	}

	public static ResearchPage recipePage(String name) {
		return new ResearchPage((IRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage arcaneRecipePage(String name) {
		return new ResearchPage((IArcaneRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage infusionPage(String name) {
		return new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage infusionPage(String name, int count) {
		InfusionRecipe[] recipes = new InfusionRecipe[count];

		for (int i = 0; i < count; i++) {
			recipes[i] = (InfusionRecipe) ConfigResearch.recipes.get(name + i);
		}

		return new ResearchPage(recipes);
	}

	public static ResearchPage enchantPage(String name) {
		return new ResearchPage((InfusionEnchantmentRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage crucibleRecipePage(String name) {
		return new ResearchPage((CrucibleRecipe) ConfigResearch.recipes.get(name));
	}
}