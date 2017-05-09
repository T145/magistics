package T145.magistics.init;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.variants.IVariant;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

	public static List<Item> itemRegistry = new ArrayList<Item>();

	public static void init() {}

	private void registerItemModel(Item item, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	private void registerItemModel(Item item, int meta, IVariant variant) {
		registerItemModel(item, meta, variant.getClientName());
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {}
}