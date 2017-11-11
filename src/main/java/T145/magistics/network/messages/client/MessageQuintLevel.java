package T145.magistics.network.messages.client;

import java.io.IOException;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.network.messages.MessageBase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageQuintLevel extends MessageBase {

	private BlockPos pos;
	private float quints;
	private int suction;

	public MessageQuintLevel() {}

	public MessageQuintLevel(BlockPos pos, float quints, int suction) {
		this.pos = pos;
		this.quints = quints;
		this.suction = suction;
	}

	@Override
	public void serialize(PacketBuffer buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeFloat(quints);
		buffer.writeInt(suction);
	}

	@Override
	public void deserialize(PacketBuffer buffer) throws IOException {
		pos = buffer.readBlockPos();
		quints = buffer.readFloat();
		suction = buffer.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage process(MessageContext context) {
		World world = FMLClientHandler.instance().getWorldClient();
		IQuintContainer container = (IQuintContainer) world.getTileEntity(pos);

		if (container != null) {
			container.setQuints(quints);
			container.setSuction(suction);
		}

		return null;
	}
}