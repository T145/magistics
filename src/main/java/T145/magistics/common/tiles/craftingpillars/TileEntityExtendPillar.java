package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.tileentity.TileEntity;

public class TileEntityExtendPillar extends TileEntity
{
	/**
     * Determines if this TileEntity requires update calls.
     * @return True if you want updateEntity() to be called, false if not
     */
	@Override
    public boolean canUpdate()
    {
        return false;
    }
}