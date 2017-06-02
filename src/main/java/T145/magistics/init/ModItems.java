package T145.magistics.init;

import T145.magistics.Magistics;
import T145.magistics.api.variants.items.EnumShard;
import T145.magistics.api.variants.items.ResearchType;
import T145.magistics.items.ItemShard;
import T145.magistics.items.research.ItemDiscovery;
import T145.magistics.items.research.ItemNote;
import T145.magistics.items.research.ItemTheory;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

	public static Item crystalShard;
	public static Item researchNote;
	public static Item researchTheory;
	public static Item researchDiscovery;

	public static void init() {
		crystalShard = new ItemShard();
		researchNote = new ItemNote();
		researchTheory = new ItemTheory();
		researchDiscovery = new ItemDiscovery();
	}

	private static void registerItemModel(Item item, int meta, String path) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Magistics.MODID + ":" + path, "inventory"));
	}

	private static void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		for (EnumShard type : EnumShard.values()) {
			registerItemModel(crystalShard, type.ordinal(), "shards/" + type.getName());
		}

		for (ResearchType type : ResearchType.values()) {
			registerItemModel(researchNote, type.ordinal(), "research/note/" + type.getName());
			registerItemModel(researchTheory, type.ordinal(), "research/theory/" + type.getName());
			registerItemModel(researchDiscovery, type.ordinal(), "research/discovery/" + type.getName());
		}
	}
}