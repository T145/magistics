package T145.magistics.lib.managers;

import net.minecraftforge.items.IItemHandler;

public class SlotRange {

	public final int first;
	public final int lastInc;
	public final int lastExc;

	public SlotRange(IItemHandler inv) {
		this(0, inv.getSlots());
	}

	public SlotRange(int start, int numSlots) {
		first = start;
		lastInc = start + numSlots - 1;
		lastExc = start + numSlots;
	}

	public boolean contains(int slot) {
		return slot >= first && slot <= lastInc;
	}

	@Override
	public String toString() {
		return String.format("SlotRange: {first: %d, lastInc: %d, lastExc: %d}", first, lastInc, lastExc);
	}
}