package T145.magistics.api.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.utils.InventoryUtils;

public class InfuserRecipe {
	protected String research;
	protected AspectList aspects;
	private ItemStack result;
	private ItemStack[] ingredients;
	private int burnTime;
	private boolean isDark;

	public InfuserRecipe(String research, AspectList aspects, ItemStack result, ItemStack[] ingredients, int burnTime, boolean isDark) {
		this.research = research;
		this.aspects = aspects;
		this.result = result;
		this.ingredients = ingredients;
		this.burnTime = burnTime;
		this.isDark = isDark;
	}

	public InfuserRecipe(String research, AspectList aspects, ItemStack result, ItemStack[] ingredients, int burnTime) {
		this(research, aspects, result, ingredients, burnTime, false);
	}

	public ItemStack getResult() {
		return result;
	}

	public AspectList getAspects() {
		return aspects;
	}

	public ItemStack[] getIngredients() {
		return ingredients;
	}

	public int getBurnTime() {
		return burnTime;
	}

	public boolean isDark() {
		return isDark;
	}

	public boolean isResearched(EntityPlayer player) {
		return research.length() > 0 && player != null && ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), research);
	}

	public boolean matches(EntityPlayer player, ItemStack[] recipe, boolean isDark) {
		ItemStack[] ingredients = this.ingredients;
		int matches = 0;

		if (isResearched(player) && isDark == isDark()) {
			for (int index = isDark ? 1 : 2; index < recipe.length; ++index) {
				ItemStack match = recipe[index];

				for (ItemStack ingredient : ingredients) {
					if (InventoryUtils.areItemStacksEqualStrict(match, ingredient)) {
						++matches;
					}
				}
			}
		}

		return ingredients.length == matches;
	}
}