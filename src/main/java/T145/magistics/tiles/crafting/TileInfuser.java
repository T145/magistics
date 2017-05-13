package T145.magistics.tiles.crafting;

import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.api.logic.IFacing;
import T145.magistics.api.magic.IQuintManager;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.client.fx.FXCreator;
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

public class TileInfuser extends MTileInventory implements IInteractionObject, IFacing, IQuintManager {

	public float cookCost;
	public float cookTime;

	protected boolean active;
	protected boolean crafting;
	protected boolean enchanted;
	protected int angle;
	protected int soundDelay;
	protected int boost;
	protected int boostDelay = 20;
	protected int suction;
	protected EnumFacing facing = EnumFacing.SOUTH;

	public boolean isActive() {
		return active;
	}

	public boolean isCrafting() {
		return crafting;
	}

	public boolean isDark() {
		return false;
	}

	public boolean isDormant() {
		return !active && !crafting;
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
	public boolean canConnect(EnumFacing facing) {
		return facing != EnumFacing.UP;
	}

	@Override
	public int getSuction() {
		return suction;
	}

	@Override
	public void setSuction(int suction) {
		this.suction = suction;
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
	public String getName() {
		return "tile.infuser.light.name";
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName(), new Object[0]);
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerInfuser(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "magistics:infuser";
	}

	@Override
	public int getSizeInventory() {
		return 8;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, boolean simulate) {
		return slot > (isDark() ? 0 : 1);
	}

	@Override
	public boolean canExtractItem(int slot, int amount, boolean simulate) {
		return true;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		super.writePacketNBT(compound);

		compound.setInteger("Facing", facing.ordinal());
		compound.setFloat("CookCost", cookCost);
		compound.setFloat("CookTime", cookTime);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		super.readPacketNBT(compound);

		setFacing(EnumFacing.getFront(compound.getInteger("Facing")));
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
		if (isDormant()) {
			setSuction(0);
			angle = facing.ordinal();
		} else {
			angle = getCookProgressScaled(360);
		}

		if (soundDelay > 0) {
			--soundDelay;
		}

		if (boostDelay <= 0) {
			if (boost > 0) {
				--boost;
			}

			boostDelay = 20;
		} else {
			--boostDelay;
		}

		InfuserRecipe infuserRecipe = RecipeRegistry.getMatchingInfuserRecipe(itemHandler.getStacks().toArray(new ItemStack[getSizeInventory()]), isDark());

		if (active = hasWorld() && infuserRecipe != null && !world.isBlockPowered(pos)) {
			cookCost = infuserRecipe.getCost();

			if (crafting = canCraft(infuserRecipe) && QuintHelper.drainQuints(world, pos, cookCost, false) > 0F) {
				float quintDrain = Math.min(0.5F + 0.05F * boost, cookCost - cookTime + 0.01F);
				cookTime += QuintHelper.drainQuints(world, pos, quintDrain, true);

				if (soundDelay == 0 && cookTime > 0.025F) {
					// slightly discharge this chunk's aura

					world.playSound(null, pos, isDark() ? ModSounds.infuserDark : ModSounds.infuser, SoundCategory.MASTER, 0.2F, 1F);
					soundDelay = 62;
				}

				if (enchanted) {
					switch (world.rand.nextInt(4)) {
					case 0:
						FXCreator.INSTANCE.smallGreenFlameFX(world, pos.getX() + 0.1F, pos.getY() + 1.15F, pos.getZ() + 0.1F);
						break;
					case 1:
						FXCreator.INSTANCE.smallGreenFlameFX(world, pos.getX() + 0.1F, pos.getY() + 1.15F, pos.getZ() + 0.9F);
						break;
					case 2:
						FXCreator.INSTANCE.smallGreenFlameFX(world, pos.getX() + 0.9F, pos.getY() + 1.15F, pos.getZ() + 0.1F);
						break;
					case 3:
						FXCreator.INSTANCE.smallGreenFlameFX(world, pos.getX() + 0.9F, pos.getY() + 1.15F, pos.getZ() + 0.9F);
						break;
					}
				}

				if (cookTime >= cookCost) {
					addProcessedItem(infuserRecipe.getResult(), infuserRecipe.getComponents());
					reset();
					refresh();
				}
			}
		} else {
			if (crafting) {
				world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1.6F);
				refresh();
			}

			reset();
		}
	}

	public boolean canCraft(InfuserRecipe infuserRecipe) {
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
		active = false;
		crafting = false;
	}
}