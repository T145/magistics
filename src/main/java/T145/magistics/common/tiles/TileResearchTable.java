package T145.magistics.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileResearchTable extends TilePedestal implements ITickable {
	
	public static final short RANGE = 5;
	
	private short updateTicks;

	@Override
	public void writePacketNBT(NBTTagCompound nbt) {
	}

	@Override
	public void readPacketNBT(NBTTagCompound nbt) {
	}

	@Override
	public void update() {
		//if (this.getObservableStack() != researchitemstack) return;
		
		if (++updateTicks == 10) {
			updateTicks = 0;
			
			BlockPos start = pos.add(RANGE, 0, RANGE);
			
			for (short x = RANGE; x > 0; --x) {
				for (short z = RANGE; z > 0; --z) {
					
				}
			}
		}
	}
}