package T145.magistics.common.config.external.fmp;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

public class PartFactory implements IPartFactory, IPartConverter {
	public static final String parts[] = {
		"tc_candle", "tc_mirror"
	};

	@Override
	public TMultiPart createPart(String name, boolean client) {
		if (name.equals(parts[0]))
			return new CandlePart();
		else if (name.equals(parts[1]))
			return new MirrorPart();
		return null;
	}

	public void init() {
		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, parts);
	}

	@Override
	public Iterable<Block> blockTypes() {
		return Arrays.asList(ConfigBlocks.blockCandle, ConfigBlocks.blockMirror);
	}

	@Override
	public TMultiPart convert(World w, BlockCoord pos) {
		Block b = w.getBlock(pos.x, pos.y, pos.z);
		int meta = w.getBlockMetadata(pos.x, pos.y, pos.z);
		if (b == ConfigBlocks.blockCandle)
			return new CandlePart(meta);
		else if (b == ConfigBlocks.blockMirror)
			return new MirrorPart(meta);
		return null;
	}
}