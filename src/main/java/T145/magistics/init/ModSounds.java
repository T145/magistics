package T145.magistics.init;

import T145.magistics.Magistics;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSounds {

	public static SoundEvent bubbling;
	public static SoundEvent infuser;
	public static SoundEvent infuserDark;

	public static void registerSounds() {
		bubbling = registerSound("bubbling");
		infuser = registerSound("infuser");
		infuserDark = registerSound("infuserDark");
	}

	private static SoundEvent registerSound(String soundName) {
		ResourceLocation resource = new ResourceLocation(Magistics.MODID, soundName);
		SoundEvent event = new SoundEvent(resource);
		return GameRegistry.register(event, resource);
	}
}