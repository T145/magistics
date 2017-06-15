package T145.magistics.client.lib;

import T145.magistics.Magistics;
import T145.magistics.client.render.blocks.RenderChestVoidModel;
import T145.magistics.client.render.blocks.RenderCrystalModel;
import T145.magistics.client.render.blocks.RenderVoidBorderModel;
import T145.magistics.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Magistics.MODID, value = Side.CLIENT)
public class ModelBakery {

	private static final ModelResourceLocation MODEL_RESOURCE_CHEST_VOID = new ModelResourceLocation(ModBlocks.CHEST_VOID.getRegistryName(), "inventory");
	private static final ModelResourceLocation MODEL_RESOURCE_VOID_BORDER = new ModelResourceLocation(ModBlocks.VOID_BORDER.getRegistryName(), "inventory");
	private static final ModelResourceLocation MODEL_RESOURCE_CRYSTAL_AIR = new ModelResourceLocation(ModBlocks.CRYSTAL.getRegistryName(), "variant=air");
	private static final ModelResourceLocation MODEL_RESOURCE_CRYSTAL_FIRE = new ModelResourceLocation(ModBlocks.CRYSTAL.getRegistryName(), "variant=fire");
	private static final ModelResourceLocation MODEL_RESOURCE_CRYSTAL_WATER = new ModelResourceLocation(ModBlocks.CRYSTAL.getRegistryName(), "variant=water");
	private static final ModelResourceLocation MODEL_RESOURCE_CRYSTAL_EARTH = new ModelResourceLocation(ModBlocks.CRYSTAL.getRegistryName(), "variant=earth");
	private static final ModelResourceLocation MODEL_RESOURCE_CRYSTAL_MAGIC = new ModelResourceLocation(ModBlocks.CRYSTAL.getRegistryName(), "variant=magic");
	private static final ModelResourceLocation MODEL_RESOURCE_CRYSTAL_VOID = new ModelResourceLocation(ModBlocks.CRYSTAL.getRegistryName(), "variant=void");

	public static TextureAtlasSprite quintFluid;
	public static TextureAtlasSprite conduitPart;

	public static TextureAtlasSprite registerSprite(TextureMap map, String name) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, name));
	}

	public static TextureAtlasSprite registerSprite(TextureMap map, String name, String dir) {
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

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {}

	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event) {
		IBakedModel chestVoidModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CHEST_VOID);
		IBakedModel voidBorderModel = event.getModelRegistry().getObject(MODEL_RESOURCE_VOID_BORDER);
		IBakedModel airCrystalModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CRYSTAL_AIR);
		IBakedModel fireCrystalModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CRYSTAL_FIRE);
		IBakedModel waterCrystalModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CRYSTAL_WATER);
		IBakedModel earthCrystalModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CRYSTAL_EARTH);
		IBakedModel magicCrystalModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CRYSTAL_MAGIC);
		IBakedModel voidCrystalModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CRYSTAL_VOID);

		event.getModelRegistry().putObject(MODEL_RESOURCE_CHEST_VOID, new RenderChestVoidModel(chestVoidModel));
		event.getModelRegistry().putObject(MODEL_RESOURCE_VOID_BORDER, new RenderVoidBorderModel(voidBorderModel));
		event.getModelRegistry().putObject(MODEL_RESOURCE_CRYSTAL_AIR, new RenderCrystalModel(airCrystalModel, 0));
		event.getModelRegistry().putObject(MODEL_RESOURCE_CRYSTAL_FIRE, new RenderCrystalModel(fireCrystalModel, 1));
		event.getModelRegistry().putObject(MODEL_RESOURCE_CRYSTAL_WATER, new RenderCrystalModel(waterCrystalModel, 2));
		event.getModelRegistry().putObject(MODEL_RESOURCE_CRYSTAL_EARTH, new RenderCrystalModel(earthCrystalModel, 3));
		event.getModelRegistry().putObject(MODEL_RESOURCE_CRYSTAL_MAGIC, new RenderCrystalModel(magicCrystalModel, 4));
		event.getModelRegistry().putObject(MODEL_RESOURCE_CRYSTAL_VOID, new RenderCrystalModel(voidCrystalModel, 5));
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent event) {
		quintFluid = registerSprite(event.getMap(), "vis", "misc");
		conduitPart = registerSprite(event.getMap(), "conduit_valve", "blocks/transport");
	}

	@SubscribeEvent
	public static void dumpAtlas(ArrowLooseEvent event) {}
}