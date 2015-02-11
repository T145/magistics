package T145.magistics.common.blocks.craftingpillars;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityBrewingPillar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarBrewing extends BlockPillarBase
{

	public BlockPillarBrewing(Material mat)
	{
		super(mat);
		float f = 3*1/16F;
		this.setBlockBounds(f, 0.0F, f, 1.0F - f, 1.0F, 1.0F - f);
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
	public int getRenderType()
	{
		return ConfigObjects.brewingillarRenderID;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
			return true;

		TileEntityBrewingPillar pillarTile = (TileEntityBrewingPillar) world.getTileEntity(x, y, z);
		int topSlot = 4;
		int sideSlot = side-2;

		if(!player.isSneaking() && hitY < 1.0F && (player.inventory.getCurrentItem() == null ||
				!(player.inventory.getCurrentItem().getItem() instanceof ItemPotion || player.inventory.getCurrentItem().getItem() == Items.glass_bottle)))
		{
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}

		if(player.isSneaking())
		{
			if(side == 1)
			{
				pillarTile.dropItemFromSlot(topSlot, 1, player);
			}
			else
			{
				if(side > 1)
					pillarTile.dropItemFromSlot(sideSlot, 1, player);
			}
		}
		else if(player.getCurrentEquippedItem() != null)
		{
			if(side > 1)
			{
				if(pillarTile.getStackInSlot(sideSlot) == null)
				{
					ItemStack in = player.getCurrentEquippedItem().copy();
					in.stackSize = 1;
					if(in.getItem() instanceof ItemPotion || in.getItem() == Items.glass_bottle)
					{
						pillarTile.setInventorySlotContents(sideSlot, in);

						if(!player.capabilities.isCreativeMode)
							player.getCurrentEquippedItem().stackSize--;
					}
				}
			}
			else if(side == 1)
			{
				if(pillarTile.getStackInSlot(topSlot) == null)
				{
					ItemStack in = player.getCurrentEquippedItem().copy();
					in.stackSize = 1;
					if(in.getItem().isPotionIngredient(in))
					{
						pillarTile.setInventorySlotContents(topSlot, in);

						if(!player.capabilities.isCreativeMode)
							player.getCurrentEquippedItem().stackSize--;
					}
				}
				else if(pillarTile.getStackInSlot(topSlot).isItemEqual(player.getCurrentEquippedItem()) && (pillarTile.getStackInSlot(topSlot).stackSize < pillarTile.getStackInSlot(topSlot).getMaxStackSize()))
				{
					if(!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;

					pillarTile.decrStackSize(topSlot, -1);
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
			TileEntityBrewingPillar pillarTile = (TileEntityBrewingPillar) world.getTileEntity(x, y, z);

			for(int i = 0; i < pillarTile.getSizeInventory(); i++)
			{
				if(pillarTile.getStackInSlot(i) != null)
				{
					EntityItem itemDropped = new EntityItem(world, x + 0.5D, y, z + 0.5D, pillarTile.getStackInSlot(i));
					itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

					if(pillarTile.getStackInSlot(i).hasTagCompound())
						itemDropped.getEntityItem().setTagCompound((NBTTagCompound) pillarTile.getStackInSlot(i).getTagCompound().copy());

					world.spawnEntityInWorld(itemDropped);
				}
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		TileEntityBrewingPillar tile = new TileEntityBrewingPillar();
		return tile;
	}

	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		TileEntityBrewingPillar tile = ((TileEntityBrewingPillar) world.getTileEntity(x, y, z));
		if(tile.canBrew() && tile.getBrewTime() > 0)
		{
			for(int i = 0; i < 4; i++)
			{
				if(rand.nextInt(4) <= 1){
					if(tile.getStackInSlot(i) != null)
					{
						int rotI = i;
						if(i == 3) rotI = 0;
						if(i == 0) rotI = 3;
						float subX = 0;
						float subZ = 0;

						if(rotI == 0)
							subX = 0.4F;
						if(rotI == 2)
							subX = -0.4F;
						if(rotI == 1)
							subZ = 0.4F;
						if(rotI == 3)
							subZ = -0.4F;
						int j = tile.getStackInSlot(i).getItemDamage();
						int k = TileEntityBrewingPillar.getPotionResult(j, tile.getStackInSlot(4));
						List list = Items.potionitem.getEffects(j);
						List list1 = Items.potionitem.getEffects(k);
						if (((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) || !ItemPotion.isSplash(j) && ItemPotion.isSplash(k))
						{
							world.spawnParticle("smoke", x+0.5F + subX, y + 0.7F, z +0.5F + subZ, 0D, 0D, 0D);
						}
					}
				}
			}
		}
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
}
