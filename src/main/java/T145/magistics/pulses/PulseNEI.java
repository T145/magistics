package T145.magistics.pulses;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.items.ItemDummy;
import T145.magistics.lib.Injector;
import T145.magistics.pulses.core.ServerPulse;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PulseNEI extends ServerPulse {
	private Injector nei = new Injector("codechicken.nei.api.API");;

	private void hideItem(ItemStack item) {
		nei.invokeMethod("hideItem", ItemStack.class, item);
	}

	public void setItemListEntries(Block block, Iterable<ItemStack> items) {
		setItemListEntries(Item.getItemFromBlock(block), items);
	}

	public void setItemListEntries(Item item, Iterable<ItemStack> items) {
		nei.invokeMethod("setItemListEntries", new Class[] { Item.class, Iterable.class }, item, items);
	}

	@Override
	public String getModId() {
		return "NotEnoughItems";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		hideItem(new ItemStack(ItemDummy.INFERNAL_FURNACE));
		hideItem(new ItemStack(BlockNetherFurnace.ACTIVE, 1, Short.MAX_VALUE));
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {}
}