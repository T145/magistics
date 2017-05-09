package T145.magistics.init;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.variants.IVariant;
import T145.magistics.api.variants.blocks.EnumConduit;
import T145.magistics.api.variants.blocks.EnumCrucible;
import T145.magistics.api.variants.blocks.EnumForge;
import T145.magistics.api.variants.blocks.EnumInfuser;
import T145.magistics.api.variants.blocks.EnumTank;
import T145.magistics.blocks.crafting.BlockCrucible;
import T145.magistics.blocks.crafting.BlockForge;
import T145.magistics.blocks.crafting.BlockInfuser;
import T145.magistics.blocks.devices.BlockElevator;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.blocks.storage.BlockTank;
import T145.magistics.client.render.blocks.RenderConduit;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.client.render.blocks.RenderTank;
import T145.magistics.tiles.crafting.TileCrucible;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.crafting.TileInfuserDark;
import T145.magistics.tiles.storage.TileConduit;
import T145.magistics.tiles.storage.TileTank;
import T145.magistics.tiles.storage.TileTankReinforced;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static List<Block> blockRegistry = new ArrayList<Block>();

	public static Block infuser;
	public static Block tank;
	public static Block conduit;
	public static Block crucible;
	public static Block elevator;
	public static Block forge;

	public static void init() {
		blockRegistry.add(ModBlocks.crucible = new BlockCrucible());
		blockRegistry.add(ModBlocks.infuser = new BlockInfuser());
		blockRegistry.add(ModBlocks.tank = new BlockTank());
		blockRegistry.add(ModBlocks.conduit = new BlockConduit());
		blockRegistry.add(ModBlocks.elevator = new BlockElevator());
		blockRegistry.add(ModBlocks.forge = new BlockForge());
	}

	private static void registerBlockModel(Block block, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
	}

	private static void registerBlockModel(Block block, int meta, IVariant variant) {
		registerBlockModel(block, meta, variant.getClientName());
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		for (EnumInfuser type : EnumInfuser.values()) {
			registerBlockModel(ModBlocks.infuser, type.ordinal(), type);
			registerBlockModel(ModBlocks.infuser, type.ordinal(), "inventory," + type.getClientName());
		}

		for (EnumTank type : EnumTank.values()) {
			registerBlockModel(ModBlocks.tank, type.ordinal(), type);
		}

		for (EnumConduit type : EnumConduit.values()) {
			registerBlockModel(ModBlocks.conduit, type.ordinal(), type);
			registerBlockModel(ModBlocks.conduit, type.ordinal(), "inventory," + type.getClientName());
		}

		for (EnumCrucible type : EnumCrucible.values()) {
			registerBlockModel(ModBlocks.crucible, type.ordinal(), type.getClientName() + ",working=false");
			registerBlockModel(ModBlocks.crucible, type.ordinal(), type.getClientName() + ",working=true");
		}

		registerBlockModel(ModBlocks.elevator, 0, "normal");

		for (EnumForge type : EnumForge.values()) {
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=north," + type.getClientName() + ",working=false");
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=north," + type.getClientName() + ",working=true");
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=south," + type.getClientName() + ",working=false");
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=south," + type.getClientName() + ",working=true");
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=east," + type.getClientName() + ",working=false");
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=east," + type.getClientName() + ",working=true");
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=west," + type.getClientName() + ",working=false");
			registerBlockModel(ModBlocks.forge, type.ordinal(), "facing=west," + type.getClientName() + ",working=true");
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuserDark.class, new RenderInfuser());

		ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTankReinforced.class, new RenderTank());
	}
}