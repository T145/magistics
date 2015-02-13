package me.dawars.mythril.blocks;

import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;
import net.minecraft.block.*;

public abstract class BaseBlockContainer extends BaseBlock implements ITileEntityProvider
{
    public BaseBlockContainer(final Material mat) {
        super(mat);
    }
    
    public TileEntity createNewTileEntity(final World w, final int i) {
        return null;
    }
    
    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int par6) {
        super.breakBlock(world, x, y, z, block, par6);
        world.removeTileEntity(x, y, z);
    }
    
    public boolean onBlockEventReceived(final World world, final int x, final int y, final int z, final int blockID, final int eventID) {
        super.onBlockEventReceived(world, x, y, z, blockID, eventID);
        final TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(blockID, eventID);
    }
}
