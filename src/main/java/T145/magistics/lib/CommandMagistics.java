package T145.magistics.lib;

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

public class CommandMagistics extends CommandBase {

	@Override
	public String getCommandName() {
		return "magistics";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/magistics <action>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {		
		if (args.length > 0) {
			String command = args[0];

			if (command.equalsIgnoreCase("help")) {
			} else if (command.equalsIgnoreCase("aura")) {
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
		} else {
			sender.addChatMessage(new TextComponentString(TextFormatting.RED + "Invalid arguments; please use /magistics help"));
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
}