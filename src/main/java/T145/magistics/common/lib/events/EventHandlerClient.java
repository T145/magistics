package T145.magistics.common.lib.events;

import net.minecraftforge.client.event.TextureStitchEvent;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventHandlerClient {
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) {
		if (event.map.getTextureType() == 0) {
			ConfigObjects.elysiumFluidWater.setIcons(
					ConfigObjects.blockElysiumWater.getBlockTextureFromSide(1),
					ConfigObjects.blockElysiumWater.getBlockTextureFromSide(2));
			ConfigObjects.elysiumFluidEnergy.setIcons(
					ConfigObjects.blockElysiumEnergyLiquid.getBlockTextureFromSide(1),
					ConfigObjects.blockElysiumEnergyLiquid.getBlockTextureFromSide(2));
		}
	}
}