package T145.magistics.common.config;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ElysiumAspects {
	public static Aspect SANCTUS = new Aspect("sanctus", 0xffffff, new Aspect[] { Aspect.SOUL, Aspect.AURA }, new ResourceLocation("elysium:textures/aspects/sanctus.png"), 1);

	private static AspectList getAspectList(ItemStack stack) {
		AspectList list = ThaumcraftApiHelper.getObjectAspects(stack);
		return list != null ? list : new AspectList();
	}

	public static void addAspects() {
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemSeedsPepper), new AspectList().add(Aspect.PLANT, 1).add(Aspect.ENTROPY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemSeedsPepper), new AspectList().add(Aspect.SENSES, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemBeryl), new AspectList().add(Aspect.METAL, 1).add(Aspect.CRYSTAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemIngotIridium), new AspectList().add(Aspect.METAL, 4).add(Aspect.SENSES, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemIngotCobalt), new AspectList().add(Aspect.METAL, 4).add(Aspect.TOOL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemJade), new AspectList().add(Aspect.GREED, 1).add(Aspect.MIND, 1).add(Aspect.CRYSTAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemSiliconChunk), new AspectList().add(Aspect.EARTH, 1).add(Aspect.CRYSTAL, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemSulphur), new AspectList().add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 1).add(Aspect.EARTH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemTourmaline), new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.SENSES, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemDeerPelt), new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemAntler), new AspectList().add(Aspect.WEAPON, 2).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemHardPaw), new AspectList().add(Aspect.WEAPON, 1).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.wandCore, 1, 0), new AspectList().add(SANCTUS, 2).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemGrapes, 1, 0), new AspectList().add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemGrapes, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemRaspberry), new AspectList().add(Aspect.PLANT, 1).add(Aspect.ENTROPY, 1).add(Aspect.LIFE, 1));

		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemStaff, 1, 0), new AspectList().add(Aspect.EARTH, 4).add(Aspect.MAGIC, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemStaff, 1, 1), new AspectList().add(Aspect.COLD, 4).add(Aspect.MAGIC, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemStaff, 1, 2), new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.itemStaff, 1, 3), new AspectList().add(Aspect.FIRE, 4).add(Aspect.MAGIC, 4));

		//Blocks
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockElysiumWater), new AspectList().add(Aspect.WATER, 4).add(SANCTUS, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockElysiumEnergyLiquid), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.WATER, 2).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockPalestone), new AspectList().add(Aspect.EARTH, 2).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockGrass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockDirt), new AspectList().add(Aspect.EARTH, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockSand), new AspectList().add(Aspect.EARTH, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockRilt), new AspectList().add(Aspect.EARTH, 1).add(Aspect.HUNGER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockSapling, 1, 0), new AspectList().add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockSapling, 1, 1), new AspectList().add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockLog, 1, 0), new AspectList().add(Aspect.TREE, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockLog, 1, 1), new AspectList().add(Aspect.TREE, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockLog, 1, 2), new AspectList().add(Aspect.TREE, 1).add(Aspect.ELDRITCH, 1).add(Aspect.TAINT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockLeaves, 1, 0), new AspectList().add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockLeaves, 1, 1), new AspectList().add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockPlanks, 1, 0), new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockPlanks, 1, 1), new AspectList().add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockFlower, 1, 0), new AspectList().add(Aspect.SENSES, 1).add(Aspect.LIFE, 1).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockTallGrass), new AspectList().add(Aspect.AIR, 1).add(Aspect.PLANT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockFlower, 1, 1), new AspectList().add(Aspect.GREED, 1).add(Aspect.PLANT, 1));

		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.oreBeryl), new AspectList().add(Aspect.EARTH, 2).add(Aspect.METAL, 1).add(Aspect.CRYSTAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.oreCobalt), new AspectList().add(Aspect.EARTH, 2).add(Aspect.METAL, 1).add(Aspect.TOOL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.oreIridium), new AspectList().add(Aspect.EARTH, 2).add(Aspect.METAL, 1).add(Aspect.SENSES, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.oreJade), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MIND, 1).add(Aspect.CRYSTAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.oreSilicon), new AspectList().add(Aspect.EARTH, 2).add(Aspect.CRYSTAL, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.oreSulphure), new AspectList().add(Aspect.EARTH, 2).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.oreTourmaline), new AspectList().add(Aspect.EARTH, 2).add(Aspect.CRYSTAL, 2).add(Aspect.SENSES, 2));

		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockFloatingConch), new AspectList().add(Aspect.ARMOR, 1).add(Aspect.DEATH, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockFloatingShell), new AspectList().add(Aspect.ARMOR, 1).add(Aspect.DEATH, 1).add(SANCTUS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockEnergyCrystal, 1, 0), new AspectList().add(SANCTUS, 2).add(Aspect.ENERGY, 2).add(Aspect.CRYSTAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockEnergyCrystal, 1, 1), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.TAINT, 2).add(Aspect.CRYSTAL, 1));

		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockQuartzFence, 1, 0), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockQuartzFence, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.CRYSTAL, 1).add(Aspect.ENERGY, 1));

		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockQuartzWall, 1, 0), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.ENERGY, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockQuartzWall, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.CRYSTAL, 2).add(Aspect.ENERGY, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockQuartzGate, 1, 0), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.TRAVEL, 1).add(Aspect.MECHANISM, 1));

		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockRaspberryBush), new AspectList().add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockGrapesBush), new AspectList().add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockGrapesBush), new AspectList().add(Aspect.PLANT, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ConfigObjects.blockCactus), new AspectList().add(Aspect.PLANT, 1).add(SANCTUS, 1).add(Aspect.ENTROPY, 1));
	}
}