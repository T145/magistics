package T145.magistics.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

public class MagisticsApi {

	private static Map<ItemStack, Float> crucibleRecipes = new HashMap<ItemStack, Float>();

	public static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB) {
		return stackA.isEmpty() && stackB.isEmpty() ? true : (!stackA.isEmpty() && !stackB.isEmpty() ? isItemStackEqual(stackA, stackB) : false);
	}

	private static boolean isItemStackEqual(ItemStack first, ItemStack other) {
		return first.getItem() != other.getItem() ? false : (first.getItemDamage() != other.getItemDamage() ? false : (first.getTagCompound() == null && other.getTagCompound() != null ? false : (first.getTagCompound() == null || first.getTagCompound().equals(other.getTagCompound())) && first.areCapsCompatible(other)));
	}

	public static void addCrucibleRecipe(ItemStack input, float quintOutput) {
		crucibleRecipes.put(input, quintOutput);
	}

	public static float getCrucibleResult(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (Entry<ItemStack, Float> entry : crucibleRecipes.entrySet()) {
				if (areItemStacksEqual(stack, entry.getKey())) {
					return entry.getValue();
				}
			}
		}

		return 0F;
	}
}