package T145.magistics.common.items.baubles;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.Thaumcraft;
import T145.magistics.api.items.baubles.ItemBauble;
import T145.magistics.common.Magistics;
import T145.magistics.common.items.ItemResources;
import baubles.api.BaubleType;

public class ItemRingSouls extends ItemBauble {
	private Random rand = new Random();

	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase user) {
		List<EntityXPOrb> xpOrbs = user.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(user.posX - 2.0D, user.posY - 2.0D, user.posZ - 2.0D, user.posX + 2.0D, user.posY + 2.0D, user.posZ + 2.0D));
		EntityXPOrb xpOrb = null;

		if (xpOrbs.size() > 0 && rand.nextInt(10) == 0)
			xpOrb = xpOrbs.get(0);

		if (xpOrb != null && !xpOrb.isDead) {
			Thaumcraft.proxy.wispFXEG(xpOrb.worldObj, xpOrb.posX, xpOrb.posY, xpOrb.posZ, xpOrb);
			if (user instanceof EntityPlayer)
				((EntityPlayer) user).inventory.addItemStackToInventory(new ItemStack(Magistics.proxy.itemResources, 1, ItemResources.Types.soul_fragment.ordinal()));
			xpOrb.setDead();
		}
	}
}