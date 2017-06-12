package T145.magistics.tiles.cosmetic;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileNitor extends TileEntity implements ITickable {

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void update() {
		if (world.isRemote) {
			IBlockState state = world.getBlockState(pos);
			int color = state.getBlock().getMapColor(state).colorValue;

			/*if (world.rand.nextInt(9 - FXCreator.INSTANCE.particleCount(2)) == 0) {
				FXCreator.INSTANCE.wispFX3(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, pos.getX() + 0.3F + world.rand.nextFloat() * 0.4F, pos.getY() + 0.5F, pos.getZ() + 0.3F + world.rand.nextFloat() * 0.4F, 0.5F, 4, true, -0.025F);
			}

			if (world.rand.nextInt(15 - FXCreator.INSTANCE.particleCount(4)) == 0) {
				FXCreator.INSTANCE.wispFX3(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, pos.getX() + 0.4F + world.rand.nextFloat() * 0.2F, pos.getY() + 0.5F, pos.getZ() + 0.4F + world.rand.nextFloat() * 0.2F, 0.25F, 1, true, -0.02F);
			}*/
		}
	}
}