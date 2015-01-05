package T145.magistics.common.config;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import T145.magistics.common.Magistics;

public class MagisticsCreativeTab extends CreativeTabs {
	public MagisticsCreativeTab() {
		super(Magistics.modid);
		setBackgroundImageName("magistics.png");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ModBlocks.blockChestHungryEnder);
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}

	@Override
	public void displayAllReleventItems(List list) {
		for (Block block : ModBlocks.blocks.keySet())
			block.getSubBlocks(Item.getItemFromBlock(block), this, list);
		for (Item item : ModItems.items)
			item.getSubItems(item, this, list);
	}
}