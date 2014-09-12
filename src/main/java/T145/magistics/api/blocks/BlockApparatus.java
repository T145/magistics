package T145.magistics.api.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
}