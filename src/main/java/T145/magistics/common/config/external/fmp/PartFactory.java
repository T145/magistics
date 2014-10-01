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
import cpw.mods.fml.common.Loader;

public class PartFactory implements IPartFactory, IPartConverter {
	@Override
	public TMultiPart createPart(String name, boolean client) {
		if (name.equals("tc_candle") && !Loader.isModLoaded("ThaumicTinkerer"))
			return new CandlePart();

		return null;
	}

	public void init() {
		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, new String[] { "tc_candle" });
	}

	@Override
	public Iterable<Block> blockTypes() {
		return Arrays.asList(ConfigBlocks.blockCandle);
	}

	@Override
	public TMultiPart convert(World world, BlockCoord pos) {
		Block b = world.getBlock(pos.x, pos.y, pos.z);
		int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
		if (b == ConfigBlocks.blockCandle && !Loader.isModLoaded("ThaumicTinkerer"))
			return new CandlePart(meta);

		return null;
	}
}