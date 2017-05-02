package T145.magistics.network;

import T145.magistics.api.ModBlocks;
import T145.magistics.api.variants.IVariant;
import T145.magistics.api.variants.blocks.EnumConduit;
import T145.magistics.api.variants.blocks.EnumCrucible;
import T145.magistics.api.variants.blocks.EnumInfuser;
import T145.magistics.api.variants.blocks.EnumTank;
import T145.magistics.client.fx.FXEngine;
import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.lib.ClientBakery;
import T145.magistics.client.render.blocks.RenderConduit;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.client.render.blocks.RenderTank;
import T145.magistics.lib.events.SoundHandler;
import T145.magistics.tiles.crafting.TileCrucible;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.crafting.TileInfuserDark;
import T145.magistics.tiles.storage.TileConduit;
import T145.magistics.tiles.storage.TileTank;
import T145.magistics.tiles.storage.TileTankReinforced;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	private void registerBlockModel(Block block, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
	}

	private void registerBlockModel(Block block, int meta, IVariant variant) {
		registerBlockModel(block, meta, variant.getClientName());
	}

	private void registerItemModel(Item item, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	private void registerItemModel(Item item, int meta, IVariant variant) {
		registerItemModel(item, meta, variant.getClientName());
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case 0:
			return new GuiInfuser(player.inventory, ((TileInfuser) tile));
		default:
			return null;
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		SoundHandler.registerSounds();

		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(ClientBakery.INSTANCE);
		MinecraftForge.EVENT_BUS.register(FXEngine.INSTANCE);

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
			registerBlockModel(ModBlocks.crucible, type.ordinal(), type);
		}

		registerBlockModel(ModBlocks.elevator, 0, "normal");

		ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuserDark.class, new RenderInfuser());

		ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileTankReinforced.class, new RenderTank());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}