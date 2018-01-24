package T145.magistics.api.front;

import T145.magistics.common.Magistics;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public interface IModelProvider {

	public static String getVariantName(IStringSerializable variant) {
		return "variant=" + variant.getName();
	}

	public static void registerBlockModel(Block block, int meta, String path, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Magistics.ID + ":" + path, variant));
	}

	public static void registerBlockModel(Block block, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
	}

	public static void registerBlockModel(Block block, int meta, IStringSerializable variant) {
		registerBlockModel(block, meta, "variant=" + variant.getName());
	}

	public static void registerItemModel(Item item, int meta, String path) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Magistics.ID + ":" + path, "inventory"));
	}

	public static void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerTileRenderer(Class tileClass, TileEntitySpecialRenderer tileRenderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileClass, tileRenderer);
	}

	void initModel();
}