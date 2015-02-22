package hu.hundevelopers.elysium.thaumcraft;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;
import cpw.mods.fml.common.registry.GameRegistry;



public class ElysiumRecipes 
{
	public static void addRecipes()
	{
		

		ItemStack wand = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("pure"));
		((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("horn"));
		GameRegistry.addRecipe(wand, new Object[] { "S  ", " Q ", "  S", Character.valueOf('Q'), new ItemStack(Elysium.wandCore, 1, 0), Character.valueOf('S'), new ItemStack(Elysium.wandCap, 1, 0)});

		wand = ItemApi.getItem("itemWandCasting", 72);
		((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("corrupted"));
		((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("horn"));
		GameRegistry.addRecipe(wand, new Object[] { "S  ", " Q ", "  S", Character.valueOf('Q'), new ItemStack(Elysium.wandCore, 1, 0), Character.valueOf('S'), new ItemStack(Elysium.wandCap, 1, 1)});
		
		GameRegistry.addRecipe(new ItemStack(Elysium.wandCap, 1, 0), new Object[] { "NNN", "N N", Character.valueOf('N'), new ItemStack(Elysium.blockEnergyCrystal, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Elysium.wandCap, 1, 1), new Object[] { "NNN", "N N", Character.valueOf('N'), new ItemStack(Elysium.blockEnergyCrystal, 1, 1)});

		
//		ElysiumResearch.recipes.put("WandRodHorn", ThaumcraftApi.addInfusionCraftingRecipe("ROD_horn",
//				new ItemStack(Elysium.wandCore, 1, 0),
//				5,
//				(new AspectList())
//					.add(ElysiumAspects.SANCTUS, 24)
//					.add(Aspect.MAGIC, 12),
//				new ItemStack(Elysium.itemHorn),
//				new ItemStack[] {
//					ItemApi.getItem("itemResource", 14),
//					new ItemStack(Elysium.blockEnergyCrystal, 1, 0),
//					new ItemStack(Elysium.blockEnergyCrystal, 1, 0),
//					new ItemStack(Elysium.blockEnergyCrystal, 1, 1),
//					new ItemStack(Elysium.blockEnergyCrystal, 1, 1)
//				}));
		
//		ThaumcraftApi.addCrucibleRecipe("Crucible", new ItemStack(Elysium.itemToothIngot), new ItemStack(Elysium.itemHardPaw), (new AspectList()).add(Aspect.METAL, 4).add(Aspect.ELDRITCH, 1));
	}
}
