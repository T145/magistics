package T145.magistics.client.lib;

import T145.magistics.Magistics;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientBakery {

	public static final ClientBakery INSTANCE = new ClientBakery();

	public TextureAtlasSprite quintFluid;
	public TextureAtlasSprite conduitPart;

	public TextureAtlasSprite registerSprite(TextureMap map, String name) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, name));
	}

	public TextureAtlasSprite registerSprite(TextureMap map, String name, String dir) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, dir + "/" + name));
	}

	public static String getVariantName(IStringSerializable variant) {
		return "variant=" + variant.getName();
	}

	public static void registerBlockModel(Block block, int meta, String path, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Magistics.MODID + ":" + path, variant));
	}

	public static void registerBlockModel(Block block, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
	}

	public static void registerBlockModel(Block block, int meta, IStringSerializable variant) {
		registerBlockModel(block, meta, "variant=" + variant.getName());
	}

	public static void registerItemModel(Item item, int meta, String path) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Magistics.MODID + ":" + path, "inventory"));
	}

	public static void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onModelBake(ModelBakeEvent event) {}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent event) {
		quintFluid = registerSprite(event.getMap(), "vis", "misc");
		conduitPart = registerSprite(event.getMap(), "conduit_valve", "blocks/transport");
	}

	@SubscribeEvent
	public void dumpAtlas(ArrowLooseEvent event) {}
}