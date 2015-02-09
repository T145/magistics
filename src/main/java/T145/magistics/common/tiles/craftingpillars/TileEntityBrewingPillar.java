package T145.magistics.common.tiles.craftingpillars;

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

public class TileEntityBrewingPillar extends BaseTileEntity implements IInventory, ISidedInventory {
	private ItemStack[] inventory = new ItemStack[5];

	// @SideOnly(Side.CLIENT)
	public float rot = 0F;
	public boolean showNum = false;

	private int brewTime;
	/**
	 * an integer with each bit specifying whether that slot of the stand
	 * contains a potion
	 */
	private int filledSlots;
	private int ingredientID;

	@Override
	public void updateEntity() {
		// System.out.println((this.worldObj.isRemote ? "Client: " :
		// "Server: ")+this.cookTime+" "+this.burnTime);

		if (this.worldObj.isRemote) {
			this.rot += 0.1F;
			if (this.rot >= 360F)
				this.rot -= 360F;

			if (this.brewTime > 0) {
				--this.brewTime;
			} else {
				this.brewTime = 350;
			}
		}


		if (!this.worldObj.isRemote) {
			if (this.brewTime > 0) {
				--this.brewTime;

				if (this.brewTime == 0) {
					this.brewPotions();
					this.onInventoryChanged();
				} else if (!this.canBrew()) {
					this.brewTime = 0;
					this.onInventoryChanged();
				} else if (this.ingredientID != Item.getIdFromItem(this.inventory[4].getItem())) {
					this.brewTime = 0;
					this.onInventoryChanged();
				}
			} else if (this.canBrew()) {
				this.brewTime = 350;
				this.ingredientID = Item.getIdFromItem(this.inventory[4].getItem());
			}
		}

		super.updateEntity();
	}

	public int getBrewTime() {
		return this.brewTime;
	}

	public boolean canBrew() {
		if (this.inventory[4] != null && this.inventory[4].stackSize > 0) {
			ItemStack itemstack = this.inventory[4];

			if (!itemstack.getItem().isPotionIngredient(itemstack)) {
				return false;
			} else {
				boolean flag = false;

				for (int i = 0; i < 4; ++i) {
					if (this.inventory[i] != null
							&& this.inventory[i].getItem() instanceof ItemPotion) {
						int j = this.inventory[i].getItemDamage();
						int k = TileEntityBrewingPillar.getPotionResult(j, itemstack);

						if (!ItemPotion.isSplash(j) && ItemPotion.isSplash(k)) {
							flag = true;
							break;
						}

						List list = Items.potionitem.getEffects(j);
						List list1 = Items.potionitem.getEffects(k);

						if ((j <= 0 || list != list1)
								&& (list == null || !list.equals(list1)
								&& list1 != null) && j != k) {
							flag = true;
							break;
						}
					}
				}

				return flag;
			}
		} else {
			return false;
		}
	}

	private void brewPotions()
	{
		if (this.canBrew())
		{
			ItemStack itemstack = this.inventory[4];

			for (int i = 0; i < 4; ++i)
			{
				if (this.inventory[i] != null && this.inventory[i].getItem() instanceof ItemPotion)
				{
					int j = this.inventory[i].getItemDamage();
					int k = TileEntityBrewingPillar.getPotionResult(j, itemstack);
					List list = Items.potionitem.getEffects(j);
					List list1 = Items.potionitem.getEffects(k);

					if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null))
					{
						if (j != k)
						{
							this.inventory[i].setItemDamage(k);
						}
					}
					else if (!ItemPotion.isSplash(j) && ItemPotion.isSplash(k))
					{
						this.inventory[i].setItemDamage(k);
					}
				}
			}

			if (itemstack.getItem().hasContainerItem())
			{
				this.inventory[4] = itemstack.getItem().getContainerItem(this.inventory[4]);
			}
			else
			{
				--this.inventory[4].stackSize;

				if (this.inventory[4].stackSize <= 0)
				{
					this.inventory[4] = null;
				}
			}

			MinecraftForge.EVENT_BUS.post(new PotionBrewedEvent(this.inventory));
		}
	}

	/**
	 * The result of brewing a potion of the specified damage value with an
	 * ingredient itemstack.
	 */
	 public static int getPotionResult(int par1, ItemStack itemstack) {
		return itemstack == null ? par1 : (itemstack.getItem().isPotionIngredient(itemstack) ?
				PotionHelper.applyIngredient(par1, itemstack.getItem().getPotionEffect(itemstack)) : par1);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			byte j = nbttagcompound1.getByte("Slot");

			if (j >= 0 && j < this.inventory.length) {
				this.inventory[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.brewTime = nbt.getShort("BrewTime");

		this.showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BrewTime", (short) this.brewTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
		nbt.setBoolean("showNum", this.showNum);
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();

		if (!this.worldObj.isRemote)
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
	}

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player) {
		if (!this.worldObj.isRemote && this.getStackInSlot(slot) != null) {
			EntityItem itemEntity = new EntityItem(this.worldObj, player.posX,
					player.posY, player.posZ);
			itemEntity.setEntityItemStack(this.decrStackSize(slot, amount));
			this.worldObj.spawnEntityInWorld(itemEntity);
			this.onInventoryChanged();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setBrewTime(int par1) {
		this.brewTime = par1;
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventory[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = null;

		if (this.inventory[slot] != null) {
			if (this.inventory[slot].stackSize <= amount) {
				stack = this.inventory[slot];
				this.inventory[slot] = null;
				this.onInventoryChanged();
			} else {
				stack = this.inventory[slot].splitStack(amount);

				if (this.inventory[slot].stackSize == 0) {
					this.inventory[slot] = null;
				}

				this.onInventoryChanged();
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = this.getStackInSlot(slot);
		if (stack != null) {
			this.setInventorySlotContents(slot, null);
		}

		return stack;
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed
	 * by automation on the given side of this block.
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return new int[] { 0, 1, 2, 3, 4 };
	}

	/**
	 * Returns true if automation can insert the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		if(slot == 4 && itemstack.getItem().isPotionIngredient(itemstack))//Input
			return true;
		if(slot != 4 && itemstack.getItem() instanceof ItemPotion || itemstack.getItem() == Items.glass_bottle)//Potion
			return true;
		return false;
	}
	/**
	 * Returns true if automation can extract the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		if(slot == 4 && (side == 0 || side == 1)/*DOWN*/)
			return true;
		if(slot == 4)
			return false;
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public String getInventoryName()
	{
		return "Brewing Pillar";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
}
