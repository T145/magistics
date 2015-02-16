package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.tiles.pillars.TilePillarFurnace;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarFurnace extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarFurnace(Material mat) {
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
		return MathHelper.floor_double(entity.rotationYaw * 4F / 360F + 0.5D) & 3;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote) {
			TilePillarFurnace pillarTile = (TilePillarFurnace) world.getTileEntity(x, y, z);

			if (pillarTile.getStackInSlot(2) != null)
				if (player.isSneaking()) {
					onCrafting(pillarTile.getStackInSlot(2), player);
					pillarTile.dropItemFromSlot(2, pillarTile.getStackInSlot(2).stackSize, player);
				} else {
					ItemStack itemStack = pillarTile.getStackInSlot(2).copy();
					itemStack.stackSize = 1;
					onCrafting(itemStack, player);
					pillarTile.dropItemFromSlot(2, 1, player);
				}
		} else
			for (int i = 0; i < 7; i++) {
				double d0 = world.rand.nextGaussian() * 0.02D;
				double d1 = world.rand.nextGaussian() * 0.02D;
				double d2 = world.rand.nextGaussian() * 0.02D;
				world.spawnParticle("heart", x + (double) (world.rand.nextFloat()), y + 1, z + (double) (world.rand.nextFloat()), d0, d1, d2);
			}
	}

	public void onCrafting(ItemStack is, EntityPlayer player) {
		int stackSize = is.stackSize;
		is.onCrafting(player.worldObj, player, stackSize);

		if (is != null) {
			if (!player.worldObj.isRemote) {
				int i = stackSize;
				float f = FurnaceRecipes.smelting().func_151398_b(is);
				int j;

				if (f == 0F)
					i = 0;
				else if (f < 1F) {
					for (j = MathHelper.floor_float(i * f); j < MathHelper.ceiling_float_int(i * f) && (float) Math.random() < i * f - j; j++);
					i = j;
				}

				while (i > 0) {
					j = EntityXPOrb.getXPSplit(i);
					i -= j;
					player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj, player.posX, player.posY + 0.5D, player.posZ + 0.5D, j));
				}
			}

			stackSize = 0;

			FMLCommonHandler.instance().firePlayerSmeltedEvent(player, is);

			if (is.getItem() == Items.iron_ingot)
				player.addStat(AchievementList.acquireIron, 1);

			if (is.getItem() == Items.cooked_fished)
				player.addStat(AchievementList.cookFish, 1);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TilePillarFurnace pillarTile = (TilePillarFurnace) world.getTileEntity(x, y, z);

		if (!player.isSneaking() && hitY < 1F && (player.inventory.getCurrentItem() == null || !TileEntityFurnace.isItemFuel(player.inventory.getCurrentItem()))) {
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}

		if (player.isSneaking()) {
			if (hitY < 1F)
				pillarTile.dropItemFromSlot(1, 1, player);
			else
				pillarTile.dropItemFromSlot(0, 1, player);
		} else if (player.getCurrentEquippedItem() != null) {
			if (hitY < 1F) {
				if (pillarTile.getStackInSlot(1) == null) {
					ItemStack in = player.getCurrentEquippedItem().copy();
					in.stackSize = 1;
					if (TileEntityFurnace.isItemFuel(in)) {
						pillarTile.setInventorySlotContents(1, in);

						if (!player.capabilities.isCreativeMode)
							player.getCurrentEquippedItem().stackSize--;
					}
				} else if (pillarTile.getStackInSlot(1).isItemEqual(player.getCurrentEquippedItem()) && (pillarTile.getStackInSlot(1).stackSize < pillarTile.getStackInSlot(1).getMaxStackSize())) {
					if (!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;

					pillarTile.decrStackSize(1, -1);
				}
			} else {
				if (pillarTile.getStackInSlot(0) == null) {
					ItemStack in = player.getCurrentEquippedItem().copy();
					in.stackSize = 1;
					if (FurnaceRecipes.smelting().getSmeltingResult(in) != null) {
						pillarTile.setInventorySlotContents(0, in);

						if (!player.capabilities.isCreativeMode)
							player.getCurrentEquippedItem().stackSize--;
					}
				} else if (pillarTile.getStackInSlot(0).isItemEqual(player.getCurrentEquippedItem()) && (pillarTile.getStackInSlot(0).stackSize < pillarTile.getStackInSlot(0).getMaxStackSize())) {
					if (!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;

					pillarTile.decrStackSize(0, -1);
				}
			}
		}

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		if (!world.isRemote) {
			TilePillarFurnace pillarTile = (TilePillarFurnace) world.getTileEntity(x, y, z);

			for (int i = 0; i < pillarTile.getSizeInventory(); i++) {
				if (pillarTile.getStackInSlot(i) != null) {
					EntityItem itemDropped = new EntityItem(world, x + 0.5D, y, z + 0.5D, pillarTile.getStackInSlot(i));
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
		return new TilePillarFurnace();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		TilePillarFurnace furnace = (TilePillarFurnace) world.getTileEntity(x, y, z);

		if (furnace.burnTime > 0) {
			double rx = x + rand.nextDouble() / 2 + 0.25D, ry = y + rand.nextDouble() / 2 + 0.25D, rz = z + rand.nextDouble() / 2 + 0.25D;

			world.spawnParticle("smoke", rx, ry, rz, 0D, 0D, 0D);
			world.spawnParticle("flame", rx, ry, rz, 0D, 0D, 0D);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TilePillarFurnace) {
			TilePillarFurnace workPillar = (TilePillarFurnace) tile;
			return workPillar.getLightLevel();
		}
		return super.getLightValue(world, x, y, z);
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
}