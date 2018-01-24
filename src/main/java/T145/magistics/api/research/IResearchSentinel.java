package T145.magistics.api.research;

import net.minecraft.item.ItemStack;

public interface IResearchSentinel {

	ItemStack getObservableStack();

	void setObservableStack(ItemStack stack);
}