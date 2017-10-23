package T145.magistics.client.lib;

import T145.magistics.Magistics;
import T145.magistics.api.variants.Aspect;
import T145.magistics.client.render.blocks.RenderChestVoidModel;
import T145.magistics.client.render.blocks.RenderVoidBorderModel;
import T145.magistics.client.render.models.blocks.ModelCrystal;
import T145.magistics.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
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
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Magistics.MODID, value = Side.CLIENT)
public class ModelBakery {

	private static final ModelResourceLocation MODEL_RESOURCE_CHEST_VOID = new ModelResourceLocation(ModBlocks.CHEST_VOID.getRegistryName(), "inventory");
	private static final ModelResourceLocation MODEL_RESOURCE_VOID_BORDER = new ModelResourceLocation(ModBlocks.VOID_BORDER.getRegistryName(), "inventory");
	private static final ModelResourceLocation[] CRYSTAL_MODELS = new ModelResourceLocation[Aspect.values().length];

	public static TextureAtlasSprite quintSprite;
	public static TextureAtlasSprite conduitSprite;
	public static TextureAtlasSprite crystalSprite;

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

		event.getModelRegistry().putObject(MODEL_RESOURCE_CHEST_VOID, new RenderChestVoidModel(chestVoidModel));
		event.getModelRegistry().putObject(MODEL_RESOURCE_VOID_BORDER, new RenderVoidBorderModel(voidBorderModel));

		for (int i = 0; i < Aspect.values().length; ++i) {
			event.getModelRegistry().putObject(CRYSTAL_MODELS[i], new ModelCrystal(crystalSprite));
		}
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent event) {
		quintSprite = registerSprite(event.getMap(), "vis", "misc");
		conduitSprite = registerSprite(event.getMap(), "conduit_valve", "blocks/transport");
		crystalSprite = registerSprite(event.getMap(), "crystal", "blocks");
	}

	@SubscribeEvent
	public static void dumpAtlas(ArrowLooseEvent event) {}

	static {
		for (int a = 0; a < Aspect.values().length; a++) {
			CRYSTAL_MODELS[a] = new ModelResourceLocation(GameData.getBlockRegistry().getNameForObject(Aspect.values()[a].getOre()), "normal");

			final ModelResourceLocation model = CRYSTAL_MODELS[a];

			ModelLoader.setCustomStateMapper(Aspect.values()[a].getOre(), new StateMapperBase() {
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					return model;
				}
			});
		}
	}
}