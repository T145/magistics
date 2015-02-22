package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
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
		this.setCreativeTab(Elysium.tabElysium);

	}

	@Override
    public Item setTextureName(String name)
    {
        this.iconString = Elysium.ID + ":" + name;
        return this;
    }
}
