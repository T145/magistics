package T145.magistics.init;

import T145.magistics.Magistics;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSounds {

	public static SoundEvent attach;
	public static SoundEvent beamloop;
	public static SoundEvent bubbling;
	public static SoundEvent creaking1;
	public static SoundEvent creaking2;
	public static SoundEvent elecloop;
	public static SoundEvent fireloop;
	public static SoundEvent gore1;
	public static SoundEvent gore2;
	public static SoundEvent heal;
	public static SoundEvent infuser;
	public static SoundEvent infuserdark;
	public static SoundEvent learn;
	public static SoundEvent monolith;
	public static SoundEvent monolithfound;
	public static SoundEvent page1;
	public static SoundEvent page2;
	public static SoundEvent pclose;
	public static SoundEvent place;
	public static SoundEvent podburst;
	public static SoundEvent popen;
	public static SoundEvent recover;
	public static SoundEvent roots;
	public static SoundEvent rumble;
	public static SoundEvent rune_set;
	public static SoundEvent scribble;
	public static SoundEvent shock1;
	public static SoundEvent shock2;
	public static SoundEvent singularity;
	public static SoundEvent stomp1;
	public static SoundEvent stomp2;
	public static SoundEvent stoneclose;
	public static SoundEvent stoneopen;
	public static SoundEvent suck;
	public static SoundEvent swing1;
	public static SoundEvent swing2;
	public static SoundEvent tinkering;
	public static SoundEvent tool1;
	public static SoundEvent tool2;
	public static SoundEvent upgrade;
	public static SoundEvent whisper;
	public static SoundEvent wind1;
	public static SoundEvent wind2;
	public static SoundEvent zap1;
	public static SoundEvent zap2;

	public static void registerSounds() {
		attach = registerSound("attach");
		beamloop = registerSound("beamloop");
		bubbling = registerSound("bubbling");
		creaking1 = registerSound("creaking1");
		creaking2 = registerSound("creaking2");
		elecloop = registerSound("elecloop");
		fireloop = registerSound("fireloop");
		gore1 = registerSound("gore1");
		gore2 = registerSound("gore2");
		heal = registerSound("heal");
		infuser = registerSound("infuser");
		infuserdark = registerSound("infuserdark");
		learn = registerSound("learn");
		monolith = registerSound("monolith");
		monolithfound = registerSound("monolithfound");
		page1 = registerSound("page1");
		page2 = registerSound("page2");
		pclose = registerSound("pclose");
		place = registerSound("place");
		podburst = registerSound("podburst");
		popen = registerSound("popen");
		recover = registerSound("recover");
		roots = registerSound("roots");
		rumble = registerSound("rumble");
		rune_set = registerSound("rune_set");
		scribble = registerSound("scribble");
		shock1 = registerSound("shock1");
		shock2 = registerSound("shock2");
		singularity = registerSound("singularity");
		stomp1 = registerSound("stomp1");
		stomp2 = registerSound("stomp2");
		stoneclose = registerSound("stoneclose");
		stoneopen = registerSound("stoneopen");
		suck = registerSound("suck");
		swing1 = registerSound("swing1");
		swing2 = registerSound("swing2");
		tinkering = registerSound("tinkering");
		tool1 = registerSound("tool1");
		tool2 = registerSound("tool2");
		upgrade = registerSound("upgrade");
		upgrade = registerSound("upgrade");
		wind1 = registerSound("wind1");
		wind2 = registerSound("wind2");
		zap1 = registerSound("zap1");
		zap2 = registerSound("zap2");
	}

	private static SoundEvent registerSound(String soundName) {
		ResourceLocation resource = new ResourceLocation(Magistics.MODID, soundName);
		SoundEvent event = new SoundEvent(resource);
		return GameRegistry.register(event, resource);
	}
}