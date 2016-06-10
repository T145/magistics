package T145.magistics.lib.crafting;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;

public class MagisticsRecipe {
	private ItemStack result;
	private AspectList aspects;
	private ItemStack[] recipe;
	private int burnTime;
	private boolean isDark;

	public MagisticsRecipe(ItemStack result, AspectList aspects, ItemStack[] recipe, int burnTime, boolean isDark) {
		this.result = result;
		this.aspects = aspects;
		this.recipe = recipe;
		this.burnTime = burnTime;
		this.isDark = isDark;
	}

	public MagisticsRecipe(ItemStack result, AspectList aspects, ItemStack[] recipe, int burnTime) {
		this(result, aspects, recipe, burnTime, false);
	}

	public ItemStack getResult() {
		return result;
	}

	public AspectList getAspects() {
		return aspects;
	}

	public ItemStack[] getRecipe() {
		return recipe;
	}

	public int getBurnTime() {
		return burnTime;
	}

	public boolean isDark() {
		return isDark;
	}
}