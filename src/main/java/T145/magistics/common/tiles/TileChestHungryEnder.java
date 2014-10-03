package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;

public class TileChestHungryEnder extends TileEntityEnderChest implements IWandable {
	@Override
	public boolean receiveClientEvent(int eventID, int recievedData) {
		switch (eventID) {
		case 1:
			field_145973_j = recievedData;
			return true;
		case 2:
			if (field_145972_a < recievedData / 10.0F)
				field_145972_a = recievedData / 10.0F;
			return true;
		default:
			return tileEntityInvalid;
		}
	}

	@Override
	public void func_145969_a() {
		++field_145973_j;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, field_145973_j);
	}

	@Override
	public void func_145970_b() {
		--field_145973_j;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, field_145973_j);
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int meta) {
		if (player.isSneaking()) {
			world.setBlockMetadataWithNotify(x, y, z, side, 2);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			player.worldObj.playSound(x + 0.5, y + 0.5, z + 0.5, "thaumcraft:tool", 0.3F, 1.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
			player.swingItem();
			markDirty();
		}
		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
		return null;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
}