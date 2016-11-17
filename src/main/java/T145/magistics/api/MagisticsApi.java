package T145.magistics.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.lib.utils.InventoryUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MagisticsApi {

	private static Map<ItemStack, Float> crucibleRecipes = new HashMap<ItemStack, Float>();
	private static List<InfuserRecipe> infuserRecipes = new ArrayList<InfuserRecipe>();

	public static void addCrucibleRecipe(ItemStack input, float visOutput) {
		crucibleRecipes.put(input, visOutput);
	}

	public static void addCrucibleRecipe(Block input, float visOutput) {
		addCrucibleRecipe(Item.getItemFromBlock(input), visOutput);
	}

	public static void addCrucibleRecipe(Item input, float visOutput) {
		addCrucibleRecipe(new ItemStack(input, 1, 32767), visOutput);
	}

	public static void addInfuserRecipe(ItemStack result, ItemStack[] components, float visCost, float miasmaCost) {
		infuserRecipes.add(new InfuserRecipe(result, components, visCost, miasmaCost));
	}

	public static void addInfuserRecipe(ItemStack result, ItemStack[] components, float visCost) {
		addInfuserRecipe(result, components, visCost, 0F);
	}

	public static float getCrucibleResult(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : crucibleRecipes.entrySet()) {
			if (InventoryUtils.areStacksEqual(stack, entry.getKey())) {
				return entry.getValue();
			}
		}

		return 0F;
	}

	@Nullable
	public static InfuserRecipe getMatchingInfuserRecipe(ItemStack[] components, boolean isDark) {
		for (InfuserRecipe recipe : infuserRecipes) {
			if (recipe.matches(components, isDark)) {
				return recipe;
			}
		}

		return null;
	}
}