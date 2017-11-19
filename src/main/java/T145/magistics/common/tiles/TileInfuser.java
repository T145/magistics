package T145.magistics.common.tiles;

import javax.annotation.Nonnull;

import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.api.magic.IQuintHandler;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.common.blocks.BlockInfuser.InfuserType;
import T145.magistics.common.network.PacketHandler;
import T145.magistics.common.network.client.MessageUpdateInfuser;
import T145.magistics.common.tiles.base.TileInventory;
import T145.magistics.core.ModInit;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileInfuser extends TileInventory implements ITickable, IQuintHandler {

	public static final EnumFacing[] VALID_SIDES = new EnumFacing[] { EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST };

	public float quintCost;
	public float progress;

	private InfuserType type;
	private EnumFacing front;
	private int suction;
	private int soundTicks;
	private int boost;
	private int boostTicks = 20;

	public TileInfuser(InfuserType type) {
		super(type.getInventorySize(), VALID_SIDES);
		this.type = type;
		this.front = EnumFacing.SOUTH;
	}

	public TileInfuser() {
		this(InfuserType.LIGHT);
	}

	public InfuserType getInfuserType() {
		return type;
	}

	public void setFront(EnumFacing facing) {
		this.front = facing;
	}

	public EnumFacing getFront() {
		return front;
	}

	public boolean isDark() {
		return type.isDark();
	}

	public int getBoost() {
		return boost;
	}

	public int getCookProgressScaled(int time) {
		return Math.round(progress / quintCost * time);
	}

	public int getBoostScaled() {
		return Math.round(0.1F + boost / 2F) * 6;
	}

	public boolean isCrafting() {
		return progress > 0F;
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return side != EnumFacing.UP;
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
	public boolean canInsertItem(int slot, ItemStack stack, @Nonnull ItemStack existing) {
		return slot > (isDark() ? 0 : 1);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setString("Type", type.toString());
		tag.setInteger("Facing", front.getIndex());
		tag.setInteger("Suction", suction);
		tag.setFloat("Progress", progress);
		tag.setFloat("QuintCost", quintCost);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		type = InfuserType.valueOf(tag.getString("Type"));
		front = EnumFacing.getFront(tag.getInteger("Facing"));
		suction = tag.getInteger("Suction");
		progress = tag.getFloat("Progress");
		quintCost = tag.getFloat("QuintCost");
	}

	public void updateInfuserProgress() {
		PacketHandler.sendToAllAround(new MessageUpdateInfuser(this), world, pos);
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		if (soundTicks > 0) {
			--soundTicks;
		}

		if (isCrafting()) {
			updateInfuserProgress();
		} else {
			setSuction(0);
		}

		/*
		 * TODO: Recipe search is O(n^3), so it should be:
		 * - optimized to at least O(n^2), which is possible w/ order-sensitive recipes
		 * - executed at best when the inventory updates
		 * - executed at worst all the time or every so often
		 */
		InfuserRecipe recipe = RecipeRegistry.getMatchingInfuserRecipe(getHandle().getStacks(), isDark());

		if (!world.isBlockPowered(pos) && canCraft(recipe)) {
			progress += QuintHelper.fill(this, Math.min(0.5F + 0.05F * boost, quintCost - progress + 0.01F), true);

			if (soundTicks == 0 && progress > 0.025F) {
				soundTicks = 62;
				// discharge chunk aura slightly
				world.playSound(null, pos, isDark() ? ModInit.SOUND_INFUSER_DARK : ModInit.SOUND_INFUSER, SoundCategory.MASTER, 0.2F, 1F);
			}

			if (progress >= quintCost) {
				softReset();
				addResult(recipe);
			}
		} else {
			hardReset();
		}

		/*if (boostTicks <= 0 || boostTicks == 10) {
			// discharge chunk aura slightly
			if (AuraChunk != null && boost < 10 && AuraChunk.boost > 0) {
				++boost;
				--AuraChunk.boost;
			}
		}*/

		if (boostTicks <= 0) {
			if (boost > 0) {
				--boost;
			}

			boostTicks = 20;
		} else {
			--boostTicks;
		}
	}

	public boolean canCraft(InfuserRecipe recipe) {
		if (recipe == null) {
			return false;
		}

		quintCost = recipe.getCost();

		if (!(QuintHelper.fill(this, quintCost, false) > 0F)) {
			return false;
		}

		ItemStack result = recipe.getResult();

		if (result.isEmpty()) {
			return false;
		} else if (getHandle().getStackInSlot(0).isEmpty()) {
			return true;
		} else if (!RecipeRegistry.areItemStacksEqual(result, getHandle().getStackInSlot(0))) {
			return false;
		} else {
			int resultCount = getHandle().getStackInSlot(0).getCount() + result.getCount();
			return resultCount <= getHandle().getSlotLimit(0) && resultCount <= result.getMaxStackSize();
		}
	}

	private void addResult(InfuserRecipe invRecipe) {
		ItemStack result = invRecipe.getResult();
		ItemStack[] recipe = invRecipe.getComponents();

		if (getHandle().getStackInSlot(0).isEmpty()) {
			getHandle().setStackInSlot(0, result.copy());
		} else if (RecipeRegistry.areItemStacksEqual(getHandle().getStackInSlot(0), result) && getHandle().getStackInSlot(0).getCount() < result.getMaxStackSize()) {
			getHandle().getStackInSlot(0).grow(result.getCount());
		}

		for (int slot = isDark() ? 1 : 2; slot < getInventorySize(); ++slot) {
			if (!getHandle().getStackInSlot(slot).isEmpty()) {
				for (ItemStack ingredient : recipe) {
					if (RecipeRegistry.areItemStacksEqual(ingredient, getHandle().getStackInSlot(slot))) {
						getHandle().getStackInSlot(slot).shrink(1);

						// fluid container compatibility

						/*if (!isDark() && ingredient.getItem() instanceof ItemShard) {
							if (getHandle().getStackInSlot(1).isEmpty()) {
								getHandle().setStackInSlot(1, new ItemStack(ModInit.CRYSTAL_SHARD, 1, 0));
							} else {
								getHandle().getStackInSlot(1).grow(1);
							}
						}*/

						if (getHandle().getStackInSlot(slot).isEmpty()) {
							getHandle().setStackInSlot(slot, ItemStack.EMPTY);
						}
					}
				}
			}
		}
	}

	private void softReset() {
		progress = 0;
		quintCost = 0;
		updateInfuserProgress();
	}

	private void hardReset() {
		if (isCrafting()) {
			world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1.6F);
		}

		softReset();
	}
}