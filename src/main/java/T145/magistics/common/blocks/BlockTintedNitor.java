package T145.magistics.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;
import T145.magistics.common.tiles.TileTintedNitor;

public class BlockTintedNitor extends BlockContainer {
	public BlockTintedNitor() {
		super(Config.airyMaterial);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTintedNitor();
	}

	@Override
	public int getLightValue(IBlockAccess world, int i, int j, int k) {
		return 15;
	}
}