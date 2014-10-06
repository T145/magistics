package T145.magistics.common.config.external.fmp;

import java.util.ArrayList;
import java.util.List;

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
	List<String> partNames = new ArrayList<String>();
	List<Block> blocksToConvert = new ArrayList<Block>();

	@Override
	public TMultiPart createPart(String name, boolean client) {
		for (String entry : partNames) {
			if (entry.equals("tc_candle"))
				return new CandlePart();
		}
		return null;
	}

	public void init() {
		MultiPartRegistry.registerConverter(this);
		if (!Loader.isModLoaded("ThaumicTinkerer")) {
			partNames.add("tc_candle");
			blocksToConvert.add(ConfigBlocks.blockCandle);
		}
		MultiPartRegistry.registerParts(this, partNames.toArray(new String[partNames.size()]));
	}

	@Override
	public Iterable<Block> blockTypes() {
		return blocksToConvert;
	}

	@Override
	public TMultiPart convert(World world, BlockCoord pos) {
		Block block = world.getBlock(pos.x, pos.y, pos.z);
		int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
		if (block == blocksToConvert.get(0))
			return new CandlePart(meta);
		return null;
	}
}