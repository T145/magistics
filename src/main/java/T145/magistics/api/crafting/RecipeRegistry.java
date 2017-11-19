package T145.magistics.api.crafting;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

public class RecipeRegistry {

	private static final HashMap<ItemStack, Integer> CRUCIBLE_RECIPES = new HashMap<>();

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
}