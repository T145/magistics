package T145.magistics.common.tiles;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import T145.magistics.common.blocks.BlockThinkTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileThinkTank extends TileEntity implements ISidedInventory {
	private static final int[] slots_top = new int[] { 0 }, slots_bottom = new int[] { 1 }, slots_sides = new int[] { 1 };

	long lastsigh = System.currentTimeMillis() + 1500L;
	protected static Random rand = new Random();

	private ItemStack[] inventoryStacks = new ItemStack[2];
	public int burnTime = 55, currentItemBurnTime, cookTime, space = 1, warmedUpNumber, rotationTicks;
	private String field_94130_e = "thaumicExploration.test";
	private boolean canSpaz;
	public float pageFlipPrev, pageFlip;

	public int getSizeInventory() {
		return inventoryStacks.length;
	}

	public ItemStack getStackInSlot(int par1) {
		return inventoryStacks[par1];
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1 - space, yCoord - 1, zCoord - 1 - space, xCoord + 1 + space, yCoord + 1, zCoord + 1 + space);
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if (inventoryStacks[par1] != null) {
			ItemStack itemstack;

			if (inventoryStacks[par1].stackSize <= par2) {
				itemstack = inventoryStacks[par1];
				inventoryStacks[par1] = null;
				return itemstack;
			} else {
				itemstack = inventoryStacks[par1].splitStack(par2);

				if (inventoryStacks[par1].stackSize == 0) {
					inventoryStacks[par1] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1) {
		if (inventoryStacks[par1] != null) {
			ItemStack itemstack = inventoryStacks[par1];
			inventoryStacks[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		inventoryStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit()) {
			par2ItemStack.stackSize = getInventoryStackLimit();
		}
	}

	public String getInvName() {
		return isInvNameLocalized() ? field_94130_e : "thaumicExploration:guiThinkTank";
	}

	public boolean isInvNameLocalized() {
		return field_94130_e != null && field_94130_e.length() > 0;
	}

	public void setGuiDisplayName(String par1Str) {
		field_94130_e = par1Str;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getTag("Items");
		inventoryStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inventoryStacks.length) {
				inventoryStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		burnTime = par1NBTTagCompound.getShort("BurnTime");
		cookTime = par1NBTTagCompound.getShort("CookTime");

		if (par1NBTTagCompound.hasKey("CustomName")) {
			field_94130_e = par1NBTTagCompound.getString("CustomName");
		}

		if (par1NBTTagCompound.hasKey("WarmUpTicks")) {
			warmedUpNumber = par1NBTTagCompound.getShort("WarmUpTicks");
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		super.getDescriptionPacket();
		NBTTagCompound access = new NBTTagCompound();
		access.setInteger("warmedUpNumber", warmedUpNumber);
		access.setInteger("rotationTicks", rotationTicks);
		access.setInteger("space", space);

		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, access);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		NBTTagCompound access = pkt.func_148857_g();
		warmedUpNumber = access.getInteger("warmedUpNumber");
		rotationTicks = access.getInteger("rotationTicks");
		space = access.getInteger("space");

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) burnTime);
		par1NBTTagCompound.setShort("WarmUpTicks", (short) warmedUpNumber);
		par1NBTTagCompound.setShort("CookTime", (short) cookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inventoryStacks.length; ++i) {
			if (inventoryStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventoryStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (isInvNameLocalized()) {
			par1NBTTagCompound.setString("CustomName", field_94130_e);
		}
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) {
		return cookTime * par1 / 200;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}

		return burnTime * par1 / currentItemBurnTime;
	}

	public boolean isBurning() {
		return true;
	}

	public void updateEntity() {
		boolean flag = burnTime > 0, flag1 = false;

		if (!worldObj.isRemote) {
			if (canSmelt()) {
				if (warmedUpNumber < 40) {
					warmedUpNumber++;
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
			}

			if (!canSmelt() && warmedUpNumber > 0) {
				warmedUpNumber--;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}

		if (warmedUpNumber > 0) {
			rotationTicks++;
		}

		if (!worldObj.isRemote) {
			if (isBurning() && canSmelt()) {
				++cookTime;

				if (cookTime == 200) {
					cookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else {
				cookTime = 0;
			}

			if (flag != burnTime > 0) {
				flag1 = true;
				BlockThinkTank.updateState(burnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (worldObj.isRemote) {
			canSpaz = true;
			Entity entity = null;

			if (entity == null) {
				entity = worldObj.getClosestPlayer(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, 6.0D);

				if ((entity != null) && (lastsigh < System.currentTimeMillis())) {
					worldObj.playSound(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "thaumcraft:brain", 0.15F, 0.8F + worldObj.rand.nextFloat() * 0.4F, false);
					lastsigh = (System.currentTimeMillis() + 5000L + worldObj.rand.nextInt(25000));
				}
			}
		}

		if (flag1) {
			markDirty();
		}
	}

	private boolean canSmelt() {
		if (!enoughSpace()) {
			return false;
		}
		if (inventoryStacks[0] == null) {
			return false;
		} else {
			if (inventoryStacks[0].getItem() == Items.book) {
				ItemStack itemstack = new ItemStack(ConfigItems.itemResource, 1, 9);
				if (inventoryStacks[1] == null)
					return true;
				if (!inventoryStacks[1].isItemEqual(itemstack))
					return false;
				int result = inventoryStacks[1].stackSize + itemstack.stackSize;
				return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
			} else if (inventoryStacks[0].getItem() == Items.enchanted_book) {
				ItemStack itemstack = new ItemStack(ConfigItems.itemResource, 2, 9);
				if (inventoryStacks[1] == null)
					return true;
				if (!inventoryStacks[1].isItemEqual(itemstack))
					return false;
				int result = inventoryStacks[1].stackSize + itemstack.stackSize;
				return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
			} else {
				return false;
			}
		}
	}

	public boolean enoughSpace() {
		boolean enoughSpace = true;
		boolean muchSpace = true;
		for (int x = -2; x < 3; x++) {
			for (int z = -2; z < 3; z++) {
				for (int y = -1; y < 2; y++) {
					if (worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z).getMaterial() != Config.airyMaterial && worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z).getMaterial() != Material.air && !worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z).isReplaceable(worldObj, xCoord + x, yCoord + y, zCoord + z)) {
						if (!(xCoord + x == xCoord && zCoord + z == zCoord && (yCoord + y == yCoord || yCoord + y == yCoord - 1))) {
							if (Math.abs(x) > 1 || Math.abs(z) > 1) {
								muchSpace = false;
							} else {
								enoughSpace = false;
							}
						}
					}
				}
			}
		}

		if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) != Blocks.bookshelf) {
			enoughSpace = false;
		}

		int oldSpace = space;
		space = muchSpace ? 1 : 0;

		if (space != oldSpace) {
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

		return enoughSpace;
	}

	public void smeltItem() {
		if (canSmelt()) {
			double rand = Math.random();
			boolean genFrag = false;
			int numberFrags = 0;

			if (inventoryStacks[0].getItem() == Items.enchanted_book) {
				if (rand > 0.33) {
					genFrag = true;
					numberFrags = (rand > 0.66) ? 1 : 2;
				}
			}

			if (inventoryStacks[0].getItem() == Items.book) {
				if (rand <= 0.1) {
					genFrag = true;
					numberFrags = 1;
				}
			}

			if (genFrag) {
				ItemStack itemstack = new ItemStack(ConfigItems.itemResource, numberFrags, 9);
				if (inventoryStacks[1] == null) {
					inventoryStacks[1] = itemstack.copy();
				} else if (inventoryStacks[1].isItemEqual(itemstack)) {
					inventoryStacks[1].stackSize += itemstack.stackSize;
				}

			}
			--inventoryStacks[0].stackSize;

			if (inventoryStacks[0].stackSize <= 0) {
				inventoryStacks[0] = null;
			}
		}
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
	}

	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return par1 == 2 ? false : (par1 == 1 ? false : true);
	}

	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return isItemValidForSlot(par1, par2ItemStack);
	}

	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
}