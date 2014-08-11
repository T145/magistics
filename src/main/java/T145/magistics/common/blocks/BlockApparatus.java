package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.lib.InventoryHelper;
import thaumcraft.common.tiles.TileCrucible;

public class BlockApparatus extends BlockContainer {
	public BlockApparatus(Material material) {
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		world.markBlockForUpdate(i, j, k);
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