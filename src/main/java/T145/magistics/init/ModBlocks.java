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

	static {
		crucible = new BlockCrucible();
		infuser = new BlockInfuser();
		tank = new BlockTank();
		conduit = new BlockConduit();
		elevator = new BlockElevator();
		forge = new BlockForge();
		chestHungry = new BlockChestHungry();
		chestVoid = new BlockChestVoid();
		voidBorder = new BlockVoidBorder();

		saplings = new BlockSaplings();
		planks = new BlockPlanks();
		logs = new BlockLogs();
		leaves = new BlockLeaves();
		//crystal = new BlockCrystal();
		nitor = new BlockNitor();
	}

	public static Block infuser;
	public static Block tank;
	public static Block conduit;
	public static Block crucible;
	public static Block elevator;
	public static Block forge;
	public static Block chestHungry;
	public static Block chestVoid;
	public static Block voidBorder;

	public static Block saplings;
	public static Block planks;
	public static Block logs;
	public static Block leaves;

	public static Block crystal;
	public static Block nitor;

	public static void init() {}

	@SideOnly(Side.CLIENT)
	public static void initClient() { // NOTE: ALWAYS REGISTER THE LAST MODEL TO BE RENDERED IN THE INVENTORY
		ModelLoader.setCustomStateMapper(infuser, ((BlockInfuser) infuser).getStateMap());

		for (EnumInfuser type : EnumInfuser.values()) {
			ModelBakery.registerBlockModel(infuser, type.ordinal(), type.getName() + "_infuser", "inventory");
		}

		for (EnumTank type : EnumTank.values()) {
			ModelBakery.registerBlockModel(tank, type.ordinal(), type);
		}

		ModelBakery.registerBlockModel(conduit, 0, "inventory");

		for (EnumCrucible type : EnumCrucible.values()) {
			ModelBakery.registerBlockModel(crucible, type.ordinal(), ModelBakery.getVariantName(type) + ",working=false");
		}

		ModelBakery.registerBlockModel(elevator, 0, "normal");

		for (EnumForge type : EnumForge.values()) {
			ModelBakery.registerBlockModel(forge, type.ordinal(), "inventory," + ModelBakery.getVariantName(type));
		}

		for (EnumChestHungry type : EnumChestHungry.values()) {
			ModelBakery.registerBlockModel(chestHungry, type.ordinal(), type);
		}

		ModelBakery.registerBlockModel(chestVoid, 0, "inventory");
		ModelBakery.registerBlockModel(voidBorder, 0, "inventory");

		for (EnumWood type : EnumWood.values()) {
			ModelBakery.registerBlockModel(leaves, type.ordinal(), type);
			ModelBakery.registerBlockModel(logs, type.ordinal(), "axis=y,variant=" + type.getName());
			ModelBakery.registerBlockModel(planks, type.ordinal(), type);
			ModelBakery.registerBlockModel(saplings, type.ordinal(), type);
		}

		for (EnumDyeColor type : EnumDyeColor.values()) {
			ModelBakery.registerBlockModel(nitor, type.ordinal(), "variant=" + type.getName());
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