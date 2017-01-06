package T145.magistics.client.lib.events;

import T145.magistics.Magistics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class IconAtlas {

	public static final IconAtlas INSTANCE = new IconAtlas();

	public TextureAtlasSprite spriteVis;
	public TextureAtlasSprite spriteConduit;

	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event) {}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre event) {
		spriteVis = registerSprite(event.getMap(), "vis", "misc");
		spriteConduit = registerSprite(event.getMap(), "conduit", "blocks");
	}

	public TextureAtlasSprite registerSprite(TextureMap map, String name) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, name));
	}

	public TextureAtlasSprite registerSprite(TextureMap map, String name, String dir) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, dir + "/" + name));
	}

	@SubscribeEvent
	public void dumpAtlas(ArrowLooseEvent event) {}
}