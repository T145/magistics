package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.Magistics;
import T145.magistics.tiles.TileEverfullUrn;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEverfullUrn extends BlockContainer {
	public static final Block INSTANCE = new BlockEverfullUrn();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public IIcon[] icon = new IIcon[3];

	public BlockEverfullUrn() {
		super(Material.rock);
		setCreativeTab(Magistics.tabMagistics);
		setBlockName("everfull_urn");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
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
		return side > 1 ? icon[2] : icon[side];
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsForItemRender();
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBounds(BlockRenderer.W2, 0, BlockRenderer.W2, BlockRenderer.W14, BlockRenderer.W9, BlockRenderer.W14);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		AxisAlignedBB.getBoundingBox(BlockRenderer.W5, BlockRenderer.W9, BlockRenderer.W5, BlockRenderer.W11, 1, BlockRenderer.W11);
		AxisAlignedBB.getBoundingBox(BlockRenderer.W2, 0, BlockRenderer.W2, BlockRenderer.W14, BlockRenderer.W9, BlockRenderer.W14);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEverfullUrn();
	}
}