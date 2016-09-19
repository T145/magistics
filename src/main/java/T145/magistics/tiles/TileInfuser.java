package T145.magistics.tiles;

import T145.magistics.api.MagisticsApi;
import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.api.tiles.IFacing;
import T145.magistics.api.tiles.IOwned;
import T145.magistics.items.ItemShardDull;
import T145.magistics.lib.utils.InventoryHelper;
import T145.magistics.lib.utils.PlayerUtils;
import T145.magistics.tiles.core.TileMagisticsInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.events.EssentiaHandler;
import thaumcraft.common.lib.utils.InventoryUtils;

public class TileInfuser extends TileMagisticsInventory implements IFacing, IOwned, ISidedInventory, IAspectContainer, IWandable {
	public TileInfuser() {
		super(8, true, true);
	}

	protected AspectList recipeEssentia = new AspectList();

	private boolean active = false;
	private boolean crafting = false;

	public int infuserCookTime;
	public int infuserBurnTime;
	public int boost = 0;

	protected int angle = 0;
	protected int soundDelay = 0;
	private int boostDelay = 20;

	public boolean isActive() {
		return active;
	}

	public boolean isCrafting() {
		return crafting;
	}

	public boolean isDormant() {
		return !active && !crafting;
	}

	public int getDiskAngle() {
		return angle;
	}

	public boolean isDark() {
		return false;
	}

	public boolean hasConnectedSide(int side) {
		return false;
	}

	@Override
	public EntityPlayer getOwner() {
		return PlayerUtils.findPlayerByUUID(ownerUUID);
	}

	@Override
	public void setOwner(EntityPlayer player) {
		ownerName = player.getCommandSenderName();
		ownerUUID = player.getUniqueID();
	}

	@Override
	public boolean isOwned() {
		return ownerName.length() > 0;
	}

	@Override
	public boolean isOwnedBy(EntityPlayer player) {
		return player.getUniqueID().equals(ownerUUID);
	}

	@Override
	public int getFacing() {
		return facing;
	}

	@Override
	public void setFacing(int dir) {
		facing = dir;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		super.readCustomNBT(tag);
		active = tag.getBoolean("active");
		crafting = tag.getBoolean("crafting");
		recipeEssentia.readFromNBT(tag);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		super.writeCustomNBT(tag);
		tag.setBoolean("active", active);
		tag.setBoolean("crafting", crafting);
		recipeEssentia.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		infuserBurnTime = tag.getShort("BurnTime");
		infuserCookTime = tag.getShort("CookTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setShort("BurnTime", (short) infuserBurnTime);
		tag.setShort("CookTime", (short) infuserCookTime);
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int time) {
		return infuserBurnTime > 0 ? (infuserCookTime * time) / infuserBurnTime : 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBoostScaled() {
		return Math.round(0.1F + (float) boost / 2F) * 6;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (soundDelay > 0) {
			--soundDelay;
		}

		angle = infuserBurnTime > 0 ? (infuserCookTime * 360) / infuserBurnTime : facing;

		if (hasWorldObj()) {
			InfuserRecipe recipe = MagisticsApi.getMatchingInfuserRecipe(getOwner(), inventoryStacks, isDark());
			boolean cooked = false;
			boolean isCooking = infuserCookTime > 0;

			if (canProcess(recipe) && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				if (isDormant()) {
					setAspects(recipe.getAspects().copy());
				}

				active = true;

				if (crafting) {
					if (infuserCookTime == 0) {
						infuserBurnTime = recipe.getBurnTime();
						markDirty();
					}

					if (soundDelay == 0) {
						worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5, zCoord + 0.5F, isDark() ? "magistics:infuserdark" : "magistics:infuser", 0.2F, 1F);
						soundDelay = 62;
					}

					++infuserCookTime;

					if (infuserCookTime >= infuserBurnTime && infuserCookTime > 0) {
						addProcessedItem(recipe.getResult(), recipe.getIngredients());
						resetInfuser();
					}
				} else {
					if (recipeEssentia.visSize() > 0) {
						for (Aspect aspect : recipeEssentia.getAspects()) {
							if (recipeEssentia.getAmount(aspect) > 0) {
								if (EssentiaHandler.drainEssentia(this, aspect, ForgeDirection.UP, 6)) {
									recipeEssentia.reduce(aspect, 1);
									worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
									markDirty();
									return;
								}
							}
						}
						return;
					} else {
						recipeEssentia = new AspectList();
						crafting = true;
					}
				}

				if (infuserCookTime > 0 != isCooking) {
					markDirty();
				}

				if (boostDelay <= 0 || boostDelay == 10) {
					// special negative effects to the player and/or environment here?
				}

				if (boostDelay <= 0) {
					if (boost > 0) {
						--boost;
					}
					boostDelay = 20;
				} else {
					--boostDelay;
				}
			} else {
				if (crafting) {
					worldObj.playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, "random.fizz", 1F, 1.6F);
				}

				resetInfuser();
			}
		}
	}

