package T145.magistics.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import T145.magistics.api.crafting.InfuserRecipe;

public class MagisticsApi {
	private static List<InfuserRecipe> infuserRecipes = new ArrayList<InfuserRecipe>();

	private static void addInfusingRecipe(String research, AspectList aspects, ItemStack result, ItemStack[] ingredients, int burnTime, boolean isDark) {
		infuserRecipes.add(new InfuserRecipe(research, aspects, result, ingredients, burnTime, isDark));
	}

	public static void addInfusingRecipe(String research, AspectList aspects, ItemStack result, ItemStack[] ingredients, int burnTime) {
		addInfusingRecipe(research, aspects, result, ingredients, burnTime, false);
	}

	public static void addDarkInfusingRecipe(String research, AspectList aspects, ItemStack result, ItemStack[] ingredients, int burnTime) {
		addInfusingRecipe(research, aspects, result, ingredients, burnTime, true);
	}

	public static InfuserRecipe getMatchingInfuserRecipe(EntityPlayer player, ItemStack[] ingredients, boolean isDark) {
		InfuserRecipe match = null;

		for (InfuserRecipe recipe : infuserRecipes) {
			if (recipe.matches(player, ingredients, isDark)) {
				match = recipe;
			}
		}

		return match;
	}
}