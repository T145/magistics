package T145.magistics.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.common.config.Log;
import T145.magistics.common.config.ModBlocks;
import T145.magistics.common.config.ModItems;
import T145.magistics.common.lib.ResearchRecipe;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy extends Log implements IGuiHandler {
	public static CreativeTabs tabMagistics;
	public static Configuration config;
	public static final String category[] = {
		"Graphics", "Blocks", "Items"
	};
	public static boolean debug, colored_names, low_gfx, hungry_chest_override;
	public static List<String> supportedMods = new ArrayList<String>();

	public void sync() {
		debug = config.getBoolean(config.CATEGORY_GENERAL, "Debug", true, "Toggles advanced log output.");
		hungry_chest_override = config.getBoolean(config.CATEGORY_GENERAL, "Hungry Chest Override", true, "Replaces Thaumcraft's Hungry Chest w/ a modified version.");
		colored_names = config.getBoolean(category[0], "Colored Names", false, "Toggles name coloring for some things.");
		low_gfx = config.getBoolean(category[0], "Low Graphics", false, "Determines some graphically intensive features are enabled.");
	}

	public void preInit(File configFile) {
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

	public void changeConfig(OnConfigChangedEvent e, String modid) {
		if (e.modID.equals(modid)) {
			sync();
			if (config != null && config.hasChanged())
				config.save();
		}
	}

	public void init() {
		ModBlocks.loadServer();
		ModItems.load();
		tabMagistics.setBackgroundImageName("magistics.png");

		if (!supportedMods.isEmpty())
			for (String supportedMod : supportedMods)
				inform("Registered: " + supportedMod);
	}

	public void postInit() {
		ResearchCategories.registerCategory(Magistics.modid, new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYENDERCHEST", new ItemStack(ModBlocks.blockChestHungryEnder), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));
		new ResearchItem("HUNGRYENDERCHEST", Magistics.modid, new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), -1, 0, 1, new ItemStack(ModBlocks.blockChestHungryEnder)).setPages(new ResearchPage("tc.research_page.HUNGRYENDERCHEST.1"), ResearchRecipe.arcane("HungryEnderChest")).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
		
		
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}

	static {
		tabMagistics = new CreativeTabs(Magistics.modid) {
			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(ModBlocks.blockChestHungryEnder);
			}

			@Override
			public boolean hasSearchBar() {
				return true;
			}
		};
	}
}