package T145.magistics.research;

import T145.magistics.Magistics;
import T145.magistics.api.MagisticsApi;
import T145.magistics.blocks.BlockInfusionWorkbench;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.items.ItemShardFragment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigResearch;

public class RecipeHandler {
	private ItemStack empty = new ItemStack(ConfigBlocks.blockHole, 1, 15);

	public static void registerRecipes() {
		ConfigResearch.recipes.put("NetherrackFurnace", ThaumcraftApi.addArcaneCraftingRecipe("NETHERRACKFURNACE", new ItemStack(BlockNetherFurnace.INACTIVE, 1, 3),
				new AspectList().add(Aspect.FIRE, 6).add(Aspect.ENTROPY, 6), new Object[] {
			"aaa",
			"aba",
			"aca",
			'a', Blocks.netherrack,
			'b', Blocks.furnace,
			'c', new ItemStack(ItemShardFragment.INSTANCE, 1, 1)
		}));

		MagisticsApi.addInfusingRecipe("NITOR", new AspectList().add(Aspect.FIRE, 6).add(Aspect.LIGHT, 6), new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack[] {
			new ItemStack(Items.redstone),
			new ItemStack(Items.glowstone_dust)
		}, 60);

		MagisticsApi.addDarkInfusingRecipe("OUTERREV", new AspectList().add(Aspect.DARKNESS, 8).add(Aspect.VOID, 8).add(Aspect.ELDRITCH, 2), new ItemStack(ConfigItems.itemResource, 1, 17), new ItemStack[] {
			new ItemStack(Items.wheat_seeds)
		}, 100);

		MagisticsApi.addDarkInfusingRecipe("VOIDMETAL", new AspectList().add(Aspect.DARKNESS, 8).add(Aspect.VOID, 8).add(Aspect.ELDRITCH, 2).add(Aspect.METAL, 8), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack[] {
			new ItemStack(Items.iron_ingot),
			new ItemStack(ConfigItems.itemResource, 1, 17)
		}, 100);

		WandTriggerRegistry.registerWandBlockTrigger(Magistics.proxy.wandManager, 1, BlockInfusionWorkbench.INSTANCE, 0, Magistics.MODID);
	}
}