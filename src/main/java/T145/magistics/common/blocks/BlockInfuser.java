package T145.magistics.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.common.tiles.TileInfuser;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfuser extends BlockContainer {
	public static enum Types {
		infuser, dark_infuser
	}

	private enum SideTypes {
		bottom, side, side_connected, top, top_clear
	}

	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[ForgeDirection.values().length * 2];

	public BlockInfuser() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileInfuser();
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		for (int i = 0; i < icon.length / 2; i++)
			icon[i] = r.registerIcon("magistics:infuser_" + SideTypes.values()[i].name());
		for (int j = icon.length / 2; j < icon.length; j++)
			icon[j] = r.registerIcon("magistics:dark_infuser_" + SideTypes.values()[j].name());
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
		int meta = world.getBlockMetadata(i, j, k);

		switch (meta) {
		case 0:
			switch (ForgeDirection.getOrientation(side)) {
			case UP:
				return icon[0];
			case DOWN:
				return icon[3];
			default:
				TileEntity tile = world.getTileEntity(i, j, k);
				if (tile instanceof TileInfuser) {
					TileInfuser infuser = (TileInfuser) tile;

					if (infuser.isSideConnected(side))
						return icon[2];
					else
						return icon[1];
				}
			}
		default:
			return blockIcon;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase user, ItemStack is) {
		if (world.getTileEntity(i, j, k) instanceof TileInfuser) {
			TileInfuser infuser = (TileInfuser) world.getTileEntity(i, j, k);
			infuser.setOrientation(ForgeDirection.values()[MathHelper.floor_double(user.rotationYaw * 4F / 360F + 0.5D) & 3]);
		}
	}
}