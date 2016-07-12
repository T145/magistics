package T145.magistics.lib.events;

import T145.magistics.items.ItemShardFragment;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import thaumcraft.common.config.ConfigBlocks;

public class WorldEventHandler {
	@SubscribeEvent
	public void onBlockHarvest(HarvestDropsEvent event) {
		if (event.harvester != null && !event.harvester.worldObj.isRemote && !event.isCanceled() && !event.isSilkTouching) {
			if (event.block == ConfigBlocks.blockCustomOre && event.blockMetadata != 0 && event.blockMetadata < 6) {
				event.drops.clear();

				for (int i = 0; i < 1 + event.harvester.worldObj.rand.nextInt(2 + event.fortuneLevel); ++i) {
					event.drops.add(new ItemStack(ItemShardFragment.INSTANCE, 1, event.blockMetadata - 1));
				}
			}
		}
	}
}