package T145.magistics.load;

import T145.magistics.items.ItemShard;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {

	public static ItemShard itemShard;
	public static ItemShard itemShardFragment;

	public static void preInit(FMLPreInitializationEvent event) {
		itemShard = new ItemShard("shard", true);
		itemShardFragment = new ItemShard("fragment", false);
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		itemShard.registerModel();
		itemShardFragment.registerModel();
	}

	public static void init(FMLInitializationEvent event) {
		OreDictionary.registerOre("shardAir", new ItemStack(itemShard, 1, 0));
		OreDictionary.registerOre("shardFire", new ItemStack(itemShard, 1, 1));
		OreDictionary.registerOre("shardWater", new ItemStack(itemShard, 1, 2));
		OreDictionary.registerOre("shardEarth", new ItemStack(itemShard, 1, 3));
		OreDictionary.registerOre("shardOrder", new ItemStack(itemShard, 1, 4));
		OreDictionary.registerOre("shardEntropy", new ItemStack(itemShard, 1, 5));
		OreDictionary.registerOre("shardTainted", new ItemStack(itemShard, 1, 6));
	}

	public static void postInit(FMLPostInitializationEvent event) {}
}