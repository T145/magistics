package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import T145.magistics.api.blocks.BlockMeta;
import T145.magistics.api.client.renderers.block.ChestRenderer;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.client.renderers.block.BlockAestheticStructureRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryRenderer;
import T145.magistics.common.blocks.BlockAestheticStructure;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.blocks.BlockEridium;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;

import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.Loader;

public class ModBlocks {
	public static List<Class> tiles = new ArrayList<Class>();
	public static Map<Block, Class> blocks = new HashMap<Block, Class>();
	public static Map<Class, TileEntitySpecialRenderer> tileRenderers = new HashMap<Class, TileEntitySpecialRenderer>();
	public static List<ISimpleBlockRenderingHandler> blockRenderers = new ArrayList<ISimpleBlockRenderingHandler>();

	public static Block blockEridium, blockAesthetic, blockAestheticStructure, blockChestHungry, blockChestHungryTrapped, blockChestHungryEnder, blockChestHungryAlchemical, blockChestHungryMetal;

	public static void init() {
		blocks.put(blockEridium = new BlockEridium().setBlockName("eridium").setHardness(50F).setResistance(2000F).setStepSound(Block.soundTypePiston), BlockMeta.class);

		blocks.put(blockAestheticStructure = new BlockAestheticStructure().setBlockName("aesthetic_structure"), BlockMeta.class);
		blockRenderers.add(new BlockAestheticStructureRenderer());

		tiles.add(TileChestHungry.class);
		tileRenderers.put(TileChestHungry.class, new TileChestHungryRenderer());
		blocks.put(blockChestHungry = new BlockChestHungry(0).setBlockName("hungry_chest"), null);
		blocks.put(blockChestHungryTrapped = new BlockChestHungry(1).setBlockName("trapped_hungry_chest"), null);
		blockRenderers.add(new ChestRenderer(BlockChestHungry.renderID, new TileChestHungry()));

		tiles.add(TileChestHungryEnder.class);
		tileRenderers.put(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		blocks.put(blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F), null);
		blockRenderers.add(new ChestRenderer(BlockChestHungryEnder.renderID, new TileChestHungryEnder()));

		if (Loader.isModLoaded("EE3")) {
			tiles.add(TileChestHungryAlchemical.class);
			tileRenderers.put(TileChestHungryAlchemical.class, new TileChestHungryAlchemicalRenderer());
			blocks.put(blockChestHungryAlchemical = new BlockChestHungryAlchemical().setBlockName("hungry_alchemical_chest"), ItemBlockAlchemicalChest.class);
			blockRenderers.add(new ChestRenderer(BlockChestHungryAlchemical.renderID, new ResourceLocation[] {
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"),
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"),
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png")
			}));
		}

		if (Loader.isModLoaded("IronChest")) {
			tiles.add(TileChestHungryMetal.class);
			tileRenderers.put(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
			blocks.put(blockChestHungryMetal = new BlockChestHungryMetal().setBlockName("hungry_metal_chest").setHardness(3F), BlockMeta.class);
			blockRenderers.add(new ChestRenderer(BlockChestHungryMetal.renderID, TextureHelper.ironChestTextures));
		}
	}

	public static void postInit() {
		OreDictionary.registerOre("oreEridium", new ItemStack(blockEridium, 1, 0));
	}
}