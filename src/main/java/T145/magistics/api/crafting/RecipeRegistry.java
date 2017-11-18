package T145.magistics.api.crafting;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

public class RecipeRegistry {

	private static final HashMap<ItemStack, Short> CRUCIBLE_RECIPES = new HashMap<>();

	private RecipeRegistry() {}

	public static void addCrucibleRecipe(ItemStack input, short output) {
		CRUCIBLE_RECIPES.put(input, output);
	}

	public static short getCrucibleOutput(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (Entry<ItemStack, Short> entry : CRUCIBLE_RECIPES.entrySet()) {
				if (ItemStack.areItemStacksEqual(stack, entry.getKey())) {
					return entry.getValue();
				}
			}
		}

		return 0;
	}
}