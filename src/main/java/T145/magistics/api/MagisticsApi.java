package T145.magistics.api;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.recipes.CrucibleRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MagisticsApi {

	private static List<CrucibleRecipe> crucibleRecipes = new ArrayList<CrucibleRecipe>();

	public static void addCrucibleRecipe(ItemStack stack, float vis) {
		crucibleRecipes.add(new CrucibleRecipe(stack, vis));
	}

	public static void addCrucibleRecipe(Item item, float vis) {
		addCrucibleRecipe(new ItemStack(item), vis);
	}

	public static void addCrucibleRecipe(Item item, int meta, float vis) {
		addCrucibleRecipe(new ItemStack(item, 1, meta), vis);
	}

	public static void addCrucibleRecipe(Item item, int quantity, int meta, float vis) {
		addCrucibleRecipe(new ItemStack(item, quantity, meta), vis);
	}

	public static void addCrucibleRecipe(Block block, float vis) {
		addCrucibleRecipe(new ItemStack(block), vis);
	}

	public static void addCrucibleRecipe(Block block, int meta, float vis) {
		addCrucibleRecipe(new ItemStack(block, 1, meta), vis);
	}

	public static void addCrucibleRecipe(Block block, int quantity, int meta, float vis) {
		addCrucibleRecipe(new ItemStack(block, quantity, meta), vis);
	}

	public static CrucibleRecipe getMatchingCrucibleRecipe(ItemStack stack) {
		for (CrucibleRecipe recipe : crucibleRecipes) {
			if (ItemStack.areItemStacksEqual(stack, recipe.getStack())) {
				return recipe;
			}
		}
		return null;
	}
}