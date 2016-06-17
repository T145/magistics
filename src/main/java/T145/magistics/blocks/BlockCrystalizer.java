package T145.magistics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.Magistics;
import T145.magistics.tiles.TileCrystalizer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalizer extends BlockContainer {
	public static final Block INSTANCE = new BlockCrystalizer();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public IIcon[] icon = new IIcon[3];
	public IIcon inside, top_opaque;

	public BlockCrystalizer() {
		super(Material.iron);
		setBlockName("crystalizer");
		setCreativeTab(Magistics.tabMagistics);

		setHardness(3F);
		setResistance(17F);
		setStepSound(soundTypeMetal);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F + BlockRenderer.W2, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:crystalizer_bottom");
		icon[1] = r.registerIcon("magistics:crystalizer_top");
		icon[2] = r.registerIcon("magistics:crystalizer_side");
		inside = r.registerIcon("magistics:crucible_thaumium_top_in");
		top_opaque = r.registerIcon("magistics:crystalizer_top_opaque");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (side < 0) {
			return side == -1 ? top_opaque : inside;
		} else {
			return side < 2 ? icon[side] : icon[2];
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
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		FXSparkle fx = new FXSparkle(world, (double) ((float) x + 0.1F + world.rand.nextFloat() * 0.8F), (double) ((float) y + 0.6F + world.rand.nextFloat() * 0.6F), (double) ((float) z + 0.1F + world.rand.nextFloat() * 0.8F), 1F, world.rand.nextInt(5), 3);
		ParticleEngine.instance.addEffect(world, fx);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileCrystalizer();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Magistics.instance, 2, world, x, y, z);
		}

		return true;
	}
}