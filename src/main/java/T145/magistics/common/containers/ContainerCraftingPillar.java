package T145.magistics.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class ContainerCraftingPillar extends Container {
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}