package T145.magistics.api.crafting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class CrucibleRecipe {

	private final ItemStack result;
	private final List<EntityItem> items;
	private final float quintOutput;

	public CrucibleRecipe(ItemStack result, List<EntityItem> items, float quintOutput) {
		this.result = result;
		this.items = items;
		this.quintOutput = quintOutput;
	}

	public ItemStack getResult() {
		return result;
	}

	public List<EntityItem> getItems() {
		return items;
	}

	public static List<ItemStack> getStacksFromItems(final List<EntityItem> items) {
		List<ItemStack> stacks = new ArrayList<ItemStack>();

		for (EntityItem item : items) {
			stacks.add(item.getEntityItem());
		}

		return stacks;
	}

	public boolean matches(final List<EntityItem> items) {
		final Set<EntityItem> set = new HashSet<EntityItem>(items);
		return items.size() == this.items.size() && set.containsAll(this.items);
	}
}