package T145.magistics.init;

import T145.magistics.Magistics;
import T145.magistics.api.variants.IVariant;
import T145.magistics.api.variants.blocks.EnumChestHungry;
import T145.magistics.api.variants.blocks.EnumCrucible;
import T145.magistics.api.variants.blocks.EnumForge;
import T145.magistics.api.variants.blocks.EnumInfuser;
import T145.magistics.api.variants.blocks.EnumTank;
import T145.magistics.blocks.crafting.BlockCrucible;
import T145.magistics.blocks.crafting.BlockForge;
import T145.magistics.blocks.crafting.BlockInfuser;
import T145.magistics.blocks.devices.BlockChestHungry;
import T145.magistics.blocks.devices.BlockElevator;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.blocks.storage.BlockTank;
import T145.magistics.client.render.blocks.RenderChestHungry;
import T145.magistics.client.render.blocks.RenderConduit;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.client.render.blocks.RenderTank;
import T145.magistics.tiles.crafting.TileCrucible;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import T145.magistics.tiles.storage.TileConduit;
import T145.magistics.tiles.storage.TileTank;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static Block infuser;
	public static Block tank;
	public static Block conduit;
	public static Block crucible;
	public static Block elevator;
	public static Block forge;
	public static Block chestHungry;

	public static void init() {
		crucible = new BlockCrucible();
		infuser = new BlockInfuser();
		tank = new BlockTank();
		conduit = new BlockConduit();
		elevator = new BlockElevator();
		forge = new BlockForge();
		chestHungry = new BlockChestHungry();
	}

	private static void registerBlockModel(Block block, int meta, String path, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Magistics.MODID + ":" + path, variant));
	}

	private static void registerBlockModel(Block block, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
	}

	private static void registerBlockModel(Block block, int meta, IVariant variant) {
		registerBlockModel(block, meta, variant.getClientName());
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() { // NOTE: ALWAYS REGISTER THE LAST MODEL TO BE RENDERED IN THE INVENTORY
		ModelLoader.setCustomStateMapper(infuser, ((BlockInfuser) infuser).getStateMap());

		for (EnumInfuser type : EnumInfuser.values()) {
			registerBlockModel(infuser, type.ordinal(), type.getName() + "_infuser", "inventory");
		}

		for (EnumTank type : EnumTank.values()) {
			registerBlockModel(tank, type.ordinal(), type);
		}

		registerBlockModel(conduit, 0, "inventory");

		for (EnumCrucible type : EnumCrucible.values()) {
			registerBlockModel(crucible, type.ordinal(), type.getClientName() + ",working=false");
		}

		registerBlockModel(elevator, 0, "normal");

		for (EnumForge type : EnumForge.values()) {
			registerBlockModel(forge, type.ordinal(), "inventory," + type.getClientName());
		}

		for (EnumChestHungry type : EnumChestHungry.values()) {
			registerBlockModel(chestHungry, type.ordinal(), type);
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungry.class, new RenderChestHungry());
	}
}