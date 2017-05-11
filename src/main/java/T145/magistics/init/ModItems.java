package T145.magistics.init;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.variants.IVariant;
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

	private static void registerItemModel(Item item, int meta, String path, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(path, variant));
	}

	private static void registerItemModel(Item item, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	private static void registerItemModel(Item item, int meta, IVariant variant) {
		registerItemModel(item, meta, variant.getClientName());
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		for (ResearchType type : ResearchType.values()) {
			registerItemModel(researchFragment, type.ordinal(), Magistics.MODID + ":research/fragment/" + type.getName(), "inventory");
			registerItemModel(researchNote, type.ordinal(), Magistics.MODID + ":research/note/" + type.getName(), "inventory");
			registerItemModel(researchDiscovery, type.ordinal(), Magistics.MODID + ":research/discovery/" + type.getName(), "inventory");
		}
	}
}