package T145.magistics.client.lib;

import T145.magistics.core.Magistics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Magistics.ID, value = Side.CLIENT)
public class SpriteAtlas {

	private SpriteAtlas() {}

	public static TextureAtlasSprite quintSprite;
	public static TextureAtlasSprite overflowSprite;

	public static TextureAtlasSprite registerSprite(TextureMap map, String name) {
		return map.registerSprite(new ResourceLocation(Magistics.ID, name));
	}

	public static TextureAtlasSprite registerSprite(TextureMap map, String name, String dir) {
		return map.registerSprite(new ResourceLocation(Magistics.ID, dir + "/" + name));
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent event) {
		quintSprite = registerSprite(event.getMap(), "vis", "misc");
		overflowSprite = registerSprite(event.getMap(), "overflow", "misc");
	}
}