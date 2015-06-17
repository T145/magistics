package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSparkle;
import T145.magistics.common.tiles.TileCrystalizer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalizer extends BlockContainer {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[3], inside;

	public BlockCrystalizer() {
		super(Material.iron);
		setBlockName("crystalizer");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:crystalizer_bottom");
		icon[1] = r.registerIcon("magistics:crystalizer_top");
		icon[2] = r.registerIcon("magistics:crystalizer_side");
		inside = r.registerIcon("magistics:crucible_thaumium_top_in");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side > 1 ? icon[2] : icon[side];
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
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return true;
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
}