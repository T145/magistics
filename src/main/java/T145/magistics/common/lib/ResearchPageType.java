package T145.magistics.common.lib;

import net.minecraft.item.crafting.IRecipe;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigResearch;

public class ResearchPageType {
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
		for (int i = 0; i < count; i++)
			recipes[i] = (InfusionRecipe) ConfigResearch.recipes.get(name + i);
		return new ResearchPage(recipes);
	}

	public static ResearchPage enchantPage(String name) {
		return new ResearchPage((InfusionEnchantmentRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage crucibleRecipePage(String name) {
		return new ResearchPage((CrucibleRecipe) ConfigResearch.recipes.get(name));
	}
}