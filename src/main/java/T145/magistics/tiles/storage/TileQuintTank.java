package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.blocks.storage.BlockQuintTank.TankType;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageQuintLevel;
import T145.magistics.tiles.base.TileSynchronized;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileQuintTank extends TileSynchronized implements ITickable, IQuintContainer {

	private TankType type;
	private float quints;
	private int suction;
	private short updateTicks;

	public TileQuintTank(TankType type) {
		this.type = type;
	}

	public TileQuintTank() {
		this(TankType.BASE);
	}

	public TankType getTankType() {
		return type;
	}

	public boolean canConnectToTank(EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos.offset(facing));

		if (tile instanceof TileQuintTank && facing.getAxis() == EnumFacing.Axis.Y) {
			TileQuintTank neighborTank = (TileQuintTank) tile;
			return type == neighborTank.getTankType();
		}

		return false;
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return true;
	}

	@Override
	public float getQuints() {
		return quints;
	}

	@Override
	public void setQuints(float quints) {
		this.quints = quints;
	}

	@Override
	public float getCapacity() {
		return type.getCapacity();
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
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setString("Type", type.toString());
		nbt.setFloat("Quints", quints);
		nbt.setInteger("Suction", suction);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		type = TankType.valueOf(nbt.getString("Type"));
		quints = nbt.getFloat("Quints");
		suction = nbt.getInteger("Suction");
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		if (++updateTicks == 10) {
			updateTicks = 0;
			PacketHandler.sendToAllAround(new MessageQuintLevel(pos, quints, suction), world, pos);
			setSuction(10);
		}
	}
}