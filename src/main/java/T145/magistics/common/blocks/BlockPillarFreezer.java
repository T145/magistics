package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import T145.magistics.api.FreezerRecipes;
import T145.magistics.common.tiles.pillars.TilePillarFreezer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarFreezer extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarFreezer(Material mat) {
		super(mat);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, entity), 0);
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity) {
		return MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
		ItemStack current = entityplayer.inventory.getCurrentItem();
		TilePillarFreezer tank = (TilePillarFreezer) world.getTileEntity(i, j, k);

		if ((current == null || FluidContainerRegistry.getFluidForFilledItem(current) == null) && !entityplayer.isSneaking()) {
			tank.showNum = !tank.showNum;
			tank.onInventoryChanged();
		} else if (hitY < 1.0F) {
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);

			if (fluid != null) {
				if ((tank.tank.getFluid() == null || tank.empty || fluid.isFluidEqual(tank.tank.getFluid())) && FreezerRecipes.getResultForFluid(fluid) != null) {
					int qty = tank.fill(ForgeDirection.UNKNOWN, fluid, true);

					if (qty != 0 && !entityplayer.capabilities.isCreativeMode) {
						entityplayer.getCurrentEquippedItem().stackSize--;

						if (current.getItem().getContainerItem(current) != null)
							entityplayer.inventory.addItemStackToInventory(current.getItem().getContainerItem(current));
					}
					return true;
				}
			} else {
				FluidStack available = tank.tank.getFluid();
				if (available != null) {
					ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);
					fluid = FluidContainerRegistry.getFluidForFilledItem(filled);

					if (fluid != null) {
						if (!entityplayer.capabilities.isCreativeMode) {
							if (current.stackSize > 1) {
								if (!entityplayer.inventory.addItemStackToInventory(filled))
									return false;
								else
									entityplayer.getCurrentEquippedItem().stackSize--;
							} else
								entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, filled);
						}
						tank.drain(ForgeDirection.UNKNOWN, fluid.amount, true);
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote) {
			TilePillarFreezer pillarTile = (TilePillarFreezer) world.getTileEntity(x, y, z);

			if (pillarTile.getStackInSlot(0) != null)
				if (player.isSneaking())
					pillarTile.dropItemFromSlot(0, pillarTile.getStackInSlot(0).stackSize, player);
				else {
					ItemStack itemStack = pillarTile.getStackInSlot(0).copy();
					itemStack.stackSize = 1;
					pillarTile.dropItemFromSlot(0, 1, player);
				}
		} else
			for (int i = 0; i < 7; ++i) {
				double d0 = world.rand.nextGaussian() * 0.02D;
				double d1 = world.rand.nextGaussian() * 0.02D;
				double d2 = world.rand.nextGaussian() * 0.02D;
				world.spawnParticle("heart", x + (double) (world.rand.nextFloat()), y + 1, z + (double) (world.rand.nextFloat()), d0, d1, d2);
			}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		if (!world.isRemote) {
			TilePillarFreezer pillarTile = (TilePillarFreezer) world.getTileEntity(x, y, z);

			if (pillarTile.getStackInSlot(0) != null) {
				EntityItem itemDropped = new EntityItem(world, x + 0.5D, y, z + 0.5D, pillarTile.getStackInSlot(0));
				itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

				if (pillarTile.getStackInSlot(0).hasTagCompound())
					itemDropped.getEntityItem().setTagCompound((NBTTagCompound) pillarTile.getStackInSlot(0).getTagCompound().copy());

				world.spawnEntityInWorld(itemDropped);
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TilePillarFreezer();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("craftingpillars:craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile instanceof TilePillarFreezer) {
			TilePillarFreezer tank = (TilePillarFreezer) tile;
			return tank.getFluidLightLevel();
		}

		return super.getLightValue(world, x, y, z);
	}
}