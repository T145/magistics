package T145.magistics.lib.events;

import T145.magistics.Magistics;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundHandler {

	public static SoundEvent BUBBLING;
	public static SoundEvent INFUSER;
	public static SoundEvent INFUSER_DARK;

	private static int size = 0;

	public static void registerSounds() {
		size = SoundEvent.REGISTRY.getKeys().size();
		BUBBLING = registerSound("bubbling");
		INFUSER = registerSound("infuser");
		INFUSER_DARK = registerSound("infuserdark");
	}

	private static SoundEvent registerSound(String name) {
		ResourceLocation loc = new ResourceLocation(Magistics.MODID, name);
		SoundEvent sound = new SoundEvent(loc);

		SoundEvent.REGISTRY.register(size, loc, sound);
		++size;

		return sound;
	}
}