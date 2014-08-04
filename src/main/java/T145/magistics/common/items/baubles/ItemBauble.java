package T145.magistics.common.items.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

public class ItemBauble extends Item implements IBauble {
	@Override
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.rare;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote) {
			InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
			for (int i = 0; i < baubles.getSizeInventory(); i++)
				if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, is)) {
					baubles.setInventorySlotContents(i, is.copy());
					if (!player.capabilities.isCreativeMode)
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					onEquipped(is, player);
					break;
				}
		}

		return is;
	}

	@Override
	public boolean canEquip(ItemStack is, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack is, EntityLivingBase player) {
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return null;
	}

	@Override
	public void onEquipped(ItemStack is, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack is, EntityLivingBase player) {}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase player) {}
}