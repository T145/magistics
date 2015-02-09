package T145.magistics.common.items.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import T145.magistics.api.items.baubles.IMod;

public class ModGlowStone implements IMod {
	@Override
	public EnumChatFormatting getColor(ItemStack gem) {
		return EnumChatFormatting.YELLOW;
	}

	@Override
	public void onWornTick(ItemStack ring, ItemStack gem,
			EntityLivingBase player) {
		// if(player.worldObj.isRemote)
		// {
		// System.out.println("Light tick");
		// player.worldObj.setLightValue(EnumSkyBlock.Sky, (int) player.posX,
		// (int) player.posY + 1, (int) player.posZ, 16);
		// player.worldObj.updateLightByType(EnumSkyBlock.Sky, (int)
		// player.posX, (int) player.posY + 1, (int) player.posZ);
		//
		// }
	}

	@Override
	public void onEquipped(ItemStack ring, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack ring, EntityLivingBase player) {}

	@Override
	public boolean canEquip(ItemStack ring, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack ring, EntityLivingBase player) {
		return true;
	}
}