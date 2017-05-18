package T145.magistics.tiles.crafting;

import T145.magistics.Magistics;
import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.api.logic.IFacing;
import T145.magistics.api.magic.IQuintManager;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.init.ModItems;
import T145.magistics.init.ModSounds;
import T145.magistics.items.ItemShard;
import T145.magistics.tiles.MTileInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;

public class TileInfuser extends MTileInventory implements IQuintManager, IFacing, IInteractionObject {

	public float cookCost;
	public float cookTime;

	private final boolean dark;
	private boolean crafting;
	private boolean upgraded;
	private int angle;
	private int soundDelay;
	private int boost;
	private int boostDelay = 20;
	private int suction;
	private EnumFacing facing = EnumFacing.NORTH;

	public TileInfuser(boolean dark) {
		this.dark = dark;
	}

	public boolean isDark() {
		return dark;
	}

	public boolean isCrafting() {
		return crafting;
	}

	public float getDiskAngle() {
		return angle;
	}

	public int getBoost() {
		return boost;
	}

	public void setBoost() {
		if (boostDelay <= 0 || boostDelay == 10) {
			if (boost < 10) {
				++boost;
			}
		}
	}

	@Override
	public String getName() {
		return "tile.infuser." + (dark ? "dark" : "light") + ".name";
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName(), new Object[0]);
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerInfuser(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return Magistics.MODID + ":infuser" + (dark ? "_dark" : "");
	}

	@Override
	public EnumFacing getFacing() {
		return facing;
	}

	@Override
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}

	@Override
	public boolean canConnect(EnumFacing facing) {
		return facing != EnumFacing.UP;
	}

	@Override
	public int getSuction() {
		return suction;
	}

	@Override
	public void setSuction(int pressure) {
		suction = pressure;
	}

	@Override
	public int getSizeInventory() {
		return dark ? 6 : 8;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, boolean simulate) {
		return slot > (dark ? 0 : 1);
	}

	@Override
	public boolean canExtractItem(int slot, int amount, boolean simulate) {
		return true;
	}

	@Override
	public void superWritePacketNBT(NBTTagCompound compound) {
		compound.setString("Facing", facing.toString());
		compound.setFloat("CookCost", cookCost);
		compound.setFloat("CookTime", cookTime);
	}

	@Override
	public void superReadPacketNBT(NBTTagCompound compound) {
		facing = EnumFacing.valueOf(compound.getString("Facing"));
		cookCost = compound.getFloat("CookCost");
		cookTime = compound.getFloat("CookTime");
	}

	public int getCookProgressScaled(int time) {
		return Math.round(cookTime / cookCost * time);
	}

	public int getBoostScaled() {
		return Math.round(0.1F + boost / 2F) * 6;
	}

	@Override
	public void update() {
		setSuction(0);

		angle = getCookProgressScaled(360);
		crafting = cookTime > 0 && cookTime > 0;

		if (!world.isRemote && !world.isBlockPowered(pos)) {
			InfuserRecipe infuserRecipe = RecipeRegistry.getMatchingInfuserRecipe(itemHandler.getStacks().toArray(new ItemStack[getSizeInventory()]), dark);

			if (canCraft(infuserRecipe)) {
				cookCost = infuserRecipe.getCost();

				if (QuintHelper.drainQuints(world, pos, cookCost, false) > 0F) {
					float quintDrain = Math.min(0.5F + 0.05F * boost, cookCost - cookTime + 0.01F);
					cookTime += QuintHelper.drainQuints(world, pos, quintDrain, true);

					if (soundDelay == 0 && cookTime > 0.025F) {
						// slightly discharge this chunk's aura

						world.playSound(null, pos, isDark() ? ModSounds.infuserDark : ModSounds.infuser, SoundCategory.MASTER, 0.2F, 1F);
						soundDelay = 62;
					}

					if (cookTime >= cookCost) {
						addProcessedItem(infuserRecipe.getResult(), infuserRecipe.getComponents());
						reset();
						refresh();
					}
				}
			}

			if (boostDelay <= 0) {
				if (boost > 0) {
					--boost;
				}

				boostDelay = 20;
			} else {
				--boostDelay;
			}
		} else { // can optimize?
			if (crafting) {
				world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1.6F);
				refresh();
			}

			reset();
		}
	}

	public boolean canCraft(InfuserRecipe infuserRecipe) {
		if (infuserRecipe == null) {
			return false;
		}

		ItemStack result = infuserRecipe.getResult();

		if (result.isEmpty()) {
			return false;
		} else if (itemHandler.getStackInSlot(0).isEmpty()) {
			return true;
		} else if (!RecipeRegistry.areItemStacksEqual(result, itemHandler.getStackInSlot(0))) {
			return false;
		} else {
			int resultCount = itemHandler.getStackInSlot(0).getCount() + result.getCount();
			return resultCount <= itemHandler.getSlotLimit(0) && resultCount <= result.getMaxStackSize();
		}
	}

	private void addProcessedItem(ItemStack result, ItemStack[] components) {
		if (itemHandler.getStackInSlot(0).isEmpty()) {
			itemHandler.setStackInSlot(0, result.copy());
		} else if (RecipeRegistry.areItemStacksEqual(itemHandler.getStackInSlot(0), result) && itemHandler.getStackInSlot(0).getCount() < result.getMaxStackSize()) {
			itemHandler.getStackInSlot(0).grow(result.getCount());
		}

		for (int slot = isDark() ? 1 : 2; slot < getSizeInventory(); ++slot) {
			if (!itemHandler.getStackInSlot(slot).isEmpty()) {
				for (ItemStack component : components) {
					if (RecipeRegistry.areItemStacksEqual(component, itemHandler.getStackInSlot(slot))) {
						itemHandler.getStackInSlot(slot).shrink(1);

						// fluid container compatibility

						if (!isDark() && component.getItem() instanceof ItemShard) {
							if (itemHandler.getStackInSlot(1).isEmpty()) {
								itemHandler.setStackInSlot(1, new ItemStack(ModItems.crystalShard, 1, 0));
							} else {
								itemHandler.getStackInSlot(1).grow(1);
							}
						}

						if (itemHandler.getStackInSlot(slot).isEmpty()) {
							itemHandler.setStackInSlot(slot, ItemStack.EMPTY);
						}
					}
				}
			}
		}
	}

	private void reset() {
		cookCost = 0F;
		cookTime = 0F;
		crafting = false;
	}
}