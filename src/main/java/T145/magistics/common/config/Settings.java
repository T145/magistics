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
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockAestheticItem;
import T145.magistics.common.blocks.BlockAestheticStructure;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryIron;
import T145.magistics.common.blocks.BlockChestHungryIronItem;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.lib.ResearchPageType;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class Settings extends Log {
	public static Configuration config;

	public static boolean low_gfx, colored_names;

	public static void sync() {
		colored_names = config.get(config.CATEGORY_GENERAL, "Colored Names", false, "Toggles name coloring similar to TC2!").getBoolean();
		low_gfx = config.get(config.CATEGORY_GENERAL, "Low Graphics", false, "Determines if graphically intensive features are enabled.").getBoolean();
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
		TileChestHungryEnder.class
	};

	public static Block blockChestHungryEnder, blockChestHungryIron, blockAesthetic, blockAestheticStructure;

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
			GameRegistry.registerTileEntity(tile, tile.getSimpleName());
		GameRegistry.registerBlock(blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setCreativeTab(tabMagistics).setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F), blockChestHungryEnder.getLocalizedName());
		GameRegistry.registerBlock(blockChestHungryIron = new BlockChestHungryIron().setBlockName("hungry_metal_chest").setCreativeTab(tabMagistics).setHardness(3F), BlockChestHungryIronItem.class, blockChestHungryIron.getLocalizedName());
		//GameRegistry.registerBlock(blockAesthetic = new BlockAesthetic().setBlockName("aesthetic").setCreativeTab(tabMagistics), BlockAestheticItem.class, blockAesthetic.getLocalizedName());
		GameRegistry.registerBlock(blockAestheticStructure = new BlockAestheticStructure().setBlockName("aesthetic_structure").setCreativeTab(tabMagistics), BlockAestheticItem.class, blockAestheticStructure.getLocalizedName());
	}

	public static void postInit() {
		ResearchCategories.registerCategory(Magistics.modid, new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYENDERCHEST", new ItemStack(blockChestHungryEnder), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));
		new ResearchItem("HUNGRYENDERCHEST", Magistics.modid, new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), -1, 0, 1, new ItemStack(blockChestHungryEnder)).setPages(new ResearchPage("tc.research_page.HUNGRYENDERCHEST.1"), ResearchPageType.arcaneRecipePage("HungryEnderChest")).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
	}
}