package T145.magistics.api.crafting;

import T145.magistics.lib.utils.InventoryUtils;
import net.minecraft.item.ItemStack;

public class InfuserRecipe {

	private ItemStack result;
	private ItemStack[] components;
	private float visCost;
	private boolean isDark;

	public InfuserRecipe(ItemStack result, ItemStack[] components, float visCost, boolean isDark) {
		this.result = result;
		this.components = components;
		this.visCost = visCost;
		this.isDark = isDark;
	}

	public InfuserRecipe(ItemStack result, ItemStack[] components, float visCost) {
		this(result, components, visCost, false);
	}

	public ItemStack getResult() {
		return result;
	}

	public ItemStack[] getComponents() {
		return components;
	}

	public float getCost() {
		return visCost;
	}

	public boolean isDark() {
		return isDark;
	}

	public boolean matches(ItemStack[] recipe, boolean isDark) {
		int matches = 0;

		if (isDark == isDark()) {
			for (int i = isDark ? 1 : 2; i < recipe.length; ++i) {
				for (ItemStack component : components) {
					if (InventoryUtils.areStacksEqual(component, recipe[i])) {
						++matches;
					}
				}
			}
		}

		return matches == components.length;
	}
}