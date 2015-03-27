package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.InventoryUtils;
import T145.magistics.common.tiles.TileEnhancedFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnhancedFurnace extends BlockContainer {
	public BlockEnhancedFurnace(Material material) {
		super(material);
	}

	public BlockEnhancedFurnace() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	public static void updateState(boolean active, World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		TileEnhancedFurnace furnace = (TileEnhancedFurnace) world.getTileEntity(x, y, z);

		if (furnace == null)
			return;

		//active = true;

		if (furnace.isActive()) {
			//world.setBlock(x, y, z, Blocks.lit_furnace);
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		} else {
			//world.setBlock(x, y, z, Blocks.furnace);
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}

		//active = false;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (furnace != null) {
			furnace.validate();
			world.setTileEntity(x, y, z, furnace);
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		InventoryUtils.dropItems(par1World, par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int i, int j, int k, Random r) {
		TileEntity te = w.getTileEntity(i, j, k);
		if (te != null && te instanceof TileEnhancedFurnace && ((TileEnhancedFurnace) te).isActive()) {
			float f = i + 0.5f, f2 = j + 0.2f + r.nextFloat() * 5.0f / 16.0f, f3 = k + 0.5f, f4 = 0.52f, f5 = r.nextFloat() * 0.5f - 0.25f;
			w.spawnParticle("smoke", (double) (f - f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
			w.spawnParticle("flame", (double) (f - f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
			w.spawnParticle("smoke", (double) (f + f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
			w.spawnParticle("flame", (double) (f + f4), (double) f2, (double) (f3 + f5), 0.0, 0.0, 0.0);
			w.spawnParticle("smoke", (double) (f + f5), (double) f2, (double) (f3 - f4), 0.0, 0.0, 0.0);
			w.spawnParticle("flame", (double) (f + f5), (double) f2, (double) (f3 - f4), 0.0, 0.0, 0.0);
			w.spawnParticle("smoke", (double) (f + f5), (double) f2, (double) (f3 + f4), 0.0, 0.0, 0.0);
			w.spawnParticle("flame", (double) (f + f5), (double) f2, (double) (f3 + f4), 0.0, 0.0, 0.0);
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TileEnhancedFurnace && ((TileEnhancedFurnace) tile).isActive())
			return Blocks.lit_furnace.getLightValue();
		else
			return 0;
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int p_149736_5_) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}
}