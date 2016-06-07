package T145.magistics.research;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.blocks.BlockChestHungryEnder;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.items.ItemShardFragment;

public class RecipeHandler {
	public static Map<String, Object> recipes = new HashMap<String, Object>();

	private ItemStack empty = new ItemStack(ConfigBlocks.blockHole, 1, 15);

	public static void registerRecipes() {
		recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYENDERCHST", new ItemStack(BlockChestHungryEnder.INSTANCE),
				new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), new Object[] {
			"aba",
			"aca",
			"aaa",
			'a', new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1),
			'b', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5),
			'c', Items.ender_pearl
		}));

		recipes.put("NetherrackFurnace", ThaumcraftApi.addArcaneCraftingRecipe("NETHERRACKFURNACE", new ItemStack(BlockNetherFurnace.INACTIVE, 1, 3),
				new AspectList().add(Aspect.FIRE, 6).add(Aspect.ENTROPY, 6), new Object[] {
			"aaa",
			"aba",
			"aca",
			'a', Blocks.netherrack,
			'b', Blocks.furnace,
			'c', new ItemStack(ItemShardFragment.INSTANCE, 1, 1)
		}));

		ConfigResearch.recipes.putAll(recipes);
	}
}