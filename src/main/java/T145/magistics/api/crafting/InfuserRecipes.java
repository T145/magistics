package T145.magistics.api.crafting;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.crafting.recipes.InfuserRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class InfuserRecipes {

	public static final InfuserRecipes INSTANCE = new InfuserRecipes();
	private static List<InfuserRecipe> recipes = new ArrayList<InfuserRecipe>();

	public static void register() {
		addRecipe(new ItemStack(Blocks.STONE), new ItemStack[] { new ItemStack(Blocks.DIRT), new ItemStack(Blocks.SAND) }, 20F);
	}

	private static void addRecipe(ItemStack result, ItemStack[] components, float visCost, boolean isDark) {
		recipes.add(new InfuserRecipe(result, components, visCost, isDark));
	}

	public static void addRecipe(ItemStack result, ItemStack[] components, float visCost) {
		addRecipe(result, components, visCost, false);
	}

	public static void addDarkRecipe(ItemStack result, ItemStack[] components, float visCost) {
		addRecipe(result, components, visCost, true);
	}

	public static InfuserRecipe getMatchingInfuserRecipe(ItemStack[] components, boolean isDark) {
		for (InfuserRecipe recipe : recipes) {
			if (recipe.matches(components, isDark)) {
				return recipe;
			}
		}

		return null;
	}
}