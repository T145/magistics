package T145.magistics.common.blocks;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import T145.magistics.common.tiles.TileTintedNitor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTintedNitor extends BlockContainer {
	public static IIcon blankIcon;

	public BlockTintedNitor() {
		super(Config.airyMaterial);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTintedNitor();
	}

	@Override
	public int getLightValue(IBlockAccess world, int i, int j, int k) {
		return 15;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blankIcon = r.registerIcon("thaumcraft:blank");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blankIcon;
	}

	@Override
	public int getRenderType() {
		return -1;
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
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public Item getItem(World world, int x, int y, int z) {
		return ConfigItems.itemResource;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int i, int j, int k, Random r) {
		TileEntity tile = w.getTileEntity(i, j, k);

		if (tile instanceof TileTintedNitor) {
			TileTintedNitor nitor = (TileTintedNitor) tile;

			//FXSparkle(world, x, y, z, particleScale, r, g, b, age)
			Color tint = new Color(nitor.getColor(w.getBlockMetadata(i, j, k)));
			//FXSparkle fx = new FXSparkle(w, i + 0.5F, j + 0.5F, k + 0.5F, i + 0.5F + (r.nextFloat() - r.nextFloat()) / 3F, j + 0.5F + (r.nextFloat() - r.nextFloat()) / 3F, k + 0.5F + (r.nextFloat() - r.nextFloat()) / 3F, 1F, 6, 3);
			FXSparkle fx = new FXSparkle(w, i + 0.5F, j + 0.5F, k + 0.5F, 1F, tint.getRed(), tint.getGreen(), tint.getBlue(), 3);
			fx.setGravity(0.05F);
			fx.noClip = true;
			ParticleEngine.instance.addEffect(w, fx);
		}
	}
}