package hu.hundevelopers.elysium.thaumcraft.wand;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandRod;

public class ElysiumWandRod extends WandRod
{

	public ElysiumWandRod(String tag, int capacity, ItemStack item, int craftCost, ResourceLocation texture)
	{
		super(tag, capacity, item, craftCost, texture);
		this.setGlowing(true);
	}

}
