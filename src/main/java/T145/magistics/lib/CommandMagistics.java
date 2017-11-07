package T145.magistics.lib;

import T145.magistics.Magistics;
import T145.magistics.world.aura.AuraChunk;
import T145.magistics.world.aura.AuraManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class CommandMagistics extends CommandBase {

	@Override
	public String getName() {
		return Magistics.MODID;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/magistics <action>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {		
		if (args.length > 0) {
			String command = args[0];

			if (command.equalsIgnoreCase("help")) {
				// TODO: Implement
			} else if (command.equalsIgnoreCase("aura")) {
				BlockPos pos = sender.getPosition();
				World world = sender.getEntityWorld();
				Chunk chunk = world.getChunkFromBlockCoords(pos);
				AuraChunk aura = AuraManager.getAuraChunk(world.provider.getDimension(), chunk.getPos());

				if (aura == null) {
					sender.sendMessage(new TextComponentString(TextFormatting.RED + "Aura is null!"));
				} else {
					sender.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "Vis: " + aura.getVis()));
					sender.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "Miasma: " + aura.getFlux()));
				}
			}
		} else {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid arguments; please use /magistics help"));
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
}