package T145.magistics.lib.sounds;

import T145.magistics.Magistics;
import net.minecraft.util.ResourceLocation;

public class SoundHandler {

	public static CustomSoundEvent BUBBLING;

	public static void registerSounds() {
		BUBBLING = new CustomSoundEvent(new ResourceLocation(Magistics.MODID, "bubbling"));
	}
}