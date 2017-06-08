package T145.magistics.world.teleporters;

import T145.magistics.config.ConfigMain;
import T145.magistics.init.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterVoidWorld extends Teleporter {

	private final WorldServer world;
	private BlockPos pos;

	public TeleporterVoidWorld(WorldServer world, BlockPos pos) {
		super(world);
		this.world = world;
		this.pos = pos;
	}

	@Override
	public void placeInPortal(Entity entity, float rotationYaw) {
		if (world.provider.getDimension() != ConfigMain.voidDimensionId && entity instanceof EntityPlayer) {
			BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos.getX(), 0, pos.getZ());
			for (int y = 0; y < 256; y++) {
				mutableBlockPos.setY(y);
			}
			pos = ((EntityPlayer) entity).getBedLocation(world.provider.getDimension());
			if (pos == null) {
				pos = world.provider.getRandomizedSpawnPoint();
			}
		}

		if (world.provider.getDimension() == ConfigMain.voidDimensionId) {
			pos = new BlockPos(pos.getX(), 64, pos.getZ());

			for (int x = -3; x < 4; x++) {
				for (int z = -3; z < 4; z++) {
					if (world.isAirBlock(pos.add(x, 0, z))) {
						world.setBlockState(pos.add(x, 0, z), ModBlocks.voidBorder.getDefaultState());
					}
				}
			}
		}

		entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, entity.rotationYaw, 0.0F);
		entity.motionX = 0.0D;
		entity.motionY = 0.0D;
		entity.motionZ = 0.0D;
	}
}