package T145.magistics.common.lib.events;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import T145.magistics.common.config.ConfigObjects;
import baubles.api.BaublesApi;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerMithril {
	Random random = new Random();

	@SubscribeEvent
	public void onPlayerGetHurt(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer && FMLCommonHandler.instance().getSide().isServer()) {
			ItemStack slot = BaublesApi.getBaubles((EntityPlayer) event.entityLiving).getStackInSlot(0);
			if (slot != null && slot.getItem() == ConfigObjects.itemEnderNecklace)
				event.entityLiving.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 300, 2));
		}
	}

	@SubscribeEvent
	public void onPlayerAttacked(AttackEntityEvent event) {
		if (event.target instanceof EntityPlayer) {
			ItemStack slot1 = BaublesApi.getBaubles(event.entityPlayer).getStackInSlot(1), slot2 = BaublesApi.getBaubles(event.entityPlayer).getStackInSlot(2);
			if (((slot1 != null && slot1.getItem() == ConfigObjects.itemWitherRing) || (slot2 != null && slot2.getItem() == ConfigObjects.itemWitherRing)) && random.nextInt(3) == 0)
				((EntityPlayer) event.target).addPotionEffect(new PotionEffect(Potion.wither.getId(), 200, 2));
		}
	}
}