package T145.magistics.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChristmasLeaves extends Block implements IShearable {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon glowing;
	Block[] adjacentTreeBlocks;

	public BlockChristmasLeaves(Material mat) {
		super(mat);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public int onBlockPlaced(World world, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
		return par9;
	}

	@Override
	public void breakBlock(World world, int par2, int par3, int par4, Block par5, int par6) {
		byte b0 = 1;
		int j1 = b0 + 1;

		if (world.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
			for (int k1 = -b0; k1 <= b0; ++k1)
				for (int l1 = -b0; l1 <= b0; ++l1)
					for (int i2 = -b0; i2 <= b0; ++i2) {
						Block j2 = world.getBlock(par2 + k1, par3 + l1, par4 + i2);

						if (j2 != null)
							j2.beginLeavesDecay(world, par2 + k1, par3 + l1, par4 + i2);
					}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int par2, int par3, int par4, Random par5Random) {
		if (world.canLightningStrikeAt(par2, par3 + 1, par4) && !World.doesBlockHaveSolidTopSurface(world, par2, par3 - 1, par4) && par5Random.nextInt(15) == 1) {
			double d0 = par2 + par5Random.nextFloat();
			double d1 = par3 - 0.05D;
			double d2 = par4 + par5Random.nextFloat();
			world.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	private void removeLeaves(World world, int par2, int par3, int par4) {
		dropBlockAsItem(world, par2, par3, par4, world.getBlockMetadata(par2, par3, par4), 0);
		world.setBlockToAir(par2, par3, par4);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return par1Random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(ConfigObjects.blockChristmasTreeSapling);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float par6, int bonus) {
		if (!world.isRemote) {
			int chance = 13;

			if (bonus > 0) {
				chance -= 2 << bonus;

				if (chance < 8)
					chance = 8;
			}

			if (world.rand.nextInt(chance) == 0)
				if (world.rand.nextInt(2) == 0) {
					Item item = getItemDropped(meta, world.rand, bonus);
					dropBlockAsItem(world, x, y, z, new ItemStack(item));
				} else
					dropBlockAsItem(world, x, y, z, new ItemStack(ConfigObjects.blockChristmasLight, 1, 0));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta & 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("magistics:christmas_leaves");
		BlockChristmasLeaves.glowing = r.registerIcon("magistics:christmas_leaves_overlay");
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
		return ret;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return FMLCommonHandler.instance().getSide().isServer() || !Minecraft.isFancyGraphicsEnabled();
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return Minecraft.isFancyGraphicsEnabled() && world.getBlock(x, y, side) == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}
}