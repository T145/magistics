package T145.magistics.common.config.external.fmp;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.config.ConfigBlocks;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.minecraft.McBlockPart;
import codechicken.multipart.minecraft.McSidedMetaPart;

public class MirrorPart extends McSidedMetaPart {
	public static int[] metaSideMap = { -1, 4, 5, 2, 3, 0 }, sideMetaMap = { 5, 0, 3, 4, 1, 2 };

	public MirrorPart() {
		super();
	}

	public MirrorPart(int meta) {
		super(meta);
	}

	@Override
	public Block getBlock() {
		return ConfigBlocks.blockMirror;
	}

	@Override
	public String getType() {
		return "tc_mirror";
	}

	@Override
	public Cuboid6 getBounds() {
		return getBounds(meta);
	}

	public Cuboid6 getBounds(final int meta) {
		final float w = BlockRenderer.W1;
		switch (meta % 6) {
		case 1:
			return new Cuboid6(0.0F, 0.0F, 0.0F, 1.0F, w, 1.0F);
		case 2:
			return new Cuboid6(0.0F, 0.0F, 1.0F - w, 1.0F, 1.0F, 1.0F);
		case 3:
			return new Cuboid6(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, w);
		case 4:
			return new Cuboid6(1.0F - w, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		case 5:
			return new Cuboid6(0.0F, 0.0F, 0.0F, w, 1.0F, 1.0F);
		default:
			return new Cuboid6(0.0F, 1.0F - w, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public int sideForMeta(int meta) {
		return metaSideMap[meta];
	}

	@Override
	public boolean canStay() {
		return sideForMeta(meta) == 0 && world().getBlock(x(), y() - 1, z()).canPlaceTorchOnTop(world(), x(), y() - 1, z()) || super.canStay();
	}

	public static McBlockPart placement(World w, BlockCoord pos, int side) {
		if (side == 0)
			return null;

		pos = pos.copy().offset(side ^ 1);
		Block block = w.getBlock(pos.x, pos.y, pos.z);

		if (!block.isBlockSolid(w, pos.x, pos.y, pos.z, side) && (side != 1 || block.canPlaceTorchOnTop(w, pos.x, pos.y, pos.z)))
			return null;

		return new MirrorPart(sideMetaMap[side ^ 1]);
	}
}