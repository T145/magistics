package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityExtendPillar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExtendPillarBlock extends BaseBlockContainer
{
	public ExtendPillarBlock(Material mat)
	{
		super(mat);
	}

	@Override
	public int getRenderType()
	{
		return ConfigObjects.extendPillarRenderID;
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
		switch(side)
		{
		case 0:
		case 1:
			meta = 0;
			break;
		case 2:
		case 3:
			meta = 1;
			break;
		case 4:
		case 5:
			meta = 2;
			break;
		}

		return meta;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		TileEntityExtendPillar tile = new TileEntityExtendPillar();
		return tile;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister itemIcon)
	{
		this.blockIcon = itemIcon.registerIcon("craftingpillars:craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
}
