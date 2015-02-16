package T145.magistics.common.tiles.pillars;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.brewing.PotionBrewedEvent;
import T145.magistics.common.Magistics;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TilePillarBrewing extends TileBase implements IInventory, ISidedInventory {
	private ItemStack[] inventory = new ItemStack[5];

	public float rot = 0F;
	public boolean showNum = false;

	private int brewTime, filledSlots, ingredientID;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			rot += 0.1F;
			if (rot >= 360F)
				rot -= 360F;

			if (brewTime > 0)
				--brewTime;
			else
				brewTime = 350;
		}

		if (!worldObj.isRemote) {
			if (brewTime > 0) {
				--brewTime;

				if (brewTime == 0) {
					brewPotions();
					onInventoryChanged();
				} else if (!canBrew()) {
					brewTime = 0;
					onInventoryChanged();
				} else if (ingredientID != Item.getIdFromItem(inventory[4].getItem())) {
					brewTime = 0;
					onInventoryChanged();
				}
			} else if (canBrew()) {
				brewTime = 350;
				ingredientID = Item.getIdFromItem(inventory[4].getItem());
			}
		}

		super.updateEntity();
	}

	public int getBrewTime() {
		return brewTime;
	}

	public boolean canBrew() {
		if (inventory[4] != null && inventory[4].stackSize > 0) {
			ItemStack itemstack = inventory[4];

			if (!itemstack.getItem().isPotionIngredient(itemstack)) {
				return false;
			} else {
				boolean flag = false;

				for (int i = 0; i < 4; ++i) {
					if (inventory[i] != null && inventory[i].getItem() instanceof ItemPotion) {
						int j = inventory[i].getItemDamage();
						int k = TilePillarBrewing.getPotionResult(j, itemstack);

						if (!ItemPotion.isSplash(j) && ItemPotion.isSplash(k)) {
							flag = true;
							break;
						}

						List list = Items.potionitem.getEffects(j), list1 = Items.potionitem.getEffects(k);

						if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null) && j != k) {
							flag = true;
							break;
						}
					}
				}
				return flag;
			}
		} else
			return false;
	}

	private void brewPotions() {
		if (canBrew()) {
			ItemStack itemstack = inventory[4];

			for (int i = 0; i < 4; ++i) {
				if (inventory[i] != null && inventory[i].getItem() instanceof ItemPotion) {
					int j = inventory[i].getItemDamage(), k = TilePillarBrewing.getPotionResult(j, itemstack);
					List list = Items.potionitem.getEffects(j);
					List list1 = Items.potionitem.getEffects(k);

					if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) {
						if (j != k)
							inventory[i].setItemDamage(k);
					} else if (!ItemPotion.isSplash(j)
							&& ItemPotion.isSplash(k)) {
						inventory[i].setItemDamage(k);
					}
				}
			}

			if (itemstack.getItem().hasContainerItem()) {
				inventory[4] = itemstack.getItem().getContainerItem(inventory[4]);
			} else {
				--inventory[4].stackSize;

				if (inventory[4].stackSize <= 0)
					inventory[4] = null;
			}

			MinecraftForge.EVENT_BUS.post(new PotionBrewedEvent(inventory));
		}
	}

	public static int getPotionResult(int par1, ItemStack itemstack) {
		return itemstack == null ? par1 : (itemstack.getItem().isPotionIngredient(itemstack) ? PotionHelper.applyIngredient(par1, itemstack.getItem().getPotionEffect(itemstack)) : par1);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tagAt = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			byte j = tagAt.getByte("Slot");

			if (j >= 0 && j < inventory.length)
				inventory[j] = ItemStack.loadItemStackFromNBT(tagAt);
		}

		brewTime = nbt.getShort("BrewTime");
		showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BrewTime", (short) brewTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inventory.length; ++i) {
			if (inventory[i] != null) {
				NBTTagCompound tagAt = new NBTTagCompound();
				tagAt.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(tagAt);
				nbttaglist.appendTag(tagAt);
			}
		}

		nbt.setTag("Items", nbttaglist);
		nbt.setBoolean("showNum", showNum);
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();

		if (!worldObj.isRemote)
			Magistics.proxy.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 64);
	}

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player) {
		if (!worldObj.isRemote && getStackInSlot(slot) != null) {
			EntityItem itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(decrStackSize(slot, amount));
			worldObj.spawnEntityInWorld(itemEntity);
			onInventoryChanged();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setBrewTime(int par1) {
		brewTime = par1;
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = null;

		if (inventory[slot] != null) {
			if (inventory[slot].stackSize <= amount) {
				stack = inventory[slot];
				inventory[slot] = null;
				onInventoryChanged();
			} else {
				stack = inventory[slot].splitStack(amount);

				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;

				onInventoryChanged();
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
			setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return new int[] { 0, 1, 2, 3, 4 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		if (slot == 4 && itemstack.getItem().isPotionIngredient(itemstack))
			return true;
		if (slot != 4 && itemstack.getItem() instanceof ItemPotion || itemstack.getItem() == Items.glass_bottle)
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		if (slot == 4 && (side == 0 || side == 1)/* DOWN */)
			return true;
		if (slot == 4)
			return false;
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public String getInventoryName() {
		return "Brewing Pillar";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return super.isUseableByPlayer(player);
	}
}