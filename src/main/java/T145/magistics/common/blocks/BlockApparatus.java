package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.lib.InventoryHelper;
import thaumcraft.common.tiles.TileCrucible;

public class BlockApparatus extends BlockContainer {
	public BlockApparatus(Material m) {
		super(m);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, Block b, int l) {
		InventoryHelper.dropItems(world, i, j, k);
		TileEntity te = world.getTileEntity(i, j, k);
		if (te != null && te instanceof TileCrucible)
			((TileCrucible) te).spillRemnants();
		super.breakBlock(world, i, j, k, b, l);
	}
}