package T145.magistics.init;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.variants.items.ResearchType;
import T145.magistics.items.research.ItemDiscovery;
import T145.magistics.items.research.ItemFragment;
import T145.magistics.items.research.ItemNote;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

	public static List<Item> itemRegistry = new ArrayList<Item>();

	public static Item researchFragment;
	public static Item researchNote;
	public static Item researchDiscovery;

	public static void init() {
		itemRegistry.add(researchFragment = new ItemFragment());
		itemRegistry.add(researchNote = new ItemNote());
		itemRegistry.add(researchDiscovery = new ItemDiscovery());
	}

	private static void registerItemModel(Item item, int meta, String path) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Magistics.MODID + ":" + path, "inventory"));
	}

	private static void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		for (ResearchType type : ResearchType.values()) {
			registerItemModel(researchFragment, type.ordinal(), "research/fragment/" + type.getName());
			registerItemModel(researchNote, type.ordinal(), "research/note/" + type.getName());
			registerItemModel(researchDiscovery, type.ordinal(), "research/discovery/" + type.getName());
		}
	}
}