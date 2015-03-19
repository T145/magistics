package T145.magistics.common.blocks;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockCrystalStorageItem extends ItemBlock {
	private static String[] types = {
		"air", "fire", "water", "earth", "ordo", "entropy"
	};

	public BlockCrystalStorageItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	public String getAspect(int meta) {
		LinkedList<String> aspects = new LinkedList<String>(), dest = new LinkedList<String>();
		for (String aspect : types)
			aspects.add(aspect);
		dest.addAll(aspects);
		dest.addAll(aspects);
		return dest.get(meta);
	}

	public String getType(String first, String second, int meta) {
		return meta > 5 ? second : first;
	}

	public String getName(String first, String second, int meta) {
		return super.getUnlocalizedName() + "." + getType(first, second, meta) + "." + getAspect(meta);
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getName("basic", "brick", is.getItemDamage());
	}
}