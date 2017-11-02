package T145.magistics.api.crafting;

import T145.magistics.api.MagisticsApi;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InfuserRecipe {

	private final ItemStack result;
	private final ItemStack[] recipe;
	private final float cost;
	private final boolean dark;

	public InfuserRecipe(ItemStack result, ItemStack[] recipe, float cost, boolean dark) {
		this.result = result;
		this.recipe = recipe;
		this.cost = cost;
		this.dark = dark;
	}

	public ItemStack getResult() {
		return result;
	}

	public ItemStack[] getComponents() {
		return recipe;
	}

	public float getCost() {
		return cost;
	}

	public boolean isDark() {
		return dark;
	}

	public boolean matches(NonNullList<ItemStack> invRecipe, boolean isDark) {
		short matches = 0;

		if (isDark == isDark()) {
			int slotOffset = isDark ? 1 : 2;

			for (int slot = slotOffset; slot < invRecipe.size(); ++slot) {
				for (ItemStack ingredient : recipe) {
					if (MagisticsApi.areItemStacksEqual(ingredient, invRecipe.get(slot))) {
						++matches;
					}
				}
			}
		}

		return matches == recipe.length;
	}
}