package T145.magistics.network.messages.client;

import java.io.IOException;

import T145.magistics.client.particles.core.ParticleManager;
import T145.magistics.network.messages.MessageBase;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSendWispFX extends MessageBase {

	private double x;
	private double y;
	private double z;
	private double destX;
	private double destY;
	private double destZ;
	private float gravity;
	private int type;

	public MessageSendWispFX() {}

	public MessageSendWispFX(double x, double y, double z, double destX, double destY, double destZ, float gravity, int type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.destX = destX;
		this.destY = destY;
		this.destZ = destZ;
		this.gravity = gravity;
		this.type = type;
	}

	@Override
	public void serialize(PacketBuffer buffer) {
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
		buffer.writeDouble(destX);
		buffer.writeDouble(destY);
		buffer.writeDouble(destZ);
		buffer.writeFloat(gravity);
		buffer.writeInt(type);
	}

	@Override
	public void deserialize(PacketBuffer buffer) throws IOException {
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		destX = buffer.readDouble();
		destY = buffer.readDouble();
		destZ = buffer.readDouble();
		gravity = buffer.readFloat();
		type = buffer.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage process(MessageContext context) {
		ParticleManager.customWispFX(FMLClientHandler.instance().getWorldClient(), x, y, z, destX, destY, destZ, gravity, type);
		return null;
	}
}