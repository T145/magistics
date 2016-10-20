package T145.magistics.lib.sounds;

import T145.magistics.Magistics;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundHandler {

	public static SoundEvent bubbling;

	private static int size = 0;

	public static void registerSounds() {
		size = SoundEvent.REGISTRY.getKeys().size();
		bubbling = registerSound("bubbling");
	}

	private static SoundEvent registerSound(String name) {
		ResourceLocation loc = new ResourceLocation(Magistics.MODID, name);
		SoundEvent sound = new SoundEvent(loc);

		SoundEvent.REGISTRY.register(size, loc, sound);
		++size;

		return sound;
	}
}