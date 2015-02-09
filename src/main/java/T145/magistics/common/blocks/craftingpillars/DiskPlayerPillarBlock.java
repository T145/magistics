package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityDiskPlayerPillar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DiskPlayerPillarBlock extends BaseBlockContainer
{
	public DiskPlayerPillarBlock(Material mat)
	{
		super(mat);
	}

	@Override
	public int getRenderType()
	{
		return ConfigObjects.diskPlayerRenderID;
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
	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntityDiskPlayerPillar tile = ((TileEntityDiskPlayerPillar)world.getTileEntity(x, y, z));
		if (tile.getDisk() == null)
		{
			if(side == 1)
			{
				ItemStack disk = player.getCurrentEquippedItem();
				if(disk != null && disk.getItem() instanceof ItemRecord)
				{
//					if(disk.isItemEqual(new ItemStack(CraftingPillars.itemDiscElysium)))
//						player.addStat(CraftingPillars.achievementDisc, 1);
					this.insertRecord(world, x, y, z, player.getCurrentEquippedItem());
					world.playAuxSFXAtEntity(null, 1005, x, y, z, Item.getIdFromItem(disk.getItem()));
					if(!player.capabilities.isCreativeMode)
						--player.getCurrentEquippedItem().stackSize;
					return true;
				}
			}
		}
		else
		{
			if(side == 1)
			{
				this.ejectRecord(world, x, y, z);
				return true;
			}
		}
		if(side != 1) tile.showNum = !tile.showNum;
		return true;
	}

	/**
	 * Insert the specified music disc in the jukebox at the given coordinates
	 */
	public void insertRecord(World world, int x, int y, int z, ItemStack item)
	{
		if (!world.isRemote)
		{
			TileEntityDiskPlayerPillar tileentityrecordplayer = (TileEntityDiskPlayerPillar)world.getTileEntity(x, y, z);

			if (tileentityrecordplayer != null)
			{
				tileentityrecordplayer.setDisk(item.copy());
			}
		}
	}

	/**
	 * Ejects the current record inside of the jukebox.
	 */
	public void ejectRecord(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			TileEntityDiskPlayerPillar tileentityrecordplayer = (TileEntityDiskPlayerPillar)world.getTileEntity(x, y, z);

			if (tileentityrecordplayer != null)
			{
				ItemStack itemstack = tileentityrecordplayer.getDisk();

				if (itemstack != null)
				{
					world.playAuxSFX(1005, x, y, z, 0);
					world.playRecord((String)null, x, y, z);
					tileentityrecordplayer.setDisk((ItemStack)null);
					float f = 0.7F;
					double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
					double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.2D + 0.6D;
					double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
					ItemStack itemstack1 = itemstack.copy();
					EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, itemstack1);
					entityitem.delayBeforeCanPickup = 10;
					world.spawnEntityInWorld(entityitem);
				}
			}
		}
	}

	/**
	 * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
	 * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
	 * metadata
	 */
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block block, int par6)
	{
		this.ejectRecord(par1World, par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		if (!par1World.isRemote)
		{
			super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
		}
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityDiskPlayerPillar();
	}

	/**
	 * If this returns true, then comparators facing away from this block will use the value from
	 * getComparatorInputOverride instead of the actual redstone signal strength.
	 */
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	/**
	 * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
	 * strength when this block inputs to a comparator.
	 */
	public int getComparatorInputOverride(World world, int x, int y, int p_149736_4_, int z)
	{
		ItemStack itemstack = ((TileEntityDiskPlayerPillar)world.getTileEntity(x, y, z)).getDisk();
		return itemstack == null ? 0 : Item.getIdFromItem(itemstack.getItem()) + 1 - Item.getIdFromItem(Items.record_13);
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
