package T145.magistics.common.config.external.fmp;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import thaumcraft.common.config.ConfigBlocks;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.IRandomDisplayTick;
import codechicken.multipart.minecraft.McMetaPart;

public class CandlePart extends McMetaPart implements IRandomDisplayTick {
	public CandlePart(int meta) {
		super(meta);
	}

	public CandlePart() {
		super();
	}

	@Override
	public Cuboid6 getBounds() {
		return new Cuboid6(0.375, 0, 0.375, 0.625, 0.5, 0.625);
	}

	@Override
	public void randomDisplayTick(Random rand) {
		getBlock().randomDisplayTick(world(), x(), y(), z(), rand);
	}

	@Override
	public Block getBlock() {
		return ConfigBlocks.blockCandle;
	}

	@Override
	public String getType() {
		return "tc_candle";
	}

	@Override
	public Iterable<ItemStack> getDrops() {
		return Arrays.asList(new ItemStack(getBlock(), 1, meta));
	}
}