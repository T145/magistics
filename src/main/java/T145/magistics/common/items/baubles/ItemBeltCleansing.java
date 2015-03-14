package T145.magistics.common.items.baubles;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import T145.magistics.api.items.baubles.ItemBauble;
import baubles.api.BaubleType;

public class ItemBeltCleansing extends ItemBauble {
	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.BELT;
	}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase user) {
		Collection effects = user.getActivePotionEffects();
		Potion cleansable[] = { Potion.blindness, Potion.confusion, Potion.poison, Potion.weakness };

		if (!effects.isEmpty()) {
			Iterator iterator = effects.iterator();
			while (iterator.hasNext()) {
				Potion effect = (Potion) iterator.next();
				for (int i = 0; i < cleansable.length; i++)
					if (effect == cleansable[i]) {
						user.removePotionEffect(effect.id);
						user.worldObj.playSoundAtEntity(user, "magistics.heal", 1F, 1F);
					}
			}
		}
	}
}