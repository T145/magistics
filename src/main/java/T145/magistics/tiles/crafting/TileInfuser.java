package T145.magistics.tiles.crafting;

import javax.annotation.Nonnull;

import T145.magistics.Magistics;
import T145.magistics.api.MagisticsApi;
import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.api.logic.IFacing;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.blocks.crafting.BlockInfuser.InfuserType;
import T145.magistics.core.Init;
import T145.magistics.items.ItemShard;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageInfuserProgress;
import T145.magistics.tiles.base.TileInventory;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class TileInfuser extends TileInventory implements ITickable, IQuintContainer, IFacing {

	public static final EnumFacing[] VALID_SIDES = new EnumFacing[] { EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST };

	public float quintCost;
	public float progress;

	private InfuserType type;
	private EnumFacing front;
	private float quints;
	private int suction;
	private short updateTicks;
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

	public boolean isFull() {
		return quints == getCapacity();
	}

	public boolean isEmpty() {
		return quints == 0;
	}

	public boolean canConnect(EnumFacing facing) {
		return facing != EnumFacing.UP;
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

	public void sendCraftingProgressPacket() {
		PacketHandler.INSTANCE.sendToAllAround(new MessageInfuserProgress(this), PacketHandler.getTargetPoint(world, pos));
	}

	@Override
	public boolean isHorizontalFacing() {
		return true;
	}

	@Override
	public EnumFacing getFacing() {
		return front;
	}

	@Override
	public void setFacing(EnumFacing side) {
		front = side;
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return side != EnumFacing.UP;
	}

	@Override
	public float getQuints() {
		return quints;
	}

	@Override
	public void setQuints(float quints) {
		this.quints = quints;
	}

	@Override
	public float getCapacity() {
		return 250.0F;
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
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setString("Type", type.toString());
		nbt.setInteger("Facing", front.getIndex());
		nbt.setInteger("Suction", suction);
		nbt.setFloat("Quints", quints);
		nbt.setFloat("Progress", progress);
		nbt.setFloat("QuintCost", quintCost);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		type = InfuserType.valueOf(nbt.getString("Type"));
		front = EnumFacing.getFront(nbt.getInteger("Facing"));
		suction = nbt.getInteger("Suction");
		quints = nbt.getFloat("Quints");
		progress = nbt.getFloat("Progress");
		quintCost = nbt.getFloat("QuintCost");
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		setSuction(0);

		if (soundTicks > 0) {
			--soundTicks;
		}

		++updateTicks;

		if (isCrafting()) {
			sendCraftingProgressPacket();
		}

		if (!isFull()) {
			MathHelper.clamp(quints += QuintHelper.fillWithQuints(world, pos, 0.5F + 0.05F * boost, true), 0, getCapacity());
			Magistics.LOG.info("Quints: " + quints);
		}

		/*
		 * TODO: Recipe search is O(n^3), so it should be:
		 * - optimized to at least O(n^2), which is possible w/ order-sensitive recipes
		 * - executed at best when the inventory updates
		 * - executed at worst all the time or every so often
		 */

		InfuserRecipe recipe = MagisticsApi.getMatchingInfuserRecipe(getItemHandler().getStacks(), isDark());

		if (!world.isBlockPowered(pos) && canCraft(recipe)) {
			quintCost = recipe.getCost();
			progress += QuintHelper.drainQuints(this, Math.min(0.5F + 0.05F * boost, quintCost - progress + 0.01F), true);

			if (soundTicks == 0 && progress > 0.025F) {
				soundTicks = 62;
				// discharge chunk aura slightly
				world.playSound(null, pos, isDark() ? Init.SOUND_INFUSER_DARK : Init.SOUND_INFUSER, SoundCategory.MASTER, 0.2F, 1F);
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

		ItemStack result = recipe.getResult();

		if (result.isEmpty()) {
			return false;
		} else if (getItemHandler().getStackInSlot(0).isEmpty()) {
			return true;
		} else if (!MagisticsApi.areItemStacksEqual(result, getItemHandler().getStackInSlot(0))) {
			return false;
		} else {
			int resultCount = getItemHandler().getStackInSlot(0).getCount() + result.getCount();
			return resultCount <= getItemHandler().getSlotLimit(0) && resultCount <= result.getMaxStackSize();
		}
	}

	private void addResult(InfuserRecipe invRecipe) {
		ItemStack result = invRecipe.getResult();
		ItemStack[] recipe = invRecipe.getComponents();

		if (handle.getStackInSlot(0).isEmpty()) {
			handle.setStackInSlot(0, result.copy());
		} else if (MagisticsApi.areItemStacksEqual(handle.getStackInSlot(0), result) && handle.getStackInSlot(0).getCount() < result.getMaxStackSize()) {
			handle.getStackInSlot(0).grow(result.getCount());
		}

		for (int slot = isDark() ? 1 : 2; slot < getInventorySize(); ++slot) {
			if (!handle.getStackInSlot(slot).isEmpty()) {
				for (ItemStack ingredient : recipe) {
					if (MagisticsApi.areItemStacksEqual(ingredient, handle.getStackInSlot(slot))) {
						handle.getStackInSlot(slot).shrink(1);

						// fluid container compatibility

						if (!isDark() && ingredient.getItem() instanceof ItemShard) {
							if (handle.getStackInSlot(1).isEmpty()) {
								handle.setStackInSlot(1, new ItemStack(Init.CRYSTAL_SHARD, 1, 0));
							} else {
								handle.getStackInSlot(1).grow(1);
							}
						}

						if (handle.getStackInSlot(slot).isEmpty()) {
							handle.setStackInSlot(slot, ItemStack.EMPTY);
						}
					}
				}
			}
		}
	}

	private void softReset() {
		progress = 0;
		quintCost = 0;
		markForUpdate();
	}

	private void hardReset() {
		if (isCrafting()) {
			world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1.6F);
		}

		softReset();
	}
}