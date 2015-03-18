package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.common.Magistics;

public class Research {
	private static Research instance = new Research();

	public static Research getInstance() {
		return instance;
	}

	private final ResourceLocation RESEARCH_BACKGROUND = new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png");

	public ResourceLocation getResearchBackground() {
		return RESEARCH_BACKGROUND;
	}

	private List<ResearchItem> research = new ArrayList<ResearchItem>();

	public void addResearch(ResearchItem item) {
		research.add(item);
	}

	public void addRecipe(String name, IArcaneRecipe recipe) {
		ConfigResearch.recipes.put(name, recipe);
	}

	public void addWarp(String name, int amount) {
		ThaumcraftApi.addWarpToResearch(name, amount);
	}

	public void addItemWarp(ItemStack is, int amount) {
		ThaumcraftApi.addWarpToItem(is, amount);
	}

	public static ResearchPage normal(String name) {
		return new ResearchPage((IRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage arcane(String name) {
		return new ResearchPage((IArcaneRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage infusion(String name) {
		return new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage infusion(String name, int count) {
		InfusionRecipe[] recipes = new InfusionRecipe[count];
		for (int i = 0; i < count; i++)
			recipes[i] = (InfusionRecipe) ConfigResearch.recipes.get(name + i);
		return new ResearchPage(recipes);
	}

	public static ResearchPage enchantment(String name) {
		return new ResearchPage((InfusionEnchantmentRecipe) ConfigResearch.recipes.get(name));
	}

	public static ResearchPage crucible(String name) {
		return new ResearchPage((CrucibleRecipe) ConfigResearch.recipes.get(name));
	}

	public void register() {
		ResearchCategories.registerCategory(Magistics.modid, new ResourceLocation("magistics", "textures/gui/tab.png"), RESEARCH_BACKGROUND);

		for (ResearchItem item : research)
			item.registerResearchItem();
	}
}