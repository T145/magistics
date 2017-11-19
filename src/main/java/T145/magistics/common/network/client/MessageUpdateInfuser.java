package T145.magistics.common.network.client;

import java.io.IOException;

import T145.magistics.common.network.base.MessageBase;
import T145.magistics.common.tiles.TileInfuser;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageUpdateInfuser extends MessageBase {

	private BlockPos pos;
	private int suction;
	private float progress;
	private float quintCost;

	public MessageUpdateInfuser() {}

	public MessageUpdateInfuser(TileInfuser infuser) {
		this.pos = infuser.getPos();
		this.suction = infuser.getSuction();
		this.progress = infuser.progress;
		this.quintCost = infuser.quintCost;
	}

	@Override
	public void serialize(PacketBuffer buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeInt(suction);
		buffer.writeFloat(progress);
		buffer.writeFloat(quintCost);
	}

	@Override
	public void deserialize(PacketBuffer buffer) throws IOException {
		pos = buffer.readBlockPos();
		suction = buffer.readInt();
		progress = buffer.readFloat();
		quintCost = buffer.readFloat();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage process(MessageContext context) {
		World world = FMLClientHandler.instance().getWorldClient();
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileInfuser) {
			TileInfuser infuser = (TileInfuser) tile;
			infuser.setSuction(suction);
			infuser.progress = progress;
			infuser.quintCost = quintCost;
		}

		return null;
	}
}