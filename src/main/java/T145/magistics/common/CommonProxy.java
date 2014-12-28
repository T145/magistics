package T145.magistics.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
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
import T145.magistics.api.blocks.BlockMagisticsItem;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.config.Log;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.lib.ResearchRecipe;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;

import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {
	public static Configuration config;
	public static boolean low_gfx, colored_names, hungry_chest_override;

	public List<Class> tiles = new ArrayList<Class>();
	public List<Item> items = new ArrayList<Item>();
	public Block blockChestHungryEnder, blockChestHungry, blockChestHungryTrapped, blockChestHungryMetal, blockHungryStrongbox, blockChestHungryAlchemical, blockAestheticStructure;
	public Map<Block, Class> blocks = new HashMap<Block, Class>();

	public void sync() {
		colored_names = config.get(config.CATEGORY_GENERAL, "Colored Names", false, "Toggles name coloring similar to TC2!").getBoolean();
		low_gfx = config.get(config.CATEGORY_GENERAL, "Low Graphics", false, "Determines if graphically intensive features are enabled.").getBoolean();
		hungry_chest_override = config.get(config.CATEGORY_GENERAL, "Hungry Chest Override", true, "Overrides Thaumcraft's default Hungry Chest for a modified version that supports the abilities of a vanilla chest.").getBoolean();
	}

	public void preInit(File configFile) {
		try {
			config = new Configuration(configFile);
			config.load();
			sync();
			config.save();
		} catch (Exception err) {
			Log.error("An error has occurred while loading configuration properties!", err);
		} finally {
			if (config != null)
				config.save();
		}

		items.add(new ItemAmuletDismay().setUnlocalizedName("bauble.amulet_dismay"));
		items.add(new ItemAmuletLife().setUnlocalizedName("bauble.amulet_life"));
		items.add(new ItemBeltCleansing().setUnlocalizedName("bauble.belt_cleansing"));
		items.add(new ItemBeltVigor().setUnlocalizedName("bauble.belt_vigor"));
		items.add(new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName("cruel_mask"));
		items.add(new ItemDawnstone().setUnlocalizedName("dawnstone"));

		//blocks.put(blockAestheticStructure = new BlockAestheticStructure().setBlockName("aesthetic_structure"), BlockMagisticsItem.class);
		blocks.put(blockChestHungry = new BlockChestHungry(0).setBlockName("hungry_chest"), null);
		blocks.put(blockChestHungryTrapped = new BlockChestHungry(1).setBlockName("trapped_hungry_chest"), null);
		tiles.add(TileChestHungry.class);
		blocks.put(blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F), null);
		tiles.add(TileChestHungryEnder.class);

		if (Loader.isModLoaded("IronChest")) {
			blocks.put(blockChestHungryMetal = new BlockChestHungryMetal().setBlockName("hungry_metal_chest").setHardness(3F), BlockMagisticsItem.class);
			tiles.add(TileChestHungryMetal.class);
		}

		if (Loader.isModLoaded("EE3")) {
			blocks.put(blockChestHungryAlchemical = new BlockChestHungryAlchemical().setBlockName("hungry_alchemical_chest"), ItemBlockAlchemicalChest.class);
			tiles.add(TileChestHungryAlchemical.class);
		}
	}

	public void changeConfig(OnConfigChangedEvent e, String modid) {
		if (e.modID.equals(modid)) {
			sync();
			if (config != null && config.hasChanged())
				config.save();
		}
	}

	public CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(blockChestHungryEnder);
		}
	};

	public void init() {
		for (Item item : items)
			GameRegistry.registerItem(item.setCreativeTab(tabMagistics), item.getUnlocalizedName());
		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, "magistics:" + tile.getSimpleName());
		for (Block block : blocks.keySet())
			if (blocks.get(block) == null)
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), block.getLocalizedName());
			else
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), blocks.get(block), block.getLocalizedName());
	}

	public void postInit() {
		ResearchCategories.registerCategory(Magistics.modid, new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYENDERCHEST", new ItemStack(blockChestHungryEnder), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));
		new ResearchItem("HUNGRYENDERCHEST", Magistics.modid, new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), -1, 0, 1, new ItemStack(blockChestHungryEnder)).setPages(new ResearchPage("tc.research_page.HUNGRYENDERCHEST.1"), ResearchRecipe.arcane("HungryEnderChest")).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}
}