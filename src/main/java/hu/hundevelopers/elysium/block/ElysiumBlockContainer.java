package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ElysiumBlockContainer extends ElysiumBlock implements ITileEntityProvider
{
    protected ElysiumBlockContainer(Material material)
    {
        super(material);
        this.isBlockContainer = true;
    }
    
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }
    
    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
        super.breakBlock(world, x, y, z, par5, par6);
        world.removeTileEntity(x, y, z);
    }
    
    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int blockID, int eventID)
    {
        super.onBlockEventReceived(world, x, y, z, blockID, eventID);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(blockID, eventID) : false;
    }
    
    @Override
    public boolean canBeReplacedByLake()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return null;
	}
}