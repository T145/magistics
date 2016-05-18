package T145.magistics.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.tiles.TileEverfullUrn;

public class BlockEverfullUrn extends BlockContainer {
	public BlockEverfullUrn() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEverfullUrn();
	}
}