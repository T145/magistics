package T145.magistics.containers;

import T145.magistics.tiles.MTileInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

public abstract class MContainer<T extends MTileInventory> extends Container {

	protected final T tile;
	protected final IItemHandlerModifiable inventory;

	public MContainer(InventoryPlayer playerInventory, T tile) {
		this.tile = tile;
		this.inventory = tile.getItemHandler();
	}

	public abstract int getField(int id);

	public abstract void setField(int id, int data);

	protected abstract void updateContainer(IContainerListener listener);

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		updateContainer(listener);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < listeners.size(); ++i) {
			updateContainer(listeners.get(i));
			setField(i, getField(i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile.isUsableByPlayer(player);
	}

	@Override
	public abstract ItemStack transferStackInSlot(EntityPlayer player, int index);
}