	private void resetInfuser() {
		infuserBurnTime = 0;
		infuserCookTime = 0;
		recipeEssentia = new AspectList();
		crafting = false;
		active = false;
	}

	protected void addProcessedItem(ItemStack result, ItemStack[] recipe) {
		if (inventoryStacks[0] == null) {
			inventoryStacks[0] = result.copy();
		} else if (inventoryStacks[0].isItemEqual(result) && inventoryStacks[0].stackSize < result.getMaxStackSize()) {
			inventoryStacks[0].stackSize += result.stackSize;
		}

		for (int slot = isDark() ? 1 : 2; slot < getSizeInventory(); ++slot) {
			if (inventoryStacks[slot] != null) {
				for (ItemStack ingredient : recipe) {
					if (InventoryUtils.areItemStacksEqualStrict(ingredient, inventoryStacks[slot])) {
						if (!isDark() && inventoryStacks[slot].getItem() == ConfigItems.itemShard && worldObj.rand.nextBoolean()) {
							ItemStack dullShard = new ItemStack(ItemShardDull.INSTANCE);

							if (inventoryStacks[1] == null) {
								inventoryStacks[1] = dullShard;
							} else if (inventoryStacks[1].isItemEqual(dullShard) && inventoryStacks[1].stackSize < 64) {
								++inventoryStacks[1].stackSize;
							} else {
								InventoryHelper.dropStack(inventoryStacks[1], worldObj, xCoord, yCoord, zCoord);
							}
						}

						--inventoryStacks[slot].stackSize;

						if (inventoryStacks[slot].stackSize == 0) {
							inventoryStacks[slot] = null;
						}
					}
				}
			}
		}
	}

	protected boolean canProcess(InfuserRecipe recipe) {
		if (recipe == null) {
			return false;
		} else {
			ItemStack result = recipe.getResult();

			if (result == null) {
				return false;
			} else if (inventoryStacks[0] == null) {
				return true;
			} else if (!inventoryStacks[0].isItemEqual(result)) {
				return false;
			} else {
				int stackSize = inventoryStacks[0].stackSize + result.stackSize;
				return stackSize <= getInventoryStackLimit() && stackSize <= result.getMaxStackSize();
			}
		}
	}

	@Override
	public String getInventoryName() {
		return "Infuser";
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return isDark() ? slot > 0 : slot > 1;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
		case 0: case 1:
			return new int[] {};
		default:
			return isDark() ? new int[] { 0, 1, 2, 3, 4, 5 } : new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return !isItemValidForSlot(slot, stack);
	}

	@Override
	public int addToContainer(Aspect aspect, int amount) {
		if (doesContainerAccept(aspect)) {
			recipeEssentia.add(aspect, amount);
			return amount;
		} else {
			return 0;
		}
	}

	@Override
	public int containerContains(Aspect aspect) {
		return recipeEssentia.getAmount(aspect) > 0 ? 1 : 0;
	}

	@Override
	public boolean doesContainerAccept(Aspect aspect) {
		return true;
	}

	@Override
	@Deprecated
	public boolean doesContainerContain(AspectList aspects) {
		return false;
	}

	public boolean hasEssentia() {
		return recipeEssentia.size() > 0;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect aspect, int amount) {
		return recipeEssentia.getAmount(aspect) == amount;
	}

	@Override
	public AspectList getAspects() {
		return recipeEssentia;
	}

	@Override
	public void setAspects(AspectList aspects) {
		recipeEssentia = aspects;
	}

	@Override
	@Deprecated
	public boolean takeFromContainer(AspectList aspects) {
		return false;
	}

	@Override
	public boolean takeFromContainer(Aspect aspect, int amount) {
		return recipeEssentia.reduce(aspect, amount);
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
		if (isDormant() && player.isSneaking() && isOwnedBy(player)) {
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
			player.swingItem();
			return 0;
		}

		return -1;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
		return wandstack;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
}