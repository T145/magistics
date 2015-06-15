package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.blocks.CustomStepSound;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModConfig;
import T145.magistics.common.tiles.TileThinkTank;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockThinkTank extends BlockContainer {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	private final Random rand = new Random();
	private final boolean isActive;
	private static boolean keepInventory;

	public BlockThinkTank(boolean active) {
		super(Material.glass);
		isActive = active;
		setStepSound(new CustomStepSound("jar", 1F, 1F));
		setHardness(0.3F);
		setLightLevel(0.66F);
		setBlockTextureName("thaumcraft:jar_bottom");
	}

	@Override
	public Item getItemDropped(int par1, Random xRandom, int y) {
		return Item.getItemFromBlock(ModConfig.blockThinkTank);
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
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (world.isRemote) {
			return true;
		} else {
			TileThinkTank tank = (TileThinkTank) world.getTileEntity(x, y, z);

			if (tank != null) {
				par5EntityPlayer.openGui(Magistics.instance, 0, world, x, y, z);
			}

			return true;
		}
	}

	public static void updateState(boolean par0, World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getTileEntity(x, y, z);
		keepInventory = true;

		keepInventory = false;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setTileEntity(x, y, z, tileentity);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack stack) {
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4F / 360F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		if (stack.hasDisplayName()) {
			((TileThinkTank) world.getTileEntity(x, y, z)).setGuiDisplayName(stack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		if (!keepInventory) {
			TileThinkTank tank = (TileThinkTank) world.getTileEntity(x, y, z);

			if (tank != null) {
				for (int j1 = 0; j1 < tank.getSizeInventory(); ++j1) {
					ItemStack itemstack = tank.getStackInSlot(j1);

					if (itemstack != null) {
						float f = rand.nextFloat() * 0.8F + 0.1F;
						float f1 = rand.nextFloat() * 0.8F + 0.1F;
						float f2 = rand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0) {
							int k1 = rand.nextInt(21) + 10;

							if (k1 > itemstack.stackSize) {
								k1 = itemstack.stackSize;
							}

							itemstack.stackSize -= k1;
							EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
							}

							float f3 = 0.05F;
							entityitem.motionX = (double) ((float) rand.nextGaussian() * f3);
							entityitem.motionY = (double) ((float) rand.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double) ((float) rand.nextGaussian() * f3);
							world.spawnEntityInWorld(entityitem);
						}
					}
				}

				world.func_147453_f(x, y, z, block);
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int signal) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileThinkTank();
	}
}