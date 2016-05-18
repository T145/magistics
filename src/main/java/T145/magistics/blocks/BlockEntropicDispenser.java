package T145.magistics.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.tiles.TileEntropicDispenser;

public class BlockEntropicDispenser extends BlockContainer {
	public BlockEntropicDispenser() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntropicDispenser();
	}
}