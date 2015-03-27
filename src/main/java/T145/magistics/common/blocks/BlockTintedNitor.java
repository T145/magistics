package T145.magistics.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import T145.magistics.common.Magistics;
import T145.magistics.common.tiles.TileTintedNitor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTintedNitor extends BlockContainer {
	private Block nitor = Block.getBlockFromItem(new ItemStack(ConfigBlocks.blockAiry, 1, 1).getItem());

	public BlockTintedNitor() {
		super(Config.airyMaterial);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTintedNitor();
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
		nitor.randomDisplayTick(w, i, j, k, r);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		tab = null;
	}
}