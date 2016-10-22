package T145.magistics.api.crafting;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrucibleRecipes {

	public static final CrucibleRecipes INSTANCE = new CrucibleRecipes();
	private static Map<ItemStack, Float> recipes = Maps.<ItemStack, Float>newHashMap();

	public static void register() {
		addRecipe(Items.STICK, 0.25F);
		addRecipe(Items.CLAY_BALL, 1F);
		addRecipe(Items.BRICK, 2F);
		addRecipe(Items.FLINT, 1F);
		addRecipe(Items.COAL, 2F);
		addRecipe(Items.SNOWBALL, 0.25F);
		/*addRecipe(Items.DYE, 0, 4F);
		addRecipe(Items.DYE, 2, 4F);
		addRecipe(Items.DYE, 3, 25F);
		addRecipe(Items.DYE, 4, 9F);*/
		addRecipe(Items.IRON_INGOT, 5F);
		addRecipe(Items.BEETROOT_SEEDS, 4F);
		addRecipe(Items.MELON_SEEDS, 4F);
		addRecipe(Items.PUMPKIN_SEEDS, 4F);
		addRecipe(Items.WHEAT_SEEDS, 4F);
		addRecipe(Items.FEATHER, 4F);
		addRecipe(Items.BONE, 4F);
		addRecipe(Items.MELON, 2F);
		addRecipe(Items.COOKED_BEEF, 5F);
		addRecipe(Items.BEEF, 4F);
		addRecipe(Items.COOKED_CHICKEN, 5F);
		addRecipe(Items.CHICKEN, 4F);
		addRecipe(Items.COOKED_PORKCHOP, 5F);
		addRecipe(Items.PORKCHOP, 4F);
		addRecipe(Items.COOKED_FISH, 5F);
		addRecipe(Items.FISH, 4F);
		addRecipe(Items.NETHER_WART, 4F);
		addRecipe(Items.ROTTEN_FLESH, 4F);
		addRecipe(Items.STRING, 4F);
		addRecipe(Items.LEATHER, 4F);
		addRecipe(Items.WHEAT, 4F);
		addRecipe(Items.REEDS, 4F);
		addRecipe(Items.GUNPOWDER, 10F);
		addRecipe(Items.GLOWSTONE_DUST, 9F);
		addRecipe(Items.REDSTONE, 6F);
		addRecipe(Items.MILK_BUCKET, 18F);
		addRecipe(Items.WATER_BUCKET, 16F);
		addRecipe(Items.LAVA_BUCKET, 17F);
		addRecipe(Items.EGG, 5F);
		addRecipe(Items.GOLD_INGOT, 27F);
		addRecipe(Items.GOLD_NUGGET, 3F);
		addRecipe(Items.SLIME_BALL, 25F);
		addRecipe(Items.APPLE, 4F);
		addRecipe(Items.DIAMOND, 64F);
		addRecipe(Items.ENDER_PEARL, 64F);
		addRecipe(Items.RECORD_13, 100F);
		addRecipe(Items.RECORD_CAT, 100F);
		addRecipe(Items.RECORD_11, 100F);
		addRecipe(Items.RECORD_CHIRP, 100F);
		addRecipe(Items.RECORD_FAR, 100F);
		addRecipe(Items.RECORD_MALL, 100F);
		addRecipe(Items.RECORD_MELLOHI, 100F);
		addRecipe(Items.RECORD_STAL, 100F);
		addRecipe(Items.RECORD_STRAD, 100F);
		addRecipe(Items.RECORD_WARD, 100F);
		addRecipe(Items.BLAZE_ROD, 36F);
		addRecipe(Items.GHAST_TEAR, 64F);
		addRecipe(Items.SPIDER_EYE, 9F);
		addRecipe(Items.SADDLE, 64F);

		addRecipe(Blocks.COBBLESTONE, 0.1F);
		addRecipe(Blocks.PLANKS, 0.5F);
		addRecipe(Blocks.MOSSY_COBBLESTONE, 4F);
		addRecipe(Blocks.SAND, 1F);
		addRecipe(Blocks.SANDSTONE, 1F);
		addRecipe(Blocks.RED_SANDSTONE, 1F);
		addRecipe(Blocks.DIRT, 1F);
		addRecipe(Blocks.GRASS, 1F);
		addRecipe(Blocks.GLASS, 1F);
		addRecipe(Blocks.ICE, 1F);
		addRecipe(Blocks.GRAVEL, 1F);
		addRecipe(Blocks.STONE, 1F);
		addRecipe(Blocks.WATERLILY, 3F);
		addRecipe(Blocks.WEB, 4F);
		addRecipe(Blocks.NETHER_BRICK, 2F);
		addRecipe(new ItemStack(Blocks.STONEBRICK, 1, 1), 1F);
		addRecipe(new ItemStack(Blocks.STONEBRICK, 1, 2), 1F);
		addRecipe(Blocks.NETHERRACK, 1F);
		addRecipe(Blocks.SOUL_SAND, 2F);
		addRecipe(Blocks.COAL_ORE, 2F);
		addRecipe(Blocks.WOOL, 4F);
		addRecipe(Blocks.LOG, 2F);
		addRecipe(Blocks.LEAVES, 2F);
		addRecipe(Blocks.TALLGRASS, 2F);
		addRecipe(Blocks.DEADBUSH, 2F);
		addRecipe(Blocks.CACTUS, 2F);
		addRecipe(Blocks.SAPLING, 2F);
		addRecipe(Blocks.MYCELIUM, 3F);
		addRecipe(Blocks.IRON_ORE, 4F);
		addRecipe(Blocks.YELLOW_FLOWER, 4F);
		addRecipe(Blocks.RED_FLOWER, 4F);
		addRecipe(Blocks.BROWN_MUSHROOM_BLOCK, 4F);
		addRecipe(Blocks.RED_MUSHROOM_BLOCK, 4F);
		addRecipe(Blocks.VINE, 4F);
		addRecipe(Blocks.PUMPKIN, 4F);
		addRecipe(Blocks.REEDS, 4F);
		addRecipe(Blocks.REDSTONE_ORE, 16F);
		addRecipe(Blocks.LAPIS_ORE, 9F);
		addRecipe(Blocks.GOLD_ORE, 25F);
		addRecipe(Blocks.OBSIDIAN, 16F);
		addRecipe(Blocks.DIAMOND_ORE, 64F);
	}

	public static void addRecipe(ItemStack input, float visOutput) {
		recipes.put(input, visOutput);
	}

	public static void addRecipe(Block input, float visOutput) {
		addRecipe(Item.getItemFromBlock(input), visOutput);
	}

	public static void addRecipe(Item input, float visOutput) {
		addRecipe(new ItemStack(input, 1, 32767), visOutput);
	}

	@Nullable
	public static float getResult(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : recipes.entrySet()) {
			if (compareItemStacks(stack, entry.getKey())) {
				return entry.getValue();
			}
		}

		return 0F;
	}

	private static boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
}