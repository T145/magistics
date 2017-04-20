package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.api.magic.QuintessenceHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class TileConduit extends MTile implements IQuintessenceContainer {

	private int suction;
	private float quintessence;

	@Override
	public boolean canConnect(EnumFacing facing) {
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
	public float getMaxQuintessence() {
		return 4;
	}

	@Override
	public float getQuintessence() {
		return quintessence;
	}

	@Override
	public void setQuintessence(float amount) {
		quintessence = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setInteger("Suction", suction);
		compound.setFloat("Quintessence", quintessence);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		setSuction(compound.getInteger("Suction"));
		setQuintessence(compound.getFloat("Quintessence"));
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			calculateSuction();

			if (suction > 0) {
				world.scheduleUpdate(pos, blockType, 10);
				equalizeWithNeighbors();
			}
		}
	}

	protected void calculateSuction() {
		setSuction(0);

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintessenceContainer source = QuintessenceHelper.getConnectedContainer(world, pos, facing);

			if (source != null && getSuction() < source.getSuction() - 1) {
				setSuction(source.getSuction() - 1);
			}
		}
	}

	protected void equalizeWithNeighbors() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintessenceContainer source = QuintessenceHelper.getConnectedContainer(world, pos, facing);

			if (quintessence < getMaxQuintessence() && suction > source.getSuction()) {
				float mod = Math.min(quintessence / getMaxQuintessence(), getMaxQuintessence());
				float diff = QuintessenceHelper.subtractQuints(source, Math.min(mod, getMaxQuintessence() - quintessence));

				if (suction > source.getSuction()) {
					quintessence += diff;
				} else {
					source.setQuintessence(diff + source.getQuintessence());
				}
			}
		}

		quintessence = MathHelper.clamp(quintessence, 0F, getMaxQuintessence());
	}

	public boolean isConnected(EnumFacing side) {
		return QuintessenceHelper.getConnectedManager(world, pos, side) != null;
	}
}