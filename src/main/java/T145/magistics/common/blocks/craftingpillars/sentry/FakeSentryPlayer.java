package T145.magistics.common.blocks.craftingpillars.sentry;

import java.util.UUID;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

import com.mojang.authlib.GameProfile;

public class FakeSentryPlayer extends FakePlayer {
	public static UUID sentryPillarUUID = UUID.randomUUID();

	public FakeSentryPlayer(World world) {
		super((WorldServer) world, new GameProfile(sentryPillarUUID, "Sentry Pillar"));
	}
}