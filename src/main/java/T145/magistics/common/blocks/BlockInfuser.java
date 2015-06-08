package T145.magistics.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.tiles.TileInfuser;
import T145.magistics.common.tiles.TileInfuserDark;

public class BlockInfuser extends BlockContainer {
	public BlockInfuser() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TileInfuser();
		case 1:
			return new TileInfuserDark();
		default:
			return null;
		}
	}
}