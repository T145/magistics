package T145.magistics.network;

import T145.magistics.Magistics;
import T145.magistics.network.messages.MessageBase;
import T145.magistics.network.messages.client.MessageInfuserProgress;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Magistics.MODID);

	private static byte id = 0;

	public static void registerMessages() {
		registerMessage(MessageInfuserProgress.class, Side.CLIENT);
	}

	public static void registerMessage(Class<? extends MessageBase> message, Side side) {
		INSTANCE.registerMessage((m, ctx) -> {
			IThreadListener thread = FMLCommonHandler.instance().getWorldThread(ctx.netHandler);
			thread.addScheduledTask(() -> m.process(ctx));
			return null;
		}, message, id++, side);
	}
}