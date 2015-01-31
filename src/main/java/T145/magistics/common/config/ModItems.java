package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.Magistics;
import T145.magistics.common.items.ItemEridium;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.baubles.ItemRingSouls;
import T145.magistics.common.items.relics.ItemDawnstone;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems extends CommonProxy {
	public static List<Item> items = new ArrayList<Item>();

	public static Item itemEridium, itemResources, itemAmuletDismay, itemAmuletLife, itemBeltCleansing, itemBeltVigor, itemRingSouls, itemCruelMask, itemDawnstone;

	public static void load() {
		items.add(itemEridium = new ItemEridium().setUnlocalizedName("eridium"));
		items.add(itemResources = new ItemResources().setUnlocalizedName("mystic_resources"));
		items.add(itemAmuletDismay = new ItemAmuletDismay().setUnlocalizedName("bauble.amulet_dismay"));
		items.add(itemAmuletLife = new ItemAmuletLife().setUnlocalizedName("bauble.amulet_life"));
		items.add(itemBeltCleansing = new ItemBeltCleansing().setUnlocalizedName("bauble.belt_cleansing"));
		items.add(itemBeltVigor = new ItemBeltVigor().setUnlocalizedName("bauble.belt_vigor"));
		items.add(itemRingSouls = new ItemRingSouls().setUnlocalizedName("bauble.ring_souls"));
		items.add(itemCruelMask = new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName("cruel_mask"));
		items.add(itemDawnstone = new ItemDawnstone().setUnlocalizedName("dawnstone"));

		for (Item item : ModItems.items)
			GameRegistry.registerItem(item.setCreativeTab(tabMagistics), item.getUnlocalizedName());
	}

	public static void registerOres() {
		OreDictionary.registerOre("ingotEridium", new ItemStack(itemEridium, 1, 0));
		OreDictionary.registerOre("nuggetEridium", new ItemStack(itemEridium, 1, 1));
	}
}