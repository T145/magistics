package T145.magistics.common.lib;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.common.lib.InventoryHelper;

public class MagisticsUtils {
	public static void absorbToInventory(World world, int i, int j, int k, EntityItem collidingEntity, Block trigger, int eventID, int eventParameter, IInventory designateInventory, boolean playSound) {
		if (world.getTileEntity(i, j, k) == null || world.isRemote)
			return;
		if (!collidingEntity.isDead) {
			ItemStack leftovers = InventoryHelper.placeItemStackIntoInventory(collidingEntity.getEntityItem(), designateInventory, 1, true);
			if (leftovers == null || leftovers.stackSize != collidingEntity.getEntityItem().stackSize) {
				if (playSound)
					world.playSoundAtEntity(collidingEntity, "random.eat", 0.25F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
				world.addBlockEvent(i, j, k, trigger, eventID, eventParameter);
			}
			if (leftovers != null)
				collidingEntity.setEntityItemStack(leftovers);
			else
				collidingEntity.setDead();
		}
	}
}