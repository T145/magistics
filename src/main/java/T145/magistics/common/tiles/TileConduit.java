package T145.magistics.common.tiles;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.IQuintHandler;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.common.blocks.BlockConduit;
import T145.magistics.common.tiles.base.TileBase;
import T145.magistics.core.Magistics;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileBase implements ITickable, IQuintContainer {

	private float quints;
	private int suction;

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return true;
	}

	@Override
	public int getSuction() {
		return suction;
	}

	@Override
	public void setSuction(int suction) {
		this.suction = suction;
	}

	@Override
	public float getQuints() {
		return suction;
	}

	@Override
	public void setQuints(float quints) {
		this.quints = quints;
	}

	@Override
	public float getCapacity() {
		return 4F;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setFloat("Quints", quints);
		tag.setInteger("Suction", suction);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		quints = tag.getFloat("Quints");
		suction = tag.getInteger("Suction");
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		setSuction(0);

		Block source = getState().getBlock();

		if (source instanceof BlockConduit) {
			BlockConduit conduit = (BlockConduit) source;

			for (EnumFacing side : EnumFacing.VALUES) {
				if (conduit.isConnected(getState(), side)) {
					IQuintHandler handler = QuintHelper.getConnectedHandler(world, pos, side);

					if (handler != null) {
						Magistics.LOG.info("Side: " + side.getName());
						int neighborSuction = handler.getSuction() - 1;

						if (suction < neighborSuction) {
							setSuction(neighborSuction);
						}
					}
				}
			}
			
			if (suction > 0) {
				for (EnumFacing side : EnumFacing.VALUES) {
					if (conduit.isConnected(getState(), side)) {
						IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, side);
						
						if (container != null) {
							if (suction > container.getSuction()) {
								if (quints < getCapacity() && container.getQuints() > 0) {
									
								}
							} else {
								
							}
						}
					}
				}
			}
		}
	}
}