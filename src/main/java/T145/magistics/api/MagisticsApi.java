package T145.magistics.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

public class MagisticsApi {

	public static final int MAGIC_STACK_META = 32767;

	private static Map<ItemStack, Float> crucibleRecipes = new HashMap<ItemStack, Float>();

	public static void addCrucibleRecipe(ItemStack input, float quintOutput) {
		crucibleRecipes.put(input, quintOutput);
	}

	public static float getCrucibleResult(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (Entry<ItemStack, Float> entry : crucibleRecipes.entrySet()) {
				if (ItemStackUtils.areItemStacksEqual(stack, entry.getKey())) {
					return entry.getValue();
				}
			}
		}

		return 0F;
	}
}