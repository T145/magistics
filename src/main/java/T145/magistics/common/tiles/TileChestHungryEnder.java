package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.wands.IWandable;
import T145.magistics.common.config.MagisticsConfig;

public class TileChestHungryEnder extends TileEntityEnderChest implements IWandable {
	public float lidAngle, prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;
	public ForgeDirection orientation = ForgeDirection.getOrientation(0);

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		orientation = ForgeDirection.getOrientation(nbt.getInteger("orientation"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("orientation", orientation.ordinal());
	}

	@Override
	public int onWandRightClick(World world, ItemStack wand, EntityPlayer player, int i, int j, int k, int side, int md) {
		orientation = ForgeDirection.getOrientation(side);
		player.worldObj.playSound(i + 0.5, j + 0.5, k + 0.5, "thaumcraft:tool", 0.3F, 1.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
		player.swingItem();
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

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (++ticksSinceSync % 20 * 4 == 0) {
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, MagisticsConfig.blocks[0], 1, numPlayersUsing);
		}

		prevLidAngle = lidAngle;
		float f = 0.1F;
		double d1;

		if (numPlayersUsing > 0 && lidAngle == 0.0F) {
			double d0 = (double) xCoord + 0.5D;
			d1 = (double) zCoord + 0.5D;
			worldObj.playSoundEffect(d0, (double) yCoord + 0.5D, d1, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
			float f2 = lidAngle;

			if (numPlayersUsing > 0) {
				lidAngle += f;
			} else {
				lidAngle -= f;
			}

			if (lidAngle > 1.0F) {
				lidAngle = 1.0F;
			}

			float f1 = 0.5F;

			if (lidAngle < f1 && f2 >= f1) {
				d1 = (double) xCoord + 0.5D;
				double d2 = (double) zCoord + 0.5D;
				worldObj.playSoundEffect(d1, (double) yCoord + 0.5D, d2, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0.0F) {
				lidAngle = 0.0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int eventID, int recievedData) {
		switch (eventID) {
		case 1:
			numPlayersUsing = recievedData;
			return true;
		case 2:
			if (lidAngle < recievedData / 10.0F)
				lidAngle = recievedData / 10.0F;
			return true;
		default:
			return tileEntityInvalid;
		}
	}

	@Override
	public void func_145969_a() {
		++numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, MagisticsConfig.blocks[0], 1, numPlayersUsing);
	}

	@Override
	public void func_145970_b() {
		--numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, MagisticsConfig.blocks[0], 1, numPlayersUsing);
	}
}