package T145.magistics.client.lib;

import T145.magistics.Magistics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientBakery {

	public static final ClientBakery INSTANCE = new ClientBakery();

	public TextureAtlasSprite quintFluid;

	public TextureAtlasSprite registerSprite(TextureMap map, String name) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, name));
	}

	public TextureAtlasSprite registerSprite(TextureMap map, String name, String dir) {
		return map.registerSprite(new ResourceLocation(Magistics.MODID, dir + "/" + name));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onModelBake(ModelBakeEvent event) {}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent event) {
		quintFluid = registerSprite(event.getMap(), "vis", "fluids");
	}

	@SubscribeEvent
	public void dumpAtlas(ArrowLooseEvent event) {}
}