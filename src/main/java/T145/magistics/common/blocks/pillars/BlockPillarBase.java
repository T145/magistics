package T145.magistics.common.blocks.pillars;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockPillarBase extends BlockContainer {
	public BlockPillarBase(Material mat) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int blockID, int eventID) {
		super.onBlockEventReceived(world, x, y, z, blockID, eventID);
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null ? tile.receiveClientEvent(blockID, eventID) : false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("craftingpillars:" + getUnlocalizedName().substring(5));
	}
}