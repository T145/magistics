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
	public Block getBlock() {
		return ConfigBlocks.blockCandle;
	}

	@Override
	public String getType() {
		return "tc_candle";
	}

	@Override
	public Cuboid6 getBounds() {
		return new Cuboid6(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
	}

	@Override
	public void randomDisplayTick(Random r) {
		getBlock().randomDisplayTick(world(), x(), y(), z(), r);
	}

	@Override
	public Iterable<ItemStack> getDrops() {
		return Arrays.asList(new ItemStack(getBlock(), 1, meta));
	}
}