package T145.magistics.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.api.blocks.BlockMagisticsItem;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockAestheticStructure;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.lib.ResearchRecipe;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class Settings extends Log {
	public static Configuration config;

	public static boolean low_gfx, colored_names, hungry_chest_override;

	public static void sync() {
		colored_names = config.get(config.CATEGORY_GENERAL, "Colored Names", false, "Toggles name coloring similar to TC2!").getBoolean();
		low_gfx = config.get(config.CATEGORY_GENERAL, "Low Graphics", false, "Determines if graphically intensive features are enabled.").getBoolean();
		hungry_chest_override = config.get(config.CATEGORY_GENERAL, "Hungry Chest Override", true, "Overrides Thaumcraft's default Hungry Chest for a modified version that supports the abilities of a vanilla chest.").getBoolean();
	}

	public static void preInit(File configFile) {
		try {
			config = new Configuration(configFile);
			config.load();
			sync();
			config.save();
		} catch (Exception err) {
			error("An error has occurred while loading configuration properties!", err);
		} finally {
			if (config != null)
				config.save();
		}
	}

	public static void onConfigChange(OnConfigChangedEvent e, String modid) {
		if (e.modID.equals(modid)) {
			sync();
			if (config != null && config.hasChanged())
				config.save();
		}
	}

	public static Item[] items = {
		new ItemAmuletDismay().setUnlocalizedName("bauble.amulet_dismay"),
		new ItemAmuletLife().setUnlocalizedName("bauble.amulet_life"),
		new ItemBeltCleansing().setUnlocalizedName("bauble.belt_cleansing"),
		new ItemBeltVigor().setUnlocalizedName("bauble.belt_vigor"),
		new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName("cruel_mask"),
		new ItemDawnstone().setUnlocalizedName("dawnstone")
	};

	public static Class tiles[] = {
		TileChestHungryEnder.class,
		TileChestHungry.class,
		TileChestHungryMetal.class
	};

	public static Block blockChestHungryEnder, blockChestHungry, blockChestHungryTrapped, blockChestHungryMetal, blockAestheticStructure;

	public static CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(blockChestHungryEnder);
		}
	};

	public static void init() {
		for (Item item : items)
			GameRegistry.registerItem(item.setCreativeTab(tabMagistics), item.getUnlocalizedName());
		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, "magistics:" + tile.getSimpleName());
		GameRegistry.registerBlock(blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setCreativeTab(tabMagistics).setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F), blockChestHungryEnder.getLocalizedName());
		GameRegistry.registerBlock(blockChestHungry = new BlockChestHungry(0).setBlockName("hungry_chest").setCreativeTab(tabMagistics), blockChestHungry.getLocalizedName());
		GameRegistry.registerBlock(blockChestHungryTrapped = new BlockChestHungry(1).setBlockName("trapped_hungry_chest").setCreativeTab(tabMagistics), blockChestHungryTrapped.getLocalizedName());
		GameRegistry.registerBlock(blockChestHungryMetal = new BlockChestHungryMetal().setBlockName("hungry_metal_chest").setCreativeTab(tabMagistics), BlockMagisticsItem.class, blockChestHungryMetal.getLocalizedName());
		GameRegistry.registerBlock(blockAestheticStructure = new BlockAestheticStructure().setBlockName("aesthetic_structure").setCreativeTab(tabMagistics), BlockMagisticsItem.class, blockAestheticStructure.getLocalizedName());
	}

	public static void postInit() {
		ResearchCategories.registerCategory(Magistics.modid, new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYENDERCHEST", new ItemStack(blockChestHungryEnder), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));
		new ResearchItem("HUNGRYENDERCHEST", Magistics.modid, new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), -1, 0, 1, new ItemStack(blockChestHungryEnder)).setPages(new ResearchPage("tc.research_page.HUNGRYENDERCHEST.1"), ResearchRecipe.arcane("HungryEnderChest")).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
	}
}