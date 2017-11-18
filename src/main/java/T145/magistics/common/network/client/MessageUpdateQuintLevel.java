package T145.magistics.common.network.client;

import java.io.IOException;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.common.network.base.MessageBase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageUpdateQuintLevel extends MessageBase {

	private BlockPos pos;

	public MessageUpdateQuintLevel() {}

	public MessageUpdateQuintLevel(BlockPos pos) {
		this.pos = pos;
	}

	public MessageUpdateQuintLevel(TileEntity tile) {
		this(tile.getPos());
	}

	@Override
	public void serialize(PacketBuffer buffer) {
		buffer.writeBlockPos(pos);
	}

	@Override
	public void deserialize(PacketBuffer buffer) throws IOException {
		pos = buffer.readBlockPos();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage process(MessageContext context) {
		World world = FMLClientHandler.instance().getWorldClient();
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof IQuintContainer) {
			IQuintContainer container = (IQuintContainer) tile;
			container.setQuints(container.getQuints());
		}

		return null;
	}
}