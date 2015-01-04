package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;
import T145.magistics.common.config.ModBlocks;

import com.pahimar.ee3.tileentity.TileEntityAlchemicalChest;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class TileChestHungryAlchemical extends TileEntityAlchemicalChest implements IWandable {
	public TileChestHungryAlchemical() {
		super(0);
	}

	public TileChestHungryAlchemical(int meta) {
		super(meta);
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 2:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return false;
		}
	}

	public boolean upgradeChest(int upgradeMetadata) {
		if (upgradeMetadata > getBlockMetadata()) {
			if (numUsingPlayers > 0)
				return false;

			TileChestHungryAlchemical newChest = (TileChestHungryAlchemical) ModBlocks.blockChestHungryAlchemical.createTileEntity(worldObj, upgradeMetadata);
			newChest.setOrientation(getOrientation());

			for (int slot = 0; slot < getSizeInventory(); slot++) {
				newChest.setInventorySlotContents(slot, getStackInSlot(slot));
				setInventorySlotContents(slot, null);
			}

			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, upgradeMetadata, 3);
			worldObj.setTileEntity(xCoord, yCoord, zCoord, newChest);

			return true;
		}
		return false;
	}

	public void fixState(byte state) {
		ItemStack[] inventory = new ItemStack[48];
		switch (state) {
		case 1:
			inventory = new ItemStack[84];
			break;
		case 2:
			inventory = new ItemStack[117];
			break;
		}
		ReflectionHelper.setPrivateValue(TileEntityAlchemicalChest.class, this, inventory, "inventory");
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		fixState(compound.getByte("state"));
		super.readFromNBT(compound);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setByte("state", state);
		super.writeToNBT(compound);
	}

	public boolean onWanded(EntityPlayer player, int side) {
		if (player.isSneaking() && numUsingPlayers == 0) {
			setOrientation(side);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			player.worldObj.playSound(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, "thaumcraft:tool", 0.3F, 1.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
			player.swingItem();
			markDirty();
			return true;
		} else
			return false;
	}

	@Override
	public int onWandRightClick(World world, ItemStack wand, EntityPlayer player, int i, int j, int k, int side, int meta) {
		onWanded(player, side);
		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wand, EntityPlayer player) {
		return null;
	}

	@Override
	public void onUsingWandTick(ItemStack wand, EntityPlayer player, int count) {}

	@Override
	public void onWandStoppedUsing(ItemStack wand, World world, EntityPlayer player, int count) {}
}