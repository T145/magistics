package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.tile.TilePipe;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ElysiumPipeBlock extends ElysiumBlockContainer
{

	public ElysiumPipeBlock(Material mat)
	{
		super(mat);
	}

	@Override
	public int getRenderType()
	{
		return Elysium.pipeStoneReinderingID;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		TilePipe tile = new TilePipe();
		
		tile.init();
		
		return tile;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

}
