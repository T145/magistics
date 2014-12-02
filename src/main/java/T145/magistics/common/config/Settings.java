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
import T145.magistics.common.lib.ResearchPageType;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.common.registry.GameRegistry;

public class Settings extends Log {
	public static Configuration config;

	public static void sync() {
		try {
			config.load();
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

	public static CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.bookshelf);
		}
	};

	public static Class tiles[] = {
		TileChestHungryEnder.class
	};

	public static Block blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setCreativeTab(tabMagistics).setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F);

	public static void init() {
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