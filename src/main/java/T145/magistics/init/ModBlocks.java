package T145.magistics.init;

import T145.magistics.api.variants.blocks.EnumChestHungry;
import T145.magistics.api.variants.blocks.EnumCrucible;
import T145.magistics.api.variants.blocks.EnumForge;
import T145.magistics.api.variants.blocks.EnumInfuser;
import T145.magistics.api.variants.blocks.EnumTank;
import T145.magistics.blocks.crafting.BlockCrucible;
import T145.magistics.blocks.crafting.BlockForge;
import T145.magistics.blocks.crafting.BlockInfuser;
import T145.magistics.blocks.devices.BlockChestHungry;
import T145.magistics.blocks.devices.BlockChestVoid;
import T145.magistics.blocks.devices.BlockElevator;
import T145.magistics.blocks.devices.BlockVoidBorder;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.blocks.storage.BlockTank;
import T145.magistics.client.lib.ClientBakery;
import T145.magistics.client.render.blocks.RenderChestHungry;
import T145.magistics.client.render.blocks.RenderChestVoid;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.client.render.blocks.RenderTank;
import T145.magistics.client.render.blocks.RenderVoidBorder;
import T145.magistics.tiles.crafting.TileCrucible;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import T145.magistics.tiles.devices.TileChestVoid;
import T145.magistics.tiles.devices.TileVoidBorder;
import T145.magistics.tiles.storage.TileTank;
import net.minecraft.block.Block;
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
	public static Block chestVoid;
	public static Block voidBorder;

	public static void init() {
		crucible = new BlockCrucible();
		infuser = new BlockInfuser();
		tank = new BlockTank();
		conduit = new BlockConduit();
		elevator = new BlockElevator();
		forge = new BlockForge();
		chestHungry = new BlockChestHungry();
		chestVoid = new BlockChestVoid();
		voidBorder = new BlockVoidBorder();
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() { // NOTE: ALWAYS REGISTER THE LAST MODEL TO BE RENDERED IN THE INVENTORY
		ModelLoader.setCustomStateMapper(infuser, ((BlockInfuser) infuser).getStateMap());

		for (EnumInfuser type : EnumInfuser.values()) {
			ClientBakery.registerBlockModel(infuser, type.ordinal(), type.getName() + "_infuser", "inventory");
		}

		for (EnumTank type : EnumTank.values()) {
			ClientBakery.registerBlockModel(tank, type.ordinal(), type);
		}

		ClientBakery.registerBlockModel(conduit, 0, "inventory");

		for (EnumCrucible type : EnumCrucible.values()) {
			ClientBakery.registerBlockModel(crucible, type.ordinal(), ClientBakery.getVariantName(type) + ",working=false");
		}

		ClientBakery.registerBlockModel(elevator, 0, "normal");

		for (EnumForge type : EnumForge.values()) {
			ClientBakery.registerBlockModel(forge, type.ordinal(), "inventory," + ClientBakery.getVariantName(type));
		}

		for (EnumChestHungry type : EnumChestHungry.values()) {
			ClientBakery.registerBlockModel(chestHungry, type.ordinal(), type);
		}

		ClientBakery.registerBlockModel(chestVoid, 0, "inventory");
		ClientBakery.registerBlockModel(voidBorder, 0, "inventory");

		ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungry.class, new RenderChestHungry());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestVoid.class, new RenderChestVoid());
		ClientRegistry.bindTileEntitySpecialRenderer(TileVoidBorder.class, new RenderVoidBorder());
	}
}