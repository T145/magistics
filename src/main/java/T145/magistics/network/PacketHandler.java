package T145.magistics.network;

import T145.magistics.Magistics;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Magistics.MODID);

	private static byte id = 0;

	public static void registerMessages() {
		registerMessage(MessageInfuserProgress.class, Side.CLIENT);
	}

	public static void registerMessage(Class<? extends MessageBase> message, Side side) {
		INSTANCE.registerMessage(getHandler(message, side), message, id++, side);
	}

	private static IMessageHandler<MessageBase, IMessage> getHandler(Class<? extends MessageBase> messageType, Side side) {
		switch (side) {
		case CLIENT:
			return new ClientMessageHandler();
		case SERVER:
			return new CommonMessageHandler();
		default:
			return null;
		}
	}

	private static class CommonMessageHandler implements IMessageHandler<MessageBase, IMessage> {

		@Override
		public IMessage onMessage(MessageBase message, MessageContext context) {
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

			try {
				server.callFromMainThread(() -> message.process(context));
			} catch (Exception err) {
				Magistics.LOGGER.catching(err);
			}

			return null;
		}
	}

	private static class ClientMessageHandler implements IMessageHandler<MessageBase, IMessage> {

		@Override
		public IMessage onMessage(MessageBase message, MessageContext context) {
			Minecraft mc = FMLClientHandler.instance().getClient();

			try {
				mc.addScheduledTask(() -> message.process(context));
			} catch (Exception err) {
				Magistics.LOGGER.catching(err);
			}

			return null;
		}
	}
}