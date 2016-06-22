package T145.magistics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.common.blocks.JarStepSound;
import T145.magistics.Magistics;
import T145.magistics.tiles.TileCrystalCore;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalCore extends BlockContainer {
	public static final Block INSTANCE = new BlockCrystalCore();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockCrystalCore() {
		super(Material.glass);
		setCreativeTab(Magistics.tabMagistics);
		setBlockName("crystal_core");

		setHardness(0.7F);
		setResistance(1F);
		setLightLevel(0.4F);
		setStepSound(new JarStepSound("crystal", 1F, 1F));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("thaumcraft:crystal");
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
		float mod = 0.2F;
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof TileCrystalCore) {
			mod += ((TileCrystalCore) tile).speed;
		}

		FXSparkle fx = new FXSparkle(world, x + 0.2F + world.rand.nextFloat() * 0.6F, y + mod + world.rand.nextFloat() * 0.6F, z + 0.2F + world.rand.nextFloat() * 0.6F, 1F, rand.nextInt(5), 3);
		fx.noClip = true;
		ParticleEngine.instance.addEffect(world, fx);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileCrystalCore();
	}
}