package T145.magistics.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import T145.magistics.api.crafting.InfuserRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;

public class MagisticsApi {

	public static enum AuraType {
		LOW, HIGH, GOOD, BAD;
	}

	private static Map<ItemStack, Float> crucibleRecipes = new HashMap<ItemStack, Float>();
	private static List<InfuserRecipe> infuserRecipes = new ArrayList<InfuserRecipe>();
	private static Map<Biome, AuraType> auraTypes = new HashMap<Biome, AuraType>();

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
			if (InventoryHelper.areStacksEqual(stack, entry.getKey())) {
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

	public static void registerBiomeAura(Biome biome, AuraType auraType) {
		auraTypes.put(biome, auraType);
	}

	public static Map<Biome, AuraType> getRegisteredBiomeAuras() {
		return auraTypes;
	}
}