package T145.magistics.init;

import T145.magistics.api.variants.Aspect;
import T145.magistics.api.variants.items.ResearchType;
import T145.magistics.client.lib.ModelBakery;
import T145.magistics.items.ItemShard;
import T145.magistics.items.MItem;
import T145.magistics.items.research.ItemDiscovery;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

	public static Item crystalShard;
	public static Item researchNote;
	public static Item researchTheory;
	public static Item researchDiscovery;

	public static void init() {
		crystalShard = new ItemShard();
		researchNote = new MItem("research_note", ResearchType.getTypes());
		researchTheory = new MItem("research_theory", ResearchType.getTypes());
		researchDiscovery = new ItemDiscovery();
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		ModelBakery.registerItemModel(crystalShard, 0, "shards/dull");

		for (Aspect type : Aspect.values()) {
			ModelBakery.registerItemModel(crystalShard, type.ordinal() + 1, "shards/" + type.getName());
		}

		for (ResearchType type : ResearchType.values()) {
			ModelBakery.registerItemModel(researchNote, type.ordinal(), "research/note/" + type.getName());
			ModelBakery.registerItemModel(researchTheory, type.ordinal(), "research/theory/" + type.getName());
			ModelBakery.registerItemModel(researchDiscovery, type.ordinal(), "research/discovery/" + type.getName());
		}
	}
}