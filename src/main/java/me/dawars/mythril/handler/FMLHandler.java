package me.dawars.mythril.handler;

import me.dawars.mythril.MythrilMod;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class FMLHandler {
	@SubscribeEvent
	public void playerTickEvent(final TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && event.player.getCurrentArmor(3) != null && event.player.getCurrentArmor(3).getItem() == MythrilMod.itemMythrilHelmet)
			event.player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 9, 1));
	}
}