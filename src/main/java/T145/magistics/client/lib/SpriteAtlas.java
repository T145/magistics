package T145.magistics.client.lib;

import T145.magistics.Magistics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Magistics.MODID, value = Side.CLIENT)
public class SpriteAtlas {

	public static TextureAtlasSprite quintSprite;
	public static TextureAtlasSprite conduitSprite;

	public static TextureAtlasSprite registerSprite(TextureMap map, String name) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, name));
	}

	public static TextureAtlasSprite registerSprite(TextureMap map, String name, String dir) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, dir + "/" + name));
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent event) {
		quintSprite = registerSprite(event.getMap(), "vis", "misc");
		conduitSprite = registerSprite(event.getMap(), "conduit_valve", "blocks/transport");
	}
}