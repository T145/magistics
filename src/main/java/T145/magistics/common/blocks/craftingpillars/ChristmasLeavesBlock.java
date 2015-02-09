package T145.magistics.common.blocks.craftingpillars;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChristmasLeavesBlock extends BaseLeavesBlock implements IShearable
{

	public static IIcon glowing;
	Block[] adjacentTreeBlocks;

	public ChristmasLeavesBlock(Material mat)
	{
		super(mat, true);
	}

	@Override
	public int getRenderType()
	{
		return Magistics.proxy.winter ? ConfigObjects.christmasLeavesRenderID : super.getRenderType();
	}

	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
	 */
	@Override
	public int onBlockPlaced(World world, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		return par9;
	}

	/**
	 * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
	 * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
	 * metadata
	 */
	@Override
	public void breakBlock(World world, int par2, int par3, int par4, Block par5, int par6)
	{
		byte b0 = 1;
		int j1 = b0 + 1;

		if (world.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
		{
			for (int k1 = -b0; k1 <= b0; ++k1)
			{
				for (int l1 = -b0; l1 <= b0; ++l1)
				{
					for (int i2 = -b0; i2 <= b0; ++i2)
					{
						Block j2 = world.getBlock(par2 + k1, par3 + l1, par4 + i2);

						if (j2 != null)
						{
							j2.beginLeavesDecay(world, par2 + k1, par3 + l1, par4 + i2);
						}
					}
				}
			}
		}
	}
	/**
	 * Ticks the block if it's been scheduled
	 */
	      /*
	@Override
	public void updateTick(World world, int par2, int par3, int par4, Random par5Random)
	{
		if (!world.isRemote)
		{
			int l = world.getBlockMetadata(par2, par3, par4);

			if ((l & 8) != 0 && (l & 4) == 0)
			{
				byte b0 = 4;
				int i1 = b0 + 1;
				byte b1 = 32;
				int j1 = b1 * b1;
				int k1 = b1 / 2;

				if (this.adjacentTreeBlocks == null)
				{
					this.adjacentTreeBlocks = new int[b1 * b1 * b1];
				}

				int l1;

				if (world.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1))
				{
					int i2;
					int j2;
					int k2;

					for (l1 = -b0; l1 <= b0; ++l1)
					{
						for (i2 = -b0; i2 <= b0; ++i2)
						{
							for (j2 = -b0; j2 <= b0; ++j2)
							{
								k2 = world.getBlockId(par2 + l1, par3 + i2, par4 + j2);

								Block block = Block.blocksList[k2];

								if (block != null && block.canSustainLeaves(world, par2 + l1, par3 + i2, par4 + j2))
								{
									this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
								}
								else if (block != null && block.isLeaves(world, par2 + l1, par3 + i2, par4 + j2))
								{
									this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
								}
								else
								{
									this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
								}
							}
						}
					}

					for (l1 = 1; l1 <= 4; ++l1)
					{
						for (i2 = -b0; i2 <= b0; ++i2)
						{
							for (j2 = -b0; j2 <= b0; ++j2)
							{
								for (k2 = -b0; k2 <= b0; ++k2)
								{
									if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
									{
										if (this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
										{
											this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										}

										if (this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
										{
											this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										}

										if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
										{
											this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
										}

										if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
										{
											this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
										}

										if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
										{
											this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
										}

										if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
										{
											this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
										}
									}
								}
							}
						}
					}
				}

				l1 = this.adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

				if (l1 >= 0)
				{
					world.setBlockMetadataWithNotify(par2, par3, par4, l & -9, 4);
				}
				else
				{
					this.removeLeaves(world, par2, par3, par4);
				}
			}
		}
	}         */

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World world, int par2, int par3, int par4, Random par5Random)
	{
		if (world.canLightningStrikeAt(par2, par3 + 1, par4) && !World.doesBlockHaveSolidTopSurface(world, par2, par3 - 1, par4) && par5Random.nextInt(15) == 1)
		{
			double d0 = par2 + par5Random.nextFloat();
			double d1 = par3 - 0.05D;
			double d2 = par4 + par5Random.nextFloat();
			world.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	private void removeLeaves(World world, int par2, int par3, int par4)
	{
		this.dropBlockAsItem(world, par2, par3, par4, world.getBlockMetadata(par2, par3, par4), 0);
		world.setBlockToAir(par2, par3, par4);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random)
	{
		return par1Random.nextInt(20) == 0 ? 1 : 0;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(ConfigObjects.blockChristmasTreeSapling);
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float par6, int bonus)
	{
		if (!world.isRemote)
		{
			int chance = 13;

			if (bonus > 0)
			{
				chance -= 2 << bonus;

				if (chance < 8)
				{
					chance = 8;
				}
			}

			if (world.rand.nextInt(chance) == 0)
			{
				if(world.rand.nextInt(2) == 0)
				{
					Item item = this.getItemDropped(meta, world.rand, bonus);
					this.dropBlockAsItem(world, x, y, z, new ItemStack(item));
				} else {
					this.dropBlockAsItem(world, x, y, z, new ItemStack(ConfigObjects.blockChristmasLight, 1, 0));
				} 
			}
		}
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	@Override
	public int damageDropped(int meta)
	{
		return meta & 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister itemIcon)
	{
		this.blockIcon = itemIcon.registerIcon("craftingpillars:ChristmasTreeLeaves");
		ChristmasLeavesBlock.glowing = itemIcon.registerIcon("craftingpillars:ChristmasTreeLeavesOverlay");
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
		return ret;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}
}
