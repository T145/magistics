package T145.magistics.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import T145.magistics.api.MagisticsApiHelper;
import T145.magistics.api.crafting.InfuserRecipe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModRecipes {

	private static Map<ItemStack, Float> crucibleRecipes = new HashMap<ItemStack, Float>();
	private static List<InfuserRecipe> infuserRecipes = new ArrayList<InfuserRecipe>();

	public static void addCrucibleRecipe(ItemStack input, float quintOutput) {
		crucibleRecipes.put(input, quintOutput);
	}

	public static void addCrucibleRecipe(Block block, float quintOutput) {
		addCrucibleRecipe(new ItemStack(block), quintOutput);
	}

	public static void addCrucibleRecipe(Item item, float quintOutput) {
		addCrucibleRecipe(new ItemStack(item), quintOutput);
	}

	@Nonnull
	public static float getCrucibleResult(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (Entry<ItemStack, Float> entry : crucibleRecipes.entrySet()) {
				if (MagisticsApiHelper.areItemStacksEqual(stack, entry.getKey())) {
					return entry.getValue();
				}
			}
		}

		return 0F;
	}

	public static void addInfuserRecipe(ItemStack result, ItemStack[] components, float quintCost, boolean dark) {
		infuserRecipes.add(new InfuserRecipe(result, components, quintCost, dark));
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

	public static void registerRecipes() {
		registerCrucibleRecipes();
		registerInfuserRecipes();
	}

	private static void registerCrucibleRecipes() {
		addCrucibleRecipe(Items.STICK, 0.25F);
		addCrucibleRecipe(Items.CLAY_BALL, 1F);
		addCrucibleRecipe(Items.BRICK, 2F);
		addCrucibleRecipe(Items.FLINT, 1F);
		addCrucibleRecipe(Items.COAL, 2F);
		addCrucibleRecipe(Items.SNOWBALL, 0.25F);
		addCrucibleRecipe(new ItemStack(Items.DYE, 1, 0), 4F);
		addCrucibleRecipe(new ItemStack(Items.DYE, 1, 2), 4F);
		addCrucibleRecipe(new ItemStack(Items.DYE, 1, 3), 25F);
		addCrucibleRecipe(new ItemStack(Items.DYE, 1, 4), 9F);
		addCrucibleRecipe(Items.IRON_INGOT, 5F);
		addCrucibleRecipe(Items.BEETROOT_SEEDS, 4F);
		addCrucibleRecipe(Items.MELON_SEEDS, 4F);
		addCrucibleRecipe(Items.PUMPKIN_SEEDS, 4F);
		addCrucibleRecipe(Items.WHEAT_SEEDS, 4F);
		addCrucibleRecipe(Items.FEATHER, 4F);
		addCrucibleRecipe(Items.BONE, 4F);
		addCrucibleRecipe(Items.MELON, 2F);
		addCrucibleRecipe(Items.COOKED_BEEF, 5F);
		addCrucibleRecipe(Items.BEEF, 4F);
		addCrucibleRecipe(Items.COOKED_CHICKEN, 5F);
		addCrucibleRecipe(Items.CHICKEN, 4F);
		addCrucibleRecipe(Items.COOKED_PORKCHOP, 5F);
		addCrucibleRecipe(Items.PORKCHOP, 4F);
		addCrucibleRecipe(Items.COOKED_FISH, 5F);
		addCrucibleRecipe(Items.FISH, 4F);
		addCrucibleRecipe(Items.NETHER_WART, 4F);
		addCrucibleRecipe(Items.ROTTEN_FLESH, 4F);
		addCrucibleRecipe(Items.STRING, 4F);
		addCrucibleRecipe(Items.LEATHER, 4F);
		addCrucibleRecipe(Items.WHEAT, 4F);
		addCrucibleRecipe(Items.REEDS, 4F);
		addCrucibleRecipe(Items.GUNPOWDER, 10F);
		addCrucibleRecipe(Items.GLOWSTONE_DUST, 9F);
		addCrucibleRecipe(Items.REDSTONE, 6F);
		addCrucibleRecipe(Items.MILK_BUCKET, 18F);
		addCrucibleRecipe(Items.WATER_BUCKET, 16F);
		addCrucibleRecipe(Items.LAVA_BUCKET, 17F);
		addCrucibleRecipe(Items.EGG, 5F);
		addCrucibleRecipe(Items.GOLD_INGOT, 27F);
		addCrucibleRecipe(Items.GOLD_NUGGET, 3F);
		addCrucibleRecipe(Items.SLIME_BALL, 25F);
		addCrucibleRecipe(Items.APPLE, 4F);
		addCrucibleRecipe(Items.DIAMOND, 64F);
		addCrucibleRecipe(Items.ENDER_PEARL, 64F);
		addCrucibleRecipe(Items.RECORD_13, 100F);
		addCrucibleRecipe(Items.RECORD_CAT, 100F);
		addCrucibleRecipe(Items.RECORD_11, 100F);
		addCrucibleRecipe(Items.RECORD_CHIRP, 100F);
		addCrucibleRecipe(Items.RECORD_FAR, 100F);
		addCrucibleRecipe(Items.RECORD_MALL, 100F);
		addCrucibleRecipe(Items.RECORD_MELLOHI, 100F);
		addCrucibleRecipe(Items.RECORD_STAL, 100F);
		addCrucibleRecipe(Items.RECORD_STRAD, 100F);
		addCrucibleRecipe(Items.RECORD_WARD, 100F);
		addCrucibleRecipe(Items.BLAZE_ROD, 36F);
		addCrucibleRecipe(Items.GHAST_TEAR, 64F);
		addCrucibleRecipe(Items.SPIDER_EYE, 9F);
		addCrucibleRecipe(Items.SADDLE, 64F);

		addCrucibleRecipe(Blocks.COBBLESTONE, 0.1F);
		addCrucibleRecipe(Blocks.PLANKS, 0.5F);
		addCrucibleRecipe(Blocks.MOSSY_COBBLESTONE, 4F);
		addCrucibleRecipe(Blocks.SAND, 1F);
		addCrucibleRecipe(Blocks.SANDSTONE, 1F);
		addCrucibleRecipe(Blocks.RED_SANDSTONE, 1F);
		addCrucibleRecipe(Blocks.DIRT, 1F);
		addCrucibleRecipe(Blocks.GRASS, 1F);
		addCrucibleRecipe(Blocks.GLASS, 1F);
		addCrucibleRecipe(Blocks.ICE, 1F);
		addCrucibleRecipe(Blocks.GRAVEL, 1F);
		addCrucibleRecipe(Blocks.STONE, 1F);
		addCrucibleRecipe(Blocks.WATERLILY, 3F);
		addCrucibleRecipe(Blocks.WEB, 4F);
		addCrucibleRecipe(Blocks.NETHER_BRICK, 2F);
		addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 1), 1F);
		addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 2), 1F);
		addCrucibleRecipe(Blocks.NETHERRACK, 1F);
		addCrucibleRecipe(Blocks.SOUL_SAND, 2F);
		addCrucibleRecipe(Blocks.COAL_ORE, 2F);
		addCrucibleRecipe(Blocks.WOOL, 4F);
		addCrucibleRecipe(Blocks.LOG, 2F);
		addCrucibleRecipe(Blocks.LEAVES, 2F);
		addCrucibleRecipe(Blocks.TALLGRASS, 2F);
		addCrucibleRecipe(Blocks.DEADBUSH, 2F);
		addCrucibleRecipe(Blocks.CACTUS, 2F);
		addCrucibleRecipe(Blocks.SAPLING, 2F);
		addCrucibleRecipe(Blocks.MYCELIUM, 3F);
		addCrucibleRecipe(Blocks.IRON_ORE, 4F);
		addCrucibleRecipe(Blocks.YELLOW_FLOWER, 4F);
		addCrucibleRecipe(Blocks.RED_FLOWER, 4F);
		addCrucibleRecipe(Blocks.BROWN_MUSHROOM_BLOCK, 4F);
		addCrucibleRecipe(Blocks.RED_MUSHROOM_BLOCK, 4F);
		addCrucibleRecipe(Blocks.VINE, 4F);
		addCrucibleRecipe(Blocks.PUMPKIN, 4F);
		addCrucibleRecipe(Blocks.REEDS, 4F);
		addCrucibleRecipe(Blocks.REDSTONE_ORE, 16F);
		addCrucibleRecipe(Blocks.LAPIS_ORE, 9F);
		addCrucibleRecipe(Blocks.GOLD_ORE, 25F);
		addCrucibleRecipe(Blocks.OBSIDIAN, 16F);
		addCrucibleRecipe(Blocks.DIAMOND_ORE, 64F);
	}

	private static void registerInfuserRecipes() {
		addInfuserRecipe(new ItemStack(Blocks.STONE), new ItemStack[] { new ItemStack(Blocks.DIRT), new ItemStack(Blocks.SAND) }, 20F, false);
		addInfuserRecipe(new ItemStack(Blocks.RED_NETHER_BRICK), new ItemStack[] { new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.STONE) }, 20F, true);
	}
}