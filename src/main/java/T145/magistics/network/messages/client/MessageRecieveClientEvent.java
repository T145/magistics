package T145.magistics.network.messages.client;

import java.io.IOException;

import T145.magistics.network.messages.MessageBase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageRecieveClientEvent extends MessageBase {

	private BlockPos pos;
	private int id;
	private int data;

	public MessageRecieveClientEvent() {
	}

	public MessageRecieveClientEvent(BlockPos pos, int id, int data) {
		this.pos = pos;
		this.id = id;
		this.data = data;
	}

	@Override
	public void serialize(PacketBuffer buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeInt(id);
		buffer.writeInt(data);
	}

	@Override
	public void deserialize(PacketBuffer buffer) throws IOException {
		pos = buffer.readBlockPos();
		id = buffer.readInt();
		data = buffer.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage process(MessageContext context) {
		World world = FMLClientHandler.instance().getWorldClient();
		TileEntity tile = world.getTileEntity(pos);

		if (tile != null) {
			tile.receiveClientEvent(id, data);
		}

		return null;
	}
}