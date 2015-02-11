package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityTankPillar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarTank extends BlockPillarBase
{
	public BlockPillarTank(Material mat)
	{
		super(mat);
	}

	@Override
	public int getRenderType()
	{
		return ConfigObjects.tankPillarRenderID;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, entity), 0);
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity)
	{
		return MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)
	{
		ItemStack current = entityplayer.inventory.getCurrentItem();
		TileEntityTankPillar tank = (TileEntityTankPillar) world.getTileEntity(i, j, k);

		if ((current == null || FluidContainerRegistry.getFluidForFilledItem(current) == null) && !entityplayer.isSneaking())
		{
			tank.showNum = !tank.showNum;
			tank.onInventoryChanged();
		} else if (hitY < 1.0F)
		{
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);

			// Handle filled containers
			if (fluid != null)
			{
				if((tank.tank.getFluid() == null || tank.isEmpty || fluid.isFluidEqual(tank.tank.getFluid())))
				{
					int qty = tank.fill(ForgeDirection.UNKNOWN, fluid, true);
	
					if (qty != 0 && !entityplayer.capabilities.isCreativeMode)
					{
						entityplayer.getCurrentEquippedItem().stackSize--;
	
						if (current.getItem().getContainerItem(current) != null)
						{
							entityplayer.inventory.addItemStackToInventory(current.getItem().getContainerItem(current));
						}
	
					}
					return true;
				}
				// Handle empty containers
			} else
			{

				FluidStack available = tank.tank.getFluid();
				if (available != null)
				{
					ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);

					fluid = FluidContainerRegistry.getFluidForFilledItem(filled);

					if (fluid != null)
					{
						if (!entityplayer.capabilities.isCreativeMode)
						{
							if (current.stackSize > 1)
							{
								if (!entityplayer.inventory.addItemStackToInventory(filled))
									return false;
								else
								{
									entityplayer.getCurrentEquippedItem().stackSize--;
								}
							} else
							{
								entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, filled);
							}
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
	public TileEntity createTileEntity(World world, int meta)
	{
		TileEntityTankPillar tile = new TileEntityTankPillar();
		return tile;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister itemIcon)
	{
		this.blockIcon = itemIcon.registerIcon("craftingpillars:craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
	

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile instanceof TileEntityTankPillar) {
			TileEntityTankPillar tank = (TileEntityTankPillar) tile;
			return tank.getFluidLightLevel();
		}

		return super.getLightValue(world, x, y, z);
	}
}