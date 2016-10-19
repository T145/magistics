package T145.magistics.lib.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class CustomSoundEvent extends SoundEvent {

	public int soundEventId = ReflectionHelper.getPrivateValue(SoundEvent.class, this, "soundEventId");

	public CustomSoundEvent(ResourceLocation name) {
		super(name);
		registerSound(name);
	}

	private void registerSound(ResourceLocation name) {
		REGISTRY.register(soundEventId++, name, new SoundEvent(name));
	}
}