package T145.magistics.blocks;

import cpw.mods.ironchest.IronChestType;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockChestHungryMetalItem extends ItemBlock {
	public BlockChestHungryMetalItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return super.getUnlocalizedName() + "." + BlockChestHungryMetal.getSimpleChestName(IronChestType.values()[is.getItemDamage()]);
	}
}