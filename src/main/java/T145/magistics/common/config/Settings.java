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
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.lib.ResearchPageType;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.common.registry.GameRegistry;

public class Settings extends Log {
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

	public static CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.bookshelf);
		}
	};

	public static Block blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setCreativeTab(tabMagistics).setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F);

	public static Configuration config;
	public static final String[] categories = {
		"Blocks", "Items"
	};

	public static boolean low_gfx, colored_names, enableItem[] = new boolean[items.length];

	public static void sync() {
		try {
			config.load();

			config.addCustomCategoryComment(categories[0], "The blocks added by Magistics");
			config.addCustomCategoryComment(categories[1], "The items added by Magistics");

			config.getBoolean("Low Graphics", config.CATEGORY_GENERAL, false, "Determines if graphically intensive features are enabled.");
			config.getBoolean("Colored Names", config.CATEGORY_GENERAL, true, "Toggles name coloring from TC2!");
		} catch (Exception err) {
			error("An error has occurred while loading configuration properties!", err);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public static void preInit(File configFile) {
		config = new Configuration(configFile);
		sync();
	}

	public static void init() {
		for (Item item : items)
			GameRegistry.registerItem(item.setCreativeTab(tabMagistics), item.getUnlocalizedName());
		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, tile.getSimpleName());
		GameRegistry.registerBlock(blockChestHungryEnder, blockChestHungryEnder.getLocalizedName());
	}

	public static void postInit() {
		ResearchCategories.registerCategory(Magistics.modid, new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYENDERCHEST", new ItemStack(blockChestHungryEnder), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));
		new ResearchItem("HUNGRYENDERCHEST", Magistics.modid, new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), -1, 0, 1, new ItemStack(blockChestHungryEnder)).setPages(new ResearchPage("tc.research_page.HUNGRYENDERCHEST.1"), ResearchPageType.arcaneRecipePage("HungryEnderChest")).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
	}
}