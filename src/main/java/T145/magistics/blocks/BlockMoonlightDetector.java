package T145.magistics.blocks;

import T145.magistics.tiles.TileMoonlightDetector;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMoonlightDetector extends BlockContainer {
	public BlockMoonlightDetector() {
		super(Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMoonlightDetector();
	}
}