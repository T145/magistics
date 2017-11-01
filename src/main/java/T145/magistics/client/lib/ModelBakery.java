package T145.magistics.client.lib;

import T145.magistics.Magistics;
import T145.magistics.client.render.blocks.RenderChestVoidModel;
import T145.magistics.client.render.blocks.RenderVoidBorderModel;
import T145.magistics.core.Init;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Magistics.MODID, value = Side.CLIENT)
public class ModelBakery {

	private static final ModelResourceLocation MODEL_RESOURCE_CHEST_VOID = new ModelResourceLocation(Init.CHEST_VOID.getRegistryName(), "inventory");
	private static final ModelResourceLocation MODEL_RESOURCE_VOID_BORDER = new ModelResourceLocation(Init.VOID_BORDER.getRegistryName(), "inventory");

	public static TextureAtlasSprite quintSprite;
	public static TextureAtlasSprite conduitSprite;
	public static TextureAtlasSprite crystalSprite;

	public static TextureAtlasSprite registerSprite(TextureMap map, String name) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, name));
	}

	public static TextureAtlasSprite registerSprite(TextureMap map, String name, String dir) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, dir + "/" + name));
	}

	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event) {
		IBakedModel chestVoidModel = event.getModelRegistry().getObject(MODEL_RESOURCE_CHEST_VOID);
		IBakedModel voidBorderModel = event.getModelRegistry().getObject(MODEL_RESOURCE_VOID_BORDER);
		event.getModelRegistry().putObject(MODEL_RESOURCE_CHEST_VOID, new RenderChestVoidModel(chestVoidModel));
		event.getModelRegistry().putObject(MODEL_RESOURCE_VOID_BORDER, new RenderVoidBorderModel(voidBorderModel));
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent event) {
		quintSprite = registerSprite(event.getMap(), "vis", "misc");
		conduitSprite = registerSprite(event.getMap(), "conduit_valve", "blocks/transport");
		crystalSprite = registerSprite(event.getMap(), "crystal", "blocks");
	}
}