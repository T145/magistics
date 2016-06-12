package T145.magistics.lib.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.utils.InventoryUtils;

public class InfuserRecipes {
	private static final InfuserRecipes infusingBase = new InfuserRecipes();
	private List<MagisticsRecipe> recipes = new ArrayList<MagisticsRecipe>();

	public static final InfuserRecipes infusing() {
		return infusingBase;
	}

	public void addInfusingRecipe(ItemStack result, AspectList cost, ItemStack[] recipe, int burnTime, boolean isDark) {
		recipes.add(new MagisticsRecipe(result, cost, recipe, burnTime, isDark));
	}

	public void addInfusingRecipe(ItemStack result, AspectList cost, ItemStack[] recipe, int burnTime) {
		addInfusingRecipe(result, cost, recipe, burnTime, false);
	}

	public MagisticsRecipe getMatchingRecipe(ItemStack[] recipe, boolean isDark) {
		MagisticsRecipe match = null;

		for (MagisticsRecipe entry : recipes) {
			if (areRecipesEqual(recipe, entry.getRecipe()) && isDark == entry.isDark()) {
				match = entry;
			}
		}

		return match;
	}

	private boolean areRecipesEqual(ItemStack[] input, ItemStack[] entries) {
		int matches = 0;

		for (int i = 2; i < input.length; ++i) {
			ItemStack match = input[i];

			for (ItemStack entry : entries) {
				if (InventoryUtils.areItemStacksEqualStrict(match, entry)) {
					++matches;
				}
			}
		}

		return entries.length == matches;
	}
}