package T145.magistics.items;

import T145.magistics.api.variants.items.EnumShard;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShard extends MItem {

	public ItemShard() {
		super("shard", EnumShard.getTypes());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return EnumShard.values()[stack.getMetadata()] != EnumShard.DULL;
	}
}