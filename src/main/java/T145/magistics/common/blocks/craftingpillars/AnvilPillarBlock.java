package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityAnvilPillar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AnvilPillarBlock extends BaseBlockContainer {
	public AnvilPillarBlock(Material mat) {
		super(mat);
	}

	@Override
	public int getRenderType() {
		return ConfigObjects.anvilPillarRenderID;
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
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote) {
			byte ringGemNum = 0;
			byte anvilGemNum = 0;

			TileEntityAnvilPillar workTile = (TileEntityAnvilPillar) world.getTileEntity(x, y, z);
			ItemStack ring = workTile.getStackInSlot(0);

			if (ring != null) {
				if (ring.stackTagCompound == null)
					ring.stackTagCompound = new NBTTagCompound();

				ItemStack[] inventory = new ItemStack[4];
				NBTTagList nbtlist = ring.stackTagCompound.getTagList("Items", 10);
				for (int i = 0; i < nbtlist.tagCount(); i++) {
					NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
					int j = nbtslot.getByte("Slot") & 255;

					if ((j >= 0) && (j < 4)) {
						inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
						if (inventory[j] != null)
							ringGemNum++;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (workTile.getStackInSlot(i + 1) != null)
						anvilGemNum++;
				}

				// if ring is empty
				if (ringGemNum == 0 && anvilGemNum > 0) {
					// System.out.println("XP level: " + player.experienceLevel + " vs " + anvilGemNum);

					if (player.capabilities.isCreativeMode || player.experienceLevel >= anvilGemNum) {
						nbtlist = new NBTTagList();
						for (int i = 1; i <= 4; i++) {
							if (workTile.getStackInSlot(i) != null) {
								NBTTagCompound nbtslot = new NBTTagCompound();
								nbtslot.setByte("Slot", (byte) (i - 1));
								workTile.getStackInSlot(i).writeToNBT(nbtslot);
								nbtlist.appendTag(nbtslot);

								workTile.decrStackSize(i, workTile.getStackInSlot(i).stackSize);

							}
						}
						ring.stackTagCompound.setTag("Items", nbtlist);
						world.playAuxSFX(1021, x, y, z, 0);

						if (!player.capabilities.isCreativeMode) {
							player.addExperienceLevel(-anvilGemNum);
						}
					}
				} else if (anvilGemNum <= 0 && ringGemNum > 0) {
					// System.out.println("Adding gems to ANVIL");
					for (int i = 0; i < 4; i++) {
						workTile.setInventorySlotContents(i + 1, inventory[i]);
					}

					ring = new ItemStack(ConfigObjects.itemRing);
					ring.stackTagCompound = new NBTTagCompound();
					workTile.setInventorySlotContents(0, ring);

					world.playAuxSFX(1021, x, y, z, 0);
				}
			}
		} else {
			if (ConfigObjects.valentine) {
				for (int i = 0; i < 7; ++i) {
					double d0 = world.rand.nextGaussian() * 0.02D;
					double d1 = world.rand.nextGaussian() * 0.02D;
					double d2 = world.rand.nextGaussian() * 0.02D;
					world.spawnParticle("heart", x + (double) (world.rand.nextFloat()), y + 1, z + (double) (world.rand.nextFloat()), d0, d1, d2);
				}
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TileEntityAnvilPillar pillarTile = (TileEntityAnvilPillar) world.getTileEntity(x, y, z);

		if (hitY < 1F && !player.isSneaking()) {
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}

		if (hitY == 1F) {
			if (player.isSneaking())// pick out
			{
				for (int i = pillarTile.getSizeInventory() - 1; i >= 0; i--) {
					if (pillarTile.getStackInSlot(i) != null) {
						pillarTile.dropItemFromSlot(i, 1, player);
						return true;
					}
				}
			} else if (player.getCurrentEquippedItem() != null) {// put in
				ItemStack in = player.getCurrentEquippedItem().copy();
				in.stackSize = 1;
				for (int i = 0; i < pillarTile.getSizeInventory(); i++) {
					if (pillarTile.getStackInSlot(i) == null && pillarTile.isItemValidForSlot(i, in)) {
						if (!player.capabilities.isCreativeMode)
							player.getCurrentEquippedItem().stackSize--;

						pillarTile.setInventorySlotContents(i, in);
						return true;
					}
				}

			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		if (!world.isRemote) {
			TileEntityAnvilPillar workTile = (TileEntityAnvilPillar) world.getTileEntity(x, y, z);

			for (int i = 0; i < workTile.getSizeInventory(); i++) {
				if (workTile.getStackInSlot(i) != null) {
					EntityItem itemDropped = new EntityItem(world, x + 0.1875D, y + 1D, z + 0.1875D, workTile.getStackInSlot(i));
					itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

					if (workTile.getStackInSlot(i).hasTagCompound())
						itemDropped.getEntityItem().setTagCompound((NBTTagCompound) workTile.getStackInSlot(i).getTagCompound().copy());

					world.spawnEntityInWorld(itemDropped);
				}
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		TileEntityAnvilPillar tile = new TileEntityAnvilPillar();
		return tile;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister itemIcon) {
		blockIcon = itemIcon.registerIcon("craftingpillars:craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return blockIcon;
	}
}