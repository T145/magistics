package T145.magistics.api.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrucibleRecipe {

	private ItemStack sourceStack;
	private float outputVis;

	public CrucibleRecipe(ItemStack stack, float vis) {
		sourceStack = stack;
		outputVis = vis;
	}

	public CrucibleRecipe(Item item, float vis) {
		this(new ItemStack(item), vis);
	}

	public CrucibleRecipe(Item item, int meta, float vis) {
		this(new ItemStack(item, 1, meta), vis);
	}

	public CrucibleRecipe(Item item, int quantity, int meta, float vis) {
		this(new ItemStack(item, quantity, meta), vis);
	}

	public CrucibleRecipe(Block block, float vis) {
		this(new ItemStack(block), vis);
	}

	public CrucibleRecipe(Block block, int meta, float vis) {
		this(new ItemStack(block, 1, meta), vis);
	}

	public CrucibleRecipe(Block block, int quantity, int meta, float vis) {
		this(new ItemStack(block, quantity, meta), vis);
	}

	public ItemStack getStack() {
		return sourceStack;
	}

	public float getResult() {
		return outputVis;
	}
}