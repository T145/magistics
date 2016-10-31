package T145.magistics.commands;

import T145.magistics.lib.aura.AuraChunk;
import T145.magistics.lib.aura.AuraHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.chunk.Chunk;

public class CommandAura extends CommandBase {

	@Override
	public String getCommandName() {
		return "aura";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "magistics.commands.aura.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		BlockPos pos = sender.getPosition();
		Chunk chunk = sender.getEntityWorld().getChunkFromBlockCoords(pos);
		AuraChunk aura = AuraHandler.getAuraChunk(chunk);

		if (aura == null) {
			sender.addChatMessage(new TextComponentString(TextFormatting.RED + "Aura is null!"));
		} else {
			sender.addChatMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "Vis: " + aura.getVis()));
			sender.addChatMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "Miasma: " + aura.getMiasma()));
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
}