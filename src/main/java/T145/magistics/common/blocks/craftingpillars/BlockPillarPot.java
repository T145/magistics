package T145.magistics.common.blocks.craftingpillars;

import java.util.Random;

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
import net.minecraft.world.World;
import T145.magistics.common.tiles.craftingpillars.TileEntityPotPillar;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarPot extends BlockPillarBase
{
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarPot(Material mat)
	{
		super(mat);
		this.setTickRandomly(true);
	}

	@Override
	public int getRenderType()
	{
		return renderID;
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
	@SideOnly(Side.CLIENT)
	/**
	 * Returns true only if block is flowerPot
	 */
	public boolean isFlowerPot()
	{
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
			return true;

		TileEntityPotPillar pillarTile = (TileEntityPotPillar) world.getTileEntity(x, y, z);

		if(hitY < 1F && !player.isSneaking() /*&& player.getCurrentEquippedItem() == null*/)
		{
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}

		if(hitY == 1F)
		{
			if(player.isSneaking())//pick out
			{
				pillarTile.dropItemFromSlot(0, 1, player);
			}
			else if(player.getCurrentEquippedItem() != null)
			{//put in
				if(pillarTile.getStackInSlot(0) == null)
				{//slot empty
					if(pillarTile.isItemValidForSlot(0, player.getCurrentEquippedItem()))
					{
						if(!player.capabilities.isCreativeMode)
							player.getCurrentEquippedItem().stackSize--;

						ItemStack in = player.getCurrentEquippedItem().copy();
						in.stackSize = 1;
						pillarTile.setInventorySlotContents(0, in);
					}
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6)
	{
		if(!world.isRemote)
		{
			TileEntityPotPillar workTile = (TileEntityPotPillar) world.getTileEntity(x, y, z);

			if(workTile.getStackInSlot(0) != null)
			{
				EntityItem itemDropped = new EntityItem(world, x + 0.1875D, y + 1D, z + 0.1875D, workTile.getStackInSlot(0));
				itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

				if(workTile.getStackInSlot(0).hasTagCompound())
					itemDropped.getEntityItem().setTagCompound((NBTTagCompound) workTile.getStackInSlot(0).getTagCompound().copy());

				world.spawnEntityInWorld(itemDropped);
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		TileEntityPotPillar tile = new TileEntityPotPillar();
		return tile;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if(!world.isRemote)
			((TileEntityPotPillar) world.getTileEntity(x, y, z)).onBlockUpdate(rand);
	}


	//	@SideOnly(Side.CLIENT)
	//	@Override
	//	/**
	//	 * A randomly called display update to be able to add particles or other items for display
	//	 */
	//	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	//	{
	//		if(rand.nextInt(25) == 0)
	//		{
	//			TileEntityPotPillar tile = (TileEntityPotPillar) world.getTileEntity(x, y, z);
	//			if(tile.getStackInSlot(0) != null && tile.getStackInSlot(0).isItemEqual(new ItemStack(CraftingPillars.blockChristmasTreeSapling)) && tile.christmasTreeState == 0)
	//			{
	//				for (int i = 0; i < 5; ++i)
	//				{
	//					double d0 = rand.nextGaussian() * 0.02D;
	//					double d1 = rand.nextGaussian() * 0.02D;
	//					double d2 = rand.nextGaussian() * 0.02D;
	//					float width = 0.3F;
	//					float height = 0.5F;
	//					world.spawnParticle("happyVillager", x + 0.6F + (double)(rand.nextFloat() * width  * 2.0F) - width, y + 1.2D + rand.nextFloat() * height, z + 0.6F + (double)(rand.nextFloat() * width * 2.0F) - width, d0, d1, d2);
	//				}
	//			}
	//		}
	//	}

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
}
