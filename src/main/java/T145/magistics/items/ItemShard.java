package T145.magistics.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShard extends MItem {

	private final boolean hasEffect;

	public ItemShard(String name, boolean hasEffect) {
		super(name, new String[] { "dull", "air", "fire", "water", "earth", "magic", "void" });
		this.hasEffect = hasEffect;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		if (hasEffect && stack.getItemDamage() > 0) {
			return true;
		}
		return false;
	}

	public static enum ShardType implements IStringSerializable {

		AIR, FIRE, WATER, EARTH, LIGHT, DARK;

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		public static ShardType byMetadata(int meta) {
			return values()[MathHelper.clamp(meta, 0, meta)];
		}
	}
}