package T145.magistics.items;

import T145.magistics.Magistics;
import net.minecraft.item.Item;

public class ItemShardDull extends Item {
	public static final Item INSTANCE = new ItemShardDull();

	public ItemShardDull() {
		super();
		setCreativeTab(Magistics.tabMagistics);
		setUnlocalizedName("shard_dull");
		setTextureName("thaumcraft:shard");
	}
}