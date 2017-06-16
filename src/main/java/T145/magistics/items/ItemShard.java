package T145.magistics.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShard extends MItem {

	public ItemShard() {
		super("shard", new String[] { "dull", "air", "earth", "fire", "water", "void", "magic" });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getMetadata() != 0;
	}
}