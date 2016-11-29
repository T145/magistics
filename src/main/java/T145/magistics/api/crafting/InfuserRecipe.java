package T145.magistics.api.crafting;

import T145.magistics.api.InventoryHelper;
import net.minecraft.item.ItemStack;

public class InfuserRecipe {

	private ItemStack result;
	private ItemStack[] components;
	private float visCost;
	private float miasmaCost;

	public InfuserRecipe(ItemStack result, ItemStack[] components, float visCost) {
		this.result = result;
		this.components = components;
		this.visCost = visCost;
	}

	public InfuserRecipe(ItemStack result, ItemStack[] components, float visCost, float miasmaCost) {
		this(result, components, visCost);
		this.miasmaCost = miasmaCost;
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

	public float getMiasmaCost() {
		return miasmaCost;
	}

	public boolean isDark() {
		return miasmaCost > 0F;
	}

	public boolean matches(ItemStack[] recipe, boolean isDark) {
		int matches = 0;

		if (isDark == isDark()) {
			for (int i = isDark ? 1 : 2; i < recipe.length; ++i) {
				for (ItemStack component : components) {
					if (InventoryHelper.areStacksEqual(component, recipe[i])) {
						++matches;
					}
				}
			}
		}

		return matches == components.length;
	}
}