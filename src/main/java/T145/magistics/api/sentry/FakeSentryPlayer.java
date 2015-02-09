package T145.magistics.api.sentry;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

import com.mojang.authlib.GameProfile;

public class FakeSentryPlayer extends FakePlayer {
	public FakeSentryPlayer(World world) {
		super((WorldServer) world, (GameProfile) GameProfileCompatibility.get("", "Sentry Pillar"));
	}
}