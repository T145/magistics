package hu.hundevelopers.elysium.thaumcraft.wand;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;

public class ElysiumWandCap extends WandCap
{
	public ElysiumWandCap(String tag, float discount, ItemStack item, int craftCost, ResourceLocation skin) {
		super(tag, discount, item, craftCost);
		setTexture(skin);
	}

	public ElysiumWandCap(String tag, float discount, List<Aspect> specialAspects, float discountSpecial, ItemStack item, int craftCost, ResourceLocation skin) {
		super(tag, discount, specialAspects, discountSpecial, item, craftCost);
		setTexture(skin);
	}
}
