package T145.magistics.common.blocks.pillars;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.pillars.TilePillarCrafting;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockPillarCrafting extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarCrafting(Material mat) {
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
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote) {
			TilePillarCrafting workTile = (TilePillarCrafting) world.getTileEntity(x, y, z);

			if (player.isSneaking())
				while (workTile.getStackInSlot(workTile.getSizeInventory()) != null)
					workTile.craftItem(player);
			else if (workTile.getStackInSlot(workTile.getSizeInventory()) != null)
				workTile.craftItem(player);
		} else if (ConfigObjects.valentine)
			for (int i = 0; i < 7; ++i) {
				double d0 = world.rand.nextGaussian() * 0.02D;
				double d1 = world.rand.nextGaussian() * 0.02D;
				double d2 = world.rand.nextGaussian() * 0.02D;
				world.spawnParticle("heart", x + (double) (world.rand.nextFloat()), y + 1, z + (double) (world.rand.nextFloat()), d0, d1, d2);
			}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, entity), 0);
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity) {
		return MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TilePillarCrafting workTile = (TilePillarCrafting) world.getTileEntity(x, y, z);
		ItemStack equipped = player.getCurrentEquippedItem();

		if (hitY < 1F && hitY > 0F) {
			if (!player.isSneaking()) {
				if (equipped != null && workTile.getStackInSlot(10) == null && workTile.isItemValidForSlot(10, equipped)) {
					ItemStack in = equipped.copy();
					if (!player.capabilities.isCreativeMode)
						equipped.stackSize--;

					in.stackSize = 1;
					workTile.setInventorySlotContents(10, in);
					return true;
				}
			} else {
				if (workTile.getStackInSlot(10) != null)
					workTile.dropItemFromSlot(10, player);
				return true;
			}
		}

		if (hitY < 1F && !player.isSneaking()) {
			workTile.showNum = !workTile.showNum;
			workTile.onInventoryChanged();
		}

		if (hitY == 1F) {
			if (player.isSneaking()) {
				hitX = (int) Math.floor(hitX / 0.33F);
				if (hitX > 2)
					hitX = 2;
				if (hitX < 0)
					hitX = 0;
				hitZ = (int) Math.floor(hitZ / 0.33F);
				if (hitZ > 2)
					hitZ = 2;
				if (hitZ < 0)
					hitZ = 0;

				int i = (int) (hitX * 3 + hitZ);

				if (workTile.getStackInSlot(i) != null)
					workTile.dropItemFromSlot(i, player);
			} else if (equipped != null) {
				hitX = (int) Math.floor(hitX / 0.33F);
				if (hitX > 2)
					hitX = 2;
				if (hitX < 0)
					hitX = 0;
				hitZ = (int) Math.floor(hitZ / 0.33F);
				if (hitZ > 2)
					hitZ = 2;
				if (hitZ < 0)
					hitZ = 0;

				int i = (int) (hitX * 3 + hitZ);

				if (workTile.getStackInSlot(i) == null) {

					ItemStack in = equipped.copy();
					if (!player.capabilities.isCreativeMode)
						equipped.stackSize--;

					in.stackSize = 1;
					workTile.setInventorySlotContents(i, in);
				} else if ((workTile.getStackInSlot(i).isItemEqual(equipped)) && (workTile.getStackInSlot(i).stackSize < workTile.getStackInSlot(i).getMaxStackSize())) {
					if (!player.capabilities.isCreativeMode)
						equipped.stackSize--;

					workTile.decrStackSize(i, -1);
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		if (!world.isRemote) {
			TilePillarCrafting pillarTile = (TilePillarCrafting) world.getTileEntity(x, y, z);

			for (int i = 0; i < pillarTile.getSizeInventory() + 2; i++) {
				if (pillarTile.getStackInSlot(i) != null && i != 9) {
					EntityItem itemDropped = new EntityItem(world, x + 0.1875D, y + 1D, z + 0.1875D, pillarTile.getStackInSlot(i));
					itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

					if (pillarTile.getStackInSlot(i).hasTagCompound())
						itemDropped.getEntityItem().setTagCompound((NBTTagCompound) pillarTile.getStackInSlot(i).getTagCompound().copy());

					world.spawnEntityInWorld(itemDropped);
				}
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TilePillarCrafting();
	}
}