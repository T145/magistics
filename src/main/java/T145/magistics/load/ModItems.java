package T145.magistics.load;

import T145.magistics.items.ItemShard;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

	public static void init(FMLInitializationEvent event) {}

	public static void postInit(FMLPostInitializationEvent event) {}
}