package hu.hundevelopers.elysium.thaumcraft;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ElysiumAspects
{
	public static Aspect SANCTUS;

	public static void initAspects()
	{
		SANCTUS = new Aspect("sanctus", 0xffffff, new Aspect[]{ Aspect.SOUL, Aspect.AURA}, new ResourceLocation(Elysium.ID + ":textures/aspects/sanctus.png"), 1);
	}

	private static AspectList getAspectList(ItemStack stack)
	{
		AspectList list = ThaumcraftApiHelper.getObjectAspects(stack);
		return list != null ? list : new AspectList();
	}

	public static void addAspects()
	{
//		AspectList list;
//
//		list = getAspectList(new ItemStack(Blocks.netherrack));
//		list.add(NETHER, 1);
//		ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.netherrack, 1, OreDictionary.WILDCARD_VALUE), list);

		//Items
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemSeedsPepper), new AspectList().add(Aspect.PLANT, 1).add(Aspect.ENTROPY, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemSeedsPepper), new AspectList().add(Aspect.SENSES, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemBeryl), new AspectList().add(Aspect.METAL, 1).add(Aspect.CRYSTAL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemIngotIridium), new AspectList().add(Aspect.METAL, 4).add(Aspect.SENSES, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemIngotCobalt), new AspectList().add(Aspect.METAL, 4).add(Aspect.TOOL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemJade), new AspectList().add(Aspect.GREED, 1).add(Aspect.MIND, 1).add(Aspect.CRYSTAL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemSiliconChunk), new AspectList().add(Aspect.EARTH, 1).add(Aspect.CRYSTAL, 4));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemSulphur), new AspectList().add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 1).add(Aspect.EARTH, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemTourmaline), new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.SENSES, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemDeerPelt), new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemAntler), new AspectList().add(Aspect.WEAPON, 2).add(Aspect.BEAST, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemHardPaw), new AspectList().add(Aspect.WEAPON, 1).add(Aspect.BEAST, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.wandCore, 1, 0), new AspectList().add(SANCTUS, 2).add(Aspect.BEAST, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemGrapes, 1, 0), new AspectList().add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.LIFE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemGrapes, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.LIFE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemRaspberry), new AspectList().add(Aspect.PLANT, 1).add(Aspect.ENTROPY, 1).add(Aspect.LIFE, 1));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemStaff, 1, 0), new AspectList().add(Aspect.EARTH, 4).add(Aspect.MAGIC, 4));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemStaff, 1, 1), new AspectList().add(Aspect.COLD, 4).add(Aspect.MAGIC, 4));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemStaff, 1, 2), new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 4));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.itemStaff, 1, 3), new AspectList().add(Aspect.FIRE, 4).add(Aspect.MAGIC, 4));
	    
	    //Blocks
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockElysiumWater), new AspectList().add(Aspect.WATER, 4).add(SANCTUS, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockElysiumEnergyLiquid), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.WATER, 2).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockPalestone), new AspectList().add(Aspect.EARTH, 2).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockGrass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockDirt), new AspectList().add(Aspect.EARTH, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockSand), new AspectList().add(Aspect.EARTH, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockRilt), new AspectList().add(Aspect.EARTH, 1).add(Aspect.HUNGER, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockSapling, 1, 0), new AspectList().add(Aspect.PLANT, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockSapling, 1, 1), new AspectList().add(Aspect.PLANT, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockLog, 1, 0), new AspectList().add(Aspect.TREE, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockLog, 1, 1), new AspectList().add(Aspect.TREE, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockLog, 1, 2), new AspectList().add(Aspect.TREE, 1).add(Aspect.ELDRITCH, 1).add(Aspect.TAINT, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockLeaves, 1, 0), new AspectList().add(Aspect.PLANT, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockLeaves, 1, 1), new AspectList().add(Aspect.PLANT, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockPlanks, 1, 0), new AspectList().add(Aspect.TREE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockPlanks, 1, 1), new AspectList().add(Aspect.TREE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockFlower, 1, 0), new AspectList().add(Aspect.SENSES, 1).add(Aspect.LIFE, 1).add(Aspect.PLANT, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockTallGrass), new AspectList().add(Aspect.AIR, 1).add(Aspect.PLANT, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockFlower, 1, 1), new AspectList().add(Aspect.GREED, 1).add(Aspect.PLANT, 1));

	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.oreBeryl), new AspectList().add(Aspect.EARTH, 2).add(Aspect.METAL, 1).add(Aspect.CRYSTAL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.oreCobalt), new AspectList().add(Aspect.EARTH, 2).add(Aspect.METAL, 1).add(Aspect.TOOL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.oreIridium), new AspectList().add(Aspect.EARTH, 2).add(Aspect.METAL, 1).add(Aspect.SENSES, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.oreJade), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MIND, 1).add(Aspect.CRYSTAL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.oreSilicon), new AspectList().add(Aspect.EARTH, 2).add(Aspect.CRYSTAL, 4));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.oreSulphure), new AspectList().add(Aspect.EARTH, 2).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.oreTourmaline), new AspectList().add(Aspect.EARTH, 2).add(Aspect.CRYSTAL, 2).add(Aspect.SENSES, 2));

	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockFloatingConch), new AspectList().add(Aspect.ARMOR, 1).add(Aspect.DEATH, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockFloatingShell), new AspectList().add(Aspect.ARMOR, 1).add(Aspect.DEATH, 1).add(SANCTUS, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockEnergyCrystal, 1, 0), new AspectList().add(SANCTUS, 2).add(Aspect.ENERGY, 2).add(Aspect.CRYSTAL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockEnergyCrystal, 1, 1), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.TAINT, 2).add(Aspect.CRYSTAL, 1));

	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockQuartzFence, 1, 0), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockQuartzFence, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));

	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockQuartzWall, 1, 0), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.ENERGY, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockQuartzWall, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.CRYSTAL, 2).add(Aspect.ENERGY, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockQuartzGate, 1, 0), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.TRAVEL, 1).add(Aspect.MECHANISM, 1));

	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockRaspberryBush), new AspectList().add(Aspect.PLANT, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockGrapesBush), new AspectList().add(Aspect.PLANT, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockGrapesBush), new AspectList().add(Aspect.PLANT, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(Elysium.blockCactus), new AspectList().add(Aspect.PLANT, 1).add(SANCTUS, 1).add(Aspect.ENTROPY, 1));
        
	}
}