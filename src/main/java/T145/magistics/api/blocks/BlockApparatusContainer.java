package T145.magistics.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.lib.InventoryHelper;
import thaumcraft.common.tiles.TileCrucible;

public class BlockApparatusContainer extends BlockApparatus {
	public BlockApparatusContainer(Material material) {
		super(material);
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
		InventoryHelper.dropItems(world, i, j, k);
		TileEntity tile = world.getTileEntity(i, j, k);
		if (hasTileEntity(meta) && tile instanceof TileCrucible)
			((TileCrucible) tile).spillRemnants();
		super.breakBlock(world, i, j, k, block, meta);
	}
}