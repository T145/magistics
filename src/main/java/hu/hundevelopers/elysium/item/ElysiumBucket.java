package hu.hundevelopers.elysium.item;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

public class ElysiumBucket extends ItemBucket
{
	public ElysiumBucket(Block block)
	{
		super(block);
		this.setContainerItem(Items.bucket);

	}

	@Override
    public Item setTextureName(String name)
    {
        this.iconString = "elysium:" + name;
        return this;
    }
}
