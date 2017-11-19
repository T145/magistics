package T145.magistics.api.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RecipeRegistry {

	private static final HashMap<ItemStack, Integer> CRUCIBLE_RECIPES = new HashMap<>();
	private static final List<InfuserRecipe> INFUSER_RECIPES = new ArrayList<InfuserRecipe>();

	private RecipeRegistry() {}

	public static boolean areItemStacksEqual(ItemStack first, ItemStack other) {
		return first.isEmpty() && other.isEmpty() ? true : (!first.isEmpty() && !other.isEmpty() ? isItemStackEqual(first, other) : false);
	}

	private static boolean isItemStackEqual(ItemStack first, ItemStack other) {
		return first.getItem() != other.getItem() ? false : (first.getItemDamage() != other.getItemDamage() ? false : (first.getTagCompound() == null && other.getTagCompound() != null ? false : (first.getTagCompound() == null || first.getTagCompound().equals(other.getTagCompound())) && first.areCapsCompatible(other)));
	}

	public static void addCrucibleRecipe(ItemStack input, int output) {
		CRUCIBLE_RECIPES.put(input, output);
	}

	public static int getCrucibleOutput(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (Entry<ItemStack, Integer> entry : CRUCIBLE_RECIPES.entrySet()) {
				if (areItemStacksEqual(stack, entry.getKey())) {
					return entry.getValue();
				}
			}
		}

		return 0;
	}

	public static void addInfuserRecipe(ItemStack result, ItemStack[] components, float quintCost, boolean dark) {
		INFUSER_RECIPES.add(new InfuserRecipe(result, components, quintCost, dark));
	}

	@Nullable
	public static InfuserRecipe getMatchingInfuserRecipe(NonNullList<ItemStack> invRecipe, boolean isDark) {
		for (InfuserRecipe recipe : INFUSER_RECIPES) {
			if (recipe.matches(invRecipe, isDark)) {
				return recipe;
			}
		}

		return null;
	}
}