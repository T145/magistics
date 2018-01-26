package T145.magistics.common.network.base;

import java.io.IOException;

import com.google.common.base.Throwables;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class MessageBase implements IMessage {

	@Override
	public final void toBytes(ByteBuf buffer) {
		serialize(new PacketBuffer(buffer));
	}

	@Override
	public final void fromBytes(ByteBuf buffer) {
		try {
			deserialize(new PacketBuffer(buffer));
		} catch (IOException err) {
			Throwables.propagate(err);
		}
	}

	public abstract void serialize(PacketBuffer buffer);

	public abstract void deserialize(PacketBuffer buffer) throws IOException;

	public abstract IMessage process(MessageContext context);
}