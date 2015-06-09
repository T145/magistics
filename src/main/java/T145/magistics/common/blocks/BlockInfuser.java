package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.tiles.TileInfuser;
import T145.magistics.common.tiles.TileInfuserDark;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfuser extends BlockContainer {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon infuser_icon[] = new IIcon[5], dark_infuser_icon[] = new IIcon[5];

	public BlockInfuser() {
		super(Material.rock);
		setBlockName("infuser");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		infuser_icon[0] = r.registerIcon("magistics:infuser_bottom");
		infuser_icon[1] = r.registerIcon("magistics:infuser_side_connected");
		infuser_icon[2] = r.registerIcon("magistics:infuser_side");
		infuser_icon[3] = r.registerIcon("magistics:infuser_top_clear");
		infuser_icon[4] = r.registerIcon("magistics:infuser_top");

		dark_infuser_icon[0] = r.registerIcon("magistics:dark_infuser_bottom");
		dark_infuser_icon[1] = r.registerIcon("magistics:dark_infuser_side_connected");
		dark_infuser_icon[2] = r.registerIcon("magistics:dark_infuser_side");
		dark_infuser_icon[3] = r.registerIcon("magistics:dark_infuser_top_clear");
		dark_infuser_icon[4] = r.registerIcon("magistics:dark_infuser_top");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);

		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
			switch (side) {
			case 0:
				return infuser_icon[0];
			case 1:
				return infuser_icon[3];
			default:
				return ((TileInfuser) tile).isSideConnected(side) ? infuser_icon[1] : infuser_icon[2];
			}
		case 1:
			switch (side) {
			case 0:
				return dark_infuser_icon[0];
			case 1:
				return dark_infuser_icon[3];
			default:
				return ((TileInfuserDark) tile).isSideConnected(side) ? dark_infuser_icon[1] : dark_infuser_icon[2];
			}
		default:
			return blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 2; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBounds(0F, 0F, 0F, 1F, 1F - 0.0625F, 1F);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBounds(0F, 0F, 0F, 1F, 1F - 0.0625F, 1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		AxisAlignedBB.getBoundingBox(0D, 0D, 0D, 1D, (double) (1F - 0.0625F), 1D);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TileInfuser();
		case 1:
			return new TileInfuserDark();
		default:
			return null;
		}
	}

	public static boolean isDark(int meta) {
		return meta > 0 ? true : false;
	}
}