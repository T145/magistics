package T145.magistics.common.blocks.craftingpillars;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityTrashPillar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarTrash extends BlockPillarBase
{
	public BlockPillarTrash(Material mat)
	{
		super(mat);
		setBlockBounds(0, 0, 0, 1F, 15F / 16F, 1F);
	}

	@Override
	public int getRenderType()
	{
		return ConfigObjects.trashPillarRenderID;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
			return true;

		TileEntityTrashPillar pillarTile = (TileEntityTrashPillar) world.getTileEntity(x, y, z);

		if (!player.isSneaking() && player.getCurrentEquippedItem() == null)
		{
			pillarTile.isOpen = !pillarTile.isOpen;
			pillarTile.onInventoryChanged();
			world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.click", 0.3F, world.rand.nextFloat() * 0.1F + 0.5F);

			return false;
		}

		return false;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor blockID
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y - 1, z))
		{
			TileEntityTrashPillar pillarTile = (TileEntityTrashPillar) world.getTileEntity(x, y, z);

			pillarTile.isOpen = !pillarTile.isOpen;
			pillarTile.onInventoryChanged();

			world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.click", 0.3F, world.rand.nextFloat() * 0.1F + 0.9F);

		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityItem)
		{
			TileEntityTrashPillar pillarTile = (TileEntityTrashPillar) world.getTileEntity(x, y, z);
			if (pillarTile.isOpen)
			{
				if (world.isRemote)
				{
					world.spawnParticle("largesmoke", entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D);
					world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "random.fizz", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

				} else
				{
					entity.setDead();
				}
			}
		}
	}

	/**
	 * A randomly called display update to be able to add particles or other
	 * items for display
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		TileEntityTrashPillar pillarTile = (TileEntityTrashPillar) world.getTileEntity(x, y, z);

		if (pillarTile.isOpen)
		{
			double d0 = (double) ((float) x + rand.nextFloat());
			double d1 = (double) ((float) y + 0.8F);
			double d2 = (double) ((float) z + rand.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			world.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
		}

	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		TileEntityTrashPillar tile = new TileEntityTrashPillar();
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
}
