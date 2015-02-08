package T145.magistics.common.blocks.aesthetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockAesthetic extends Block {
	public BlockAesthetic(Material material) {
		super(material);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
		return true;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}