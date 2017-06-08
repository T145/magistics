package T145.magistics.lib.world;

import T145.magistics.init.ModDimensions;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class DimensionBlockPos {

	private final BlockPos pos;
	private final int dimension;

	public DimensionBlockPos(BlockPos pos, int dimension) {
		this.pos = pos;
		this.dimension = dimension;
	}

	public BlockPos getBlockPos() {
		return pos;
	}

	public int getDimension() {
		return dimension;
	}

	public TileEntity getTileEntity() {
		return ModDimensions.getWorldServerForDimension(dimension).getTileEntity(pos);
	}
}