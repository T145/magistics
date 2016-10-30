package T145.magistics.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import T145.magistics.Magistics;
import T145.magistics.lib.aura.AuraChunk;
import T145.magistics.lib.aura.AuraHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.chunk.Chunk;

public class CommandAura extends CommandBase {

	private final List<String> aliases;

	public CommandAura() {
		aliases = Lists.newArrayList(Magistics.MODID, "aura");
	}

	@Override
	public String getCommandName() {
		return "auralevel";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "auralevel";
	}

	@Override
	@Nonnull
	public List<String> getCommandAliases() {
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		BlockPos pos = sender.getPosition();
		Chunk chunk = sender.getEntityWorld().getChunkFromBlockCoords(pos);
		ChunkPos chunkPos = chunk.getChunkCoordIntPair();
		AuraChunk aura = AuraHandler.getAuraChunk(chunkPos, sender.getEntityWorld().provider.getDimension());

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

	@Override
	@Nonnull
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
		return Collections.emptyList();
	}
}