package T145.magistics.network.messages.client;

import java.io.IOException;

import T145.magistics.network.messages.MessageBase;
import T145.magistics.tiles.crafting.TileInfuser;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageInfuserProgress extends MessageBase {

	private float progress;
	private BlockPos pos;

	public MessageInfuserProgress() {}

	public MessageInfuserProgress(TileInfuser infuser, float progress) {
		this.progress = progress;
		pos = infuser.getPos();
	}

	public MessageInfuserProgress(TileInfuser infuser) {
		this(infuser, infuser.cookTime);
	}

	@Override
	public void serialize(PacketBuffer buffer) {
		buffer.writeFloat(progress);
		buffer.writeBlockPos(pos);
	}

	@Override
	public void deserialize(PacketBuffer buffer) throws IOException {
		progress = buffer.readFloat();
		pos = buffer.readBlockPos();
	}

	@Override
	public IMessage process(MessageContext context) {
		World world = FMLClientHandler.instance().getWorldClient();
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileInfuser) {
			TileInfuser infuser = (TileInfuser) tile;
			infuser.cookTime = progress;
		}
		return null;
	}
}