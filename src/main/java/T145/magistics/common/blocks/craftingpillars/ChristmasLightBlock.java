package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityLight;

public class ChristmasLightBlock extends BaseBlockContainer
{
	public ChristmasLightBlock(Material mat)
	{
		super(mat);
		this.setLightLevel(1F);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		if(player.getCurrentEquippedItem() == null)
			((TileEntityLight)world.getTileEntity(x, y, z)).incrColorIndex(-1);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(player.getCurrentEquippedItem() == null)
			((TileEntityLight)world.getTileEntity(x, y, z)).incrColorIndex(1);
		return player.getCurrentEquippedItem() == null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		switch(world.getBlockMetadata(x, y, z))
		{
		case 0:
			this.setBlockBounds(4F/16F, 6F/16F, 4F/16F, 12F/16F, 16F/16F, 12F/16F);
			break;
		case 1:
			this.setBlockBounds(4F/16F, 0F/16F, 4F/16F, 12F/16F, 10F/16F, 12F/16F);
			break;
		case 2:
			this.setBlockBounds(4F/16F, 4F/16F, 6F/16F, 12F/16F, 12F/16F, 16F/16F);
			break;
		case 3:
			this.setBlockBounds(4F/16F, 4F/16F, 0F/16F, 12F/16F, 12F/16F, 10F/16F);
			break;
		case 4:
			this.setBlockBounds(6F/16F, 4F/16F, 4F/16F, 16F/16F, 12F/16F, 12F/16F);
			break;
		case 5:
			this.setBlockBounds(0F/16F, 4F/16F, 4F/16F, 10F/16F, 12F/16F, 12F/16F);
			break;
		}
	}

	@Override
	public int getRenderType()
	{
		return ConfigObjects.lightRenderID;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		return side;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityLight();
	}
}
