package T145.magistics.pulses;

import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import T145.magistics.pulses.core.ServerPulse;
import T145.magistics.utils.Injector;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Pulse(id = "NotEnoughItems", modsRequired = "NotEnoughItems")
public class PulseNotEnoughItems extends ServerPulse {
	private Injector nei = new Injector("codechicken.nei.api.API");

	private void hideItem(ItemStack item) {
		nei.invokeMethod("hideItem", ItemStack.class, item);
	}

	public void setItemListEntries(Block block, Iterable<ItemStack> items) {
		setItemListEntries(Item.getItemFromBlock(block), items);
	}

	public void setItemListEntries(Item item, Iterable<ItemStack> items) {
		nei.invokeMethod("setItemListEntries", new Class[] { Item.class, Iterable.class }, item, items);
	}

	@Handler
	public void preInit(FMLPreInitializationEvent event) {}

	@Handler
	public void init(FMLInitializationEvent event) {
	}

	@Handler
	public void postInit(FMLPostInitializationEvent event) {}
}