package T145.magistics.common.lib;

import net.minecraft.item.crafting.IRecipe;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigResearch;

public class ResearchPages {
	public static ResearchPage craftingRecipe(String name) {
		return new ResearchPage((IRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage arcaneRecipe(String name) {
		return new ResearchPage((IArcaneRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage infusionRecipe(String name) {
		return new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage infusionEnchantmentRecipe(String name) {
		return new ResearchPage((InfusionEnchantmentRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage crucibleRecipe(String name) {
		return new ResearchPage((CrucibleRecipe) ConfigResearch.recipes.get(name));
	}
}