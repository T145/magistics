package T145.magistics.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.tiles.TileEverfullUrn;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEverfullUrn extends BlockContainer {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[3];

	public BlockEverfullUrn() {
		super(Material.rock);
		setBlockName("everfull_urn");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:urn_everfull_bottom");
		icon[1] = r.registerIcon("magistics:urn_everfull_top");
		icon[2] = r.registerIcon("magistics:urn_everfull_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side > 1) {
			return icon[2];
		} else {
			return icon[side];
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBounds(0.3125F, 0.5625F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.5625F, 0.875F);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.5625F, 0.875F);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		AxisAlignedBB.getBoundingBox(0.3125D, 0.5625D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
		AxisAlignedBB.getBoundingBox(0.125D, 0.0D, 0.125D, 0.875D, 0.5625D, 0.875D);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEverfullUrn();
	}
}