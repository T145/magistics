package T145.magistics.common.tiles.craftingpillars;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import T145.magistics.api.FreezerRecipes;
import T145.magistics.client.lib.craftingpillars.Blobs;
import cpw.mods.fml.client.FMLClientHandler;

public class TilePillarFreezer extends TileBase implements IInventory, ISidedInventory, IFluidHandler {
	public int freezingTime, prevLightValue = 0;
	public boolean showNum = false, empty = true;
	public ItemStack[] inventory = new ItemStack[getSizeInventory()];
	public final FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	public List<Blobs> blobs;

	public TilePillarFreezer() {
		blobs = new ArrayList<Blobs>();
	}

	public void addBlob() {
		blobs.add(new Blobs(rand.nextInt(12) + 2.5F, rand.nextInt(9) + 4.5F, rand.nextInt(12) + 2.5F, 4));
	}

	public void removeBlob() {
		blobs.remove(rand.nextInt(blobs.size()));
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			int lightValue = getFluidLightLevel();
			if (prevLightValue != lightValue) {
				prevLightValue = lightValue;
				worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
			}

			EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
			if ((player.posX - xCoord) * (player.posX - xCoord) + (player.posY - yCoord) * (player.posY - yCoord) + (player.posZ - zCoord) * (player.posZ - zCoord) < 128) {
				while (blobs.size() < tank.getFluidAmount() / FluidContainerRegistry.BUCKET_VOLUME)
					addBlob();
				while (blobs.size() > tank.getFluidAmount() / FluidContainerRegistry.BUCKET_VOLUME)
					removeBlob();

				for (int i = 0; i < blobs.size(); i++)
					blobs.get(i).update(0.1F);
			}
		}
		if (canFreeze()) {
			if (freezingTime > 0)
				freezingTime--;
			else
				freezeFluid();
		} else
			freezingTime = 150;
		super.updateEntity();
	}

	public boolean canFreeze() {
		if (empty)
			return false;
		if (tank.getFluid() == null)
			return false;
		if (tank.getFluidAmount() >= FluidContainerRegistry.BUCKET_VOLUME) {
			if (inventory[0] == null)
				return true;
			if (inventory[0].stackSize < getInventoryStackLimit()) {
				ItemStack res = FreezerRecipes.getResultForFluid(tank.getFluid());
				if (res != null && inventory[0].isItemEqual(res))
					return true;
			}
		}
		return false;
	}

	private void freezeFluid() {
		freezingTime = 150;

		if (!worldObj.isRemote) {
			ItemStack itemstack = FreezerRecipes.getResultForFluid(tank.getFluid());
			if (inventory[0] == null)
				inventory[0] = itemstack.copy();
			else if (inventory[0].isItemEqual(itemstack))
				inventory[0].stackSize += itemstack.stackSize;

			drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.BUCKET_VOLUME, true);
			if (tank.getFluidAmount() <= 0)
				empty = true;
			else
				empty = false;
		}
		onInventoryChanged();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey("tank"))
			tank.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("tank")));
		inventory = new ItemStack[getSizeInventory()];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);
		for (int i = 0; i < nbtlist.tagCount(); i++) {
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if ((j >= 0) && (j < getSizeInventory()))
				inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}

		freezingTime = nbt.getInteger("freezingTime");
		showNum = nbt.getBoolean("showNum");
		empty = nbt.getBoolean("isEmpty");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (tank.getFluid() != null)
			nbt.setTag("tank", tank.getFluid().writeToNBT(new NBTTagCompound()));
		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++)
			if (inventory[i] != null) {
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		nbt.setTag("Items", nbtlist);

		nbt.setInteger("freezingTime", freezingTime);
		nbt.setBoolean("showNum", showNum);
		nbt.setBoolean("isEmpty", empty);
	}

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player) {
		if (!worldObj.isRemote && getStackInSlot(slot) != null) {
			EntityItem itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(decrStackSize(slot, amount));
			worldObj.spawnEntityInWorld(itemEntity);

			onInventoryChanged();
		}
	}

	@Override
	public int getSizeInventory() {
		return 1;
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public String getInventoryName() {
		return "Freezer Pillar";
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
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return true;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (!canFill(from, resource.getFluid()))
			return 0;
		if (doFill && resource.amount > 0)
			empty = false;
		int res = tank.fill(resource, doFill);
		onInventoryChanged();
		return res;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack res = tank.drain(maxDrain, doDrain);
		if (tank.getFluidAmount() <= 0)
			empty = true;
		onInventoryChanged();
		return res;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (tank.getFluid() == null)
			return FreezerRecipes.getResultForFluid(fluid) != null;
		return tank.getFluid().isFluidEqual(new FluidStack(fluid, 1));
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	public int getFluidLightLevel() {
		if (empty)
			return 0;
		FluidStack tankFluid = tank.getFluid();
		return tankFluid == null ? 0 : tankFluid.getFluid().getLuminosity(tankFluid);
	}
}