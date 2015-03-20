package T145.magistics.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.common.config.Config;
import T145.magistics.common.Magistics;
import T145.magistics.common.tiles.TileTintedNitor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTintedNitor extends BlockContainer {
	public BlockTintedNitor() {
		super(Config.airyMaterial);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTintedNitor(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		blockIcon = ir.registerIcon("thaumcraft:blank");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return 15;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess ba, int x, int y, int z) {
		setBlockBounds(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
		return null;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return false;
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
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return new ItemStack(Magistics.proxy.itemTintedNitor, 1, meta).getItem();
	}

	@Override
	public Item getItem(World world, int x, int y, int z) {
		return new ItemStack(Magistics.proxy.itemTintedNitor, 1, world.getBlockMetadata(x, y, z)).getItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int i, int j, int k, Random r) {
		FXSparkle ef2 = new FXSparkle(w, i + 0.5f, j + 0.5f, k + 0.5f, i + 0.5f + (r.nextFloat() - r.nextFloat()) / 3.0f, j + 0.5f + (r.nextFloat() - r.nextFloat()) / 3.0f, k + 0.5f + (r.nextFloat() - r.nextFloat()) / 3.0f, 1.0f, 6, 3);
		ef2.setGravity(0.05f);
		ef2.noClip = true;
		ParticleEngine.instance.addEffect(w, ef2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		tab = null;
	}
}