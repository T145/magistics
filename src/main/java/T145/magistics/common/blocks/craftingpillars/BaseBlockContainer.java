package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BaseBlockContainer extends BaseBlock implements ITileEntityProvider
{
	public BaseBlockContainer(Material mat)
	{
		super(mat);
	}

	/**
	 * Overridden by {@link #createTileEntity(World, int)}
	 */
	@Override
	public TileEntity createNewTileEntity(World w, int i)
	{
		return null;
	}

	@Override
	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	public void breakBlock(World world, int x, int y, int z, Block block, int par6)
	{
		super.breakBlock(world, x, y, z, block, par6);
		world.removeTileEntity(x, y, z);
	}

	@Override
	/**
	 * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
	 * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
	 */
	public boolean onBlockEventReceived(World world, int x, int y, int z, int blockID, int eventID)
	{
		super.onBlockEventReceived(world, x, y, z, blockID, eventID);
		TileEntity tileentity = world.getTileEntity(x, y, z);
		return tileentity != null ? tileentity.receiveClientEvent(blockID, eventID) : false;
	}
}
