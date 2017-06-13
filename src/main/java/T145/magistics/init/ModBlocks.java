package T145.magistics.init;

import T145.magistics.api.variants.blocks.EnumChestHungry;
import T145.magistics.api.variants.blocks.EnumCrucible;
import T145.magistics.api.variants.blocks.EnumForge;
import T145.magistics.api.variants.blocks.EnumInfuser;
import T145.magistics.api.variants.blocks.EnumTank;
import T145.magistics.api.variants.blocks.EnumWood;
import T145.magistics.blocks.cosmetic.BlockLeaves;
import T145.magistics.blocks.cosmetic.BlockLogs;
import T145.magistics.blocks.cosmetic.BlockNitor;
import T145.magistics.blocks.cosmetic.BlockPlanks;
import T145.magistics.blocks.cosmetic.BlockSaplings;
import T145.magistics.blocks.cosmetic.BlockVoidBorder;
import T145.magistics.blocks.crafting.BlockCrucible;
import T145.magistics.blocks.crafting.BlockForge;
import T145.magistics.blocks.crafting.BlockInfuser;
import T145.magistics.blocks.devices.BlockChestHungry;
import T145.magistics.blocks.devices.BlockChestVoid;
import T145.magistics.blocks.devices.BlockElevator;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.blocks.storage.BlockTank;
import T145.magistics.client.lib.ModelBakery;
import T145.magistics.client.render.blocks.RenderChestHungry;
import T145.magistics.client.render.blocks.RenderChestVoid;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.client.render.blocks.RenderTank;
import T145.magistics.client.render.blocks.RenderVoidBorder;
import T145.magistics.tiles.cosmetic.TileVoidBorder;
import T145.magistics.tiles.crafting.TileCrucible;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import T145.magistics.tiles.devices.TileChestVoid;
import T145.magistics.tiles.storage.TileTank;
import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static final Block INFUSER = new BlockInfuser();
	public static final Block TANK = new BlockTank();
	public static final Block CONDUIT = new BlockConduit();
	public static final Block CRUCIBLE = new BlockCrucible();
	public static final Block ELEVATOR = new BlockElevator();
	public static final Block FORGE = new BlockForge();
	public static final Block CHEST_HUNGRY = new BlockChestHungry();
	public static final Block CHEST_VOID = new BlockChestVoid();
	public static final Block VOID_BORDER = new BlockVoidBorder();

	public static final Block SAPLINGS = new BlockSaplings();
	public static final Block PLANKS = new  BlockPlanks();
	public static final Block LOGS = new BlockLogs();
	public static final Block LEAVES = new BlockLeaves();

	public static Block crystal;
	public static final Block NITOR = new BlockNitor();

	public static void init() {}

	@SideOnly(Side.CLIENT)
	public static void initClient() { // NOTE: ALWAYS REGISTER THE LAST MODEL TO BE RENDERED IN THE INVENTORY
		ModelLoader.setCustomStateMapper(INFUSER, ((BlockInfuser) INFUSER).getStateMap());

		for (EnumInfuser type : EnumInfuser.values()) {
			ModelBakery.registerBlockModel(INFUSER, type.ordinal(), type.getName() + "_infuser", "inventory");
		}

		for (EnumTank type : EnumTank.values()) {
			ModelBakery.registerBlockModel(TANK, type.ordinal(), type);
		}

		ModelBakery.registerBlockModel(CONDUIT, 0, "inventory");

		for (EnumCrucible type : EnumCrucible.values()) {
			ModelBakery.registerBlockModel(CRUCIBLE, type.ordinal(), ModelBakery.getVariantName(type) + ",working=false");
		}

		ModelBakery.registerBlockModel(ELEVATOR, 0, "normal");

		for (EnumForge type : EnumForge.values()) {
			ModelBakery.registerBlockModel(FORGE, type.ordinal(), "inventory," + ModelBakery.getVariantName(type));
		}

		for (EnumChestHungry type : EnumChestHungry.values()) {
			ModelBakery.registerBlockModel(CHEST_HUNGRY, type.ordinal(), type);
		}

		ModelBakery.registerBlockModel(CHEST_VOID, 0, "inventory");
		ModelBakery.registerBlockModel(VOID_BORDER, 0, "inventory");

		for (EnumWood type : EnumWood.values()) {
			ModelBakery.registerBlockModel(LEAVES, type.ordinal(), type);
			ModelBakery.registerBlockModel(LOGS, type.ordinal(), "axis=y,variant=" + type.getName());
			ModelBakery.registerBlockModel(PLANKS, type.ordinal(), type);
			ModelBakery.registerBlockModel(SAPLINGS, type.ordinal(), type);
		}

		for (EnumDyeColor type : EnumDyeColor.values()) {
			ModelBakery.registerBlockModel(NITOR, type.ordinal(), "variant=" + type.getName());
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungry.class, new RenderChestHungry());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestVoid.class, new RenderChestVoid());
		ClientRegistry.bindTileEntitySpecialRenderer(TileVoidBorder.class, new RenderVoidBorder());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new RenderCrystal());
	}
}