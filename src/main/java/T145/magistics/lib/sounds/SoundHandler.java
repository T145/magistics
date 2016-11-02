package T145.magistics.lib.sounds;

import T145.magistics.Magistics;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundHandler {

	public static SoundEvent bubbling;
	public static SoundEvent infuser;
	public static SoundEvent infuserDark;

	private static int size = 0;

	public static void registerSounds() {
		size = SoundEvent.REGISTRY.getKeys().size();
		bubbling = registerSound("bubbling");
		infuser = registerSound("infuser");
		infuserDark = registerSound("infuserdark");
	}

	private static SoundEvent registerSound(String name) {
		ResourceLocation loc = new ResourceLocation(Magistics.MODID, name);
		SoundEvent sound = new SoundEvent(loc);

		SoundEvent.REGISTRY.register(size, loc, sound);
		++size;

		return sound;
	}
}