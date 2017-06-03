package T145.magistics.tiles.crafting;

import T145.magistics.api.MagisticsApiHelper;
import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.api.logic.IFacing;
import T145.magistics.api.magic.IQuintManager;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.init.ModItems;
import T145.magistics.init.ModRecipes;
import T145.magistics.init.ModSounds;
import T145.magistics.items.ItemShard;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageInfuserProgress;
import T145.magistics.tiles.MTileInventory;
import net.minecraft.block.state.IBlockState;
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
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class TileInfuser extends MTileInventory implements IQuintManager, IFacing, IInteractionObject {

	public float cookCost;
	public float cookTime;

	private final boolean dark;
	private int soundDelay;
	private int boost;
	private int boostDelay = 20;
	private int suction;
	private EnumFacing facing = EnumFacing.NORTH;

	public TileInfuser(boolean dark) {
		this.dark = dark;
	}

	public TileInfuser() {
		this(false);
	}

	public boolean isDark() {
		return dark;
	}

	public int getBoost() {
		return boost;
	}

	@Override
	public String getName() {
		return dark ? "tile.infuser.dark.name" : "tile.infuser.light.name";
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
		return dark ? "magistics:infuserdark" : "magistics:infuser";
	}

	@Override
	public boolean isHorizontalFacing() {
		return true;
	}

	@Override
	public EnumFacing getFacing(IBlockState state) {
		return facing;
	}

	@Override
	public void setFacing(IBlockState state, EnumFacing side) {
		facing = side;
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
	public void readPacketNBT(NBTTagCompound compound) {
		super.readPacketNBT(compound);
		facing = EnumFacing.getFront(compound.getInteger("Facing"));
		cookCost = compound.getFloat("CookCost");
		cookTime = compound.getFloat("CookTime");

		if (isCrafting()) {
			world.notifyBlockUpdate(pos, getState(), getState(), 1);
		}
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		super.writePacketNBT(compound);
		compound.setInteger("Facing", facing.getIndex());
		compound.setFloat("CookCost", cookCost);
		compound.setFloat("CookTime", cookTime);
	}

	public int getCookProgressScaled(int time) {
		return Math.round(cookTime / cookCost * time);
	}

	public int getBoostScaled() {
		return Math.round(0.1F + boost / 2F) * 6;
	}

	public boolean isCrafting() {
		return cookTime > 0F;
	}

	public void sendCraftingProgressPacket() {
		PacketHandler.INSTANCE.sendToAllAround(new MessageInfuserProgress(this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 64D));
	}

	@Override
	public void update() {
		setSuction(0);

		if (soundDelay > 0) {
			--soundDelay;
		}

		if (world.isRemote) {
			return;
		}

		if (isCrafting()) {
			sendCraftingProgressPacket();
		}

		InfuserRecipe infuserRecipe = ModRecipes.getMatchingInfuserRecipe(itemHandler.getStacks().toArray(new ItemStack[getSizeInventory()]), isDark());

		if (infuserRecipe != null && !world.isBlockPowered(pos)) {
			cookCost = infuserRecipe.getCost();

			if (canCraft(infuserRecipe) && QuintHelper.drainQuints(world, pos, cookCost, false) > 0F) {
				float quintDrain = Math.min(0.5F + 0.05F * boost, cookCost - cookTime + 0.01F);
				cookTime += QuintHelper.drainQuints(world, pos, quintDrain, true);

				if (soundDelay == 0 && cookTime > 0.025F) {
					// slightly discharge this chunk's aura

					world.playSound(null, pos, isDark() ? ModSounds.infuserdark : ModSounds.infuser, SoundCategory.MASTER, 0.2F, 1F);
					soundDelay = 62;
				}

				if (cookTime >= cookCost) {
					addProcessedItem(infuserRecipe);
					reset();
					refresh();
				}
			} else { // pause crafting
				hardReset();
			}
		} else {
			hardReset();
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
		} else if (!MagisticsApiHelper.areItemStacksEqual(result, itemHandler.getStackInSlot(0))) {
			return false;
		} else {
			int resultCount = itemHandler.getStackInSlot(0).getCount() + result.getCount();
			return resultCount <= itemHandler.getSlotLimit(0) && resultCount <= result.getMaxStackSize();
		}
	}

	private void addProcessedItem(InfuserRecipe infuserRecipe) {
		ItemStack result = infuserRecipe.getResult();
		ItemStack[] components = infuserRecipe.getComponents();

		if (itemHandler.getStackInSlot(0).isEmpty()) {
			itemHandler.setStackInSlot(0, result.copy());
		} else if (MagisticsApiHelper.areItemStacksEqual(itemHandler.getStackInSlot(0), result) && itemHandler.getStackInSlot(0).getCount() < result.getMaxStackSize()) {
			itemHandler.getStackInSlot(0).grow(result.getCount());
		}

		for (int slot = dark ? 1 : 2; slot < getSizeInventory(); ++slot) {
			if (!itemHandler.getStackInSlot(slot).isEmpty()) {
				for (ItemStack component : components) {
					if (MagisticsApiHelper.areItemStacksEqual(component, itemHandler.getStackInSlot(slot))) {
						itemHandler.getStackInSlot(slot).shrink(1);

						// fluid container compatibility

						if (!dark && component.getItem() instanceof ItemShard) {
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
	}

	private void hardReset() {
		if (isCrafting()) {
			world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1.6F);
			refresh();
		}

		reset();
	}
}