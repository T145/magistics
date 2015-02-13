package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.containers.ContainerCraftingPillar;

public class TilePillarCrafting extends TileBase implements IInventory, ISidedInventory {
	public ContainerCraftingPillar container = new ContainerCraftingPillar();
	private ItemStack[] inventory = new ItemStack[getSizeInventory() + 2];

	public float rot = 0F;
	public boolean showNum = false;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			rot += 0.1F;
			if (rot >= 360F)
				rot -= 360F;
		}

		super.updateEntity();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		inventory = new ItemStack[getSizeInventory() + 2];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);

		for (int i = 0; i < nbtlist.tagCount(); i++) {
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if ((j >= 0) && (j < getSizeInventory() + 2))
				inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}
		showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		NBTTagList nbtlist = new NBTTagList();

		for (int i = 0; i < getSizeInventory() + 2; i++) {
			if (inventory[i] != null) {
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}

		nbt.setTag("Items", nbtlist);
		nbt.setBoolean("showNum", showNum);
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();

		if (!worldObj.isRemote) {
			rotateCraftingGrid();
			inventory[getSizeInventory()] = CraftingManager.getInstance().findMatchingRecipe(container.craftMatrix, worldObj);
			Magistics.proxy.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 64);
		}
	}

	public void rotateCraftingGrid() {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				switch (getBlockMetadata()) {
				case 0:
					container.craftMatrix.setInventorySlotContents(8 - k * 3 - i, getStackInSlot(i * 3 + k));
					break;
				case 1:
					container.craftMatrix.setInventorySlotContents(i * 3 + k, getStackInSlot(i * 3 + k));
					break;
				case 2:
					container.craftMatrix.setInventorySlotContents(k * 3 + i, getStackInSlot(i * 3 + k));
					break;
				default:
					container.craftMatrix.setInventorySlotContents(8 - i * 3 - k, getStackInSlot(i * 3 + k));
					break;
				}
			}
		}
	}

	public void craftItem(EntityPlayer player) {
		if (!worldObj.isRemote) {
			EntityItem itemCrafted = new EntityItem(worldObj, xCoord + 0.5D, yCoord + 1.5D, zCoord + 0.5D, inventory[getSizeInventory()]);
			itemCrafted.motionX = rand.nextDouble() / 4 - 0.125D;
			itemCrafted.motionZ = rand.nextDouble() / 4 - 0.125D;
			itemCrafted.motionY = rand.nextDouble() / 4;
			worldObj.spawnEntityInWorld(itemCrafted);

			EntityItem itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(inventory[getSizeInventory()]);
			worldObj.spawnEntityInWorld(itemEntity);
			onCrafting(player, inventory[getSizeInventory()]);

			for (int i = 0; i < getSizeInventory(); i++) {
				ItemStack itemstack1 = getStackInSlot(i);

				if (itemstack1 != null) {
					decrStackSize(i, 1);

					if (itemstack1.getItem().hasContainerItem()) {
						ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);

						if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage()) {
							MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, itemstack2));
							itemstack2 = null;
						}

						if (itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !player.inventory.addItemStackToInventory(itemstack2))) {
							if (getStackInSlot(i) == null) {
								setInventorySlotContents(i, itemstack2);
							} else {
								itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
								itemEntity.setEntityItemStack(itemstack2);
								worldObj.spawnEntityInWorld(itemEntity);
							}
						}
					}
				}
			}
		}
	}

	public void onCrafting(EntityPlayer player, ItemStack is) {
		is.onCrafting(player.worldObj, player, is.stackSize);

		if (is.getItem() == Item.getItemFromBlock(Blocks.crafting_table))
			player.addStat(AchievementList.buildWorkBench, 1);

		if (is.getItem() instanceof ItemPickaxe)
			player.addStat(AchievementList.buildPickaxe, 1);

		if (is.getItem() == Item.getItemFromBlock(Blocks.furnace))
			player.addStat(AchievementList.buildFurnace, 1);

		if (is.getItem() instanceof ItemHoe)
			player.addStat(AchievementList.buildHoe, 1);

		if (is.getItem() == Items.bread)
			player.addStat(AchievementList.makeBread, 1);

		if (is.getItem() == Items.cake)
			player.addStat(AchievementList.bakeCake, 1);

		if (is.getItem() instanceof ItemPickaxe && ((ItemPickaxe) is.getItem()).func_150913_i() != Item.ToolMaterial.WOOD)
			player.addStat(AchievementList.buildBetterPickaxe, 1);

		if (is.getItem() instanceof ItemSword)
			player.addStat(AchievementList.buildSword, 1);

		if (is.getItem() == Item.getItemFromBlock(Blocks.enchanting_table))
			player.addStat(AchievementList.enchantments, 1);

		if (is.getItem() == Item.getItemFromBlock(Blocks.bookshelf))
			player.addStat(AchievementList.bookcase, 1);

		if (is.getItem() == Item.getItemFromBlock(ConfigObjects.blockCraftingPillar))
			player.addStat(ConfigObjects.achievementRecursion, 1);
	}

	public void dropItemFromSlot(int slot, EntityPlayer player) {
		if (!worldObj.isRemote && getStackInSlot(slot) != null) {
			EntityItem itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(decrStackSize(slot, 1));
			worldObj.spawnEntityInWorld(itemEntity);
			onInventoryChanged();
		}
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is) {
		inventory[slot] = is;

		if (is != null && is.stackSize > getInventoryStackLimit())
			is.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = null;

		if (inventory[slot] != null)
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public String getInventoryName() {
		return "Crafting Pillar";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return super.isUseableByPlayer(player);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return slot == 10 && itemstack.getItem() == ConfigObjects.itemWandThaumcraft;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] { 10 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return isItemValidForSlot(slot, itemstack);
	}
}