package T145.magistics.network;

import T145.magistics.api.variants.EnumInfuser;
import T145.magistics.api.variants.IVariant;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.tiles.machines.TileInfuser;
import T145.magistics.tiles.machines.TileInfuserDark;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		default:
			return null;
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		for (EnumInfuser type : EnumInfuser.values()) {
			registerBlockModel(infuser, type.ordinal(), type);
			registerBlockModel(infuser, type.ordinal(), "inventory," + type.getClientName());
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuserDark.class, new RenderInfuser());
	}

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
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}