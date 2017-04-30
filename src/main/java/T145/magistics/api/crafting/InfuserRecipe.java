package T145.magistics.api.crafting;

import net.minecraft.item.ItemStack;

public class InfuserRecipe {

	private final ItemStack result;
	private final ItemStack[] components;
	private final float quintCost;
	private final boolean dark;

	public InfuserRecipe(ItemStack result, ItemStack[] components, float quintCost, boolean dark) {
		this.result = result;
		this.components = components;
		this.quintCost = quintCost;
		this.dark = dark;
	}

	public ItemStack getResult() {
		return result;
	}

	public ItemStack[] getComponents() {
		return components;
	}

	public float getCost() {
		return quintCost;
	}

	public boolean isDark() {
		return dark;
	}

	public boolean matches(ItemStack[] recipe, boolean isDark) {
		int matches = 0;

		if (isDark == isDark()) {
			for (int i = isDark ? 1 : 2; i < recipe.length; ++i) {
				for (ItemStack component : components) {
					if (RecipeRegistry.areItemStacksEqual(component, recipe[i])) {
						++matches;
					}
				}
			}
		}

		return matches == components.length;
	}
}