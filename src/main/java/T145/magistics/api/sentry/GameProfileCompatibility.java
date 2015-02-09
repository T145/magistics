package T145.magistics.api.sentry;

import java.lang.reflect.Constructor;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.common.Loader;

public class GameProfileCompatibility {
	public static UUID sentryPillarUUID = UUID.fromString("sentry");

	public static Object get(String id, String name) {
		if (Loader.instance().getMinecraftModContainer().getVersion().equals("1.7.2")) {
			try {
				Class<?> fake = Class.forName("com.mojang.authlib.GameProfile");
				Constructor<?> constructor = fake.getConstructor(String.class, String.class);
				return fake.cast(constructor.newInstance("sentry", name));
			} catch (Exception e) {
				System.out.println("Exception in CraftingPillars mod Sentry Pillar: " + e.getMessage());
				return null;
			}
		} else {
			return new GameProfile(sentryPillarUUID, "Sentry Pillar");
		}
	}
}