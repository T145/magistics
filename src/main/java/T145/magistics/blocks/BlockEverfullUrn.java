package T145.magistics.blocks;

import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.tiles.TileEverfullUrn;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockEverfullUrn extends BlockContainer {
	public static final Block INSTANCE = new BlockEverfullUrn();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public static IIcon[] icon = new IIcon[3];

	public BlockEverfullUrn() {
		super(Material.rock);

		setBlockName("everfull_urn");
		setCreativeTab(Magistics.tabMagistics);

		setHardness(2F);
		setResistance(15F);
		setStepSound(soundTypeStone);
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

	@SideOnly(Side.CLIENT)
	public void spawnWaterAtLocation(World world, double x, double y, double z, double dX, double dY, double dZ) {
		FXEssentiaTrail fx = new FXEssentiaTrail(world, x + 0.5F, y + 1.1F, z + 0.5F, dX + 0.5F, dY + 1.1F, dZ + 0.5F, 5, Aspect.TOOL.getColor(), 1.0F);
		ParticleEngine.instance.addEffect(world, fx);
	}

	@SideOnly(Side.CLIENT)
	public void spawnRandomWaterFountain(World world, Random rand, int x, int y, int z) {
		spawnWaterAtLocation(world, x, y, z, x + (Math.random() - 0.5), y + 1F, z + (Math.random() - 0.5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		spawnRandomWaterFountain(world, rand, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEverfullUrn();
	}
}