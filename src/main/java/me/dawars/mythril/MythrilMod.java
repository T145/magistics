package me.dawars.mythril;

import me.dawars.mythril.blocks.MithrilChunk;
import me.dawars.mythril.blocks.MithrilDecoBlock;
import me.dawars.mythril.blocks.MithrilDecoItemBlock;
import me.dawars.mythril.blocks.MithrilOre;
import me.dawars.mythril.blocks.MithrilPillarBlock;
import me.dawars.mythril.blocks.PillarItemBlock;
import me.dawars.mythril.handler.MythrilWorldGen;
import me.dawars.mythril.items.EnderNecklace;
import me.dawars.mythril.items.MithrilWhistle;
import me.dawars.mythril.items.MythrilArmor;
import me.dawars.mythril.items.MythrilAxe;
import me.dawars.mythril.items.MythrilBow;
import me.dawars.mythril.items.MythrilHoe;
import me.dawars.mythril.items.MythrilPickaxe;
import me.dawars.mythril.items.MythrilRing;
import me.dawars.mythril.items.MythrilSpade;
import me.dawars.mythril.items.MythrilSword;
import me.dawars.mythril.items.MythrilWitherRing;
import me.dawars.mythril.proxy.CommonProxy;
import me.dawars.mythril.tiles.TileMythrilPillar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(name = "Mithril mod", version = "1.6", useMetadata = false, modid = "mythril")
public class MythrilMod
{
	@Mod.Instance("mythril")
	private static MythrilMod instance;
	public static final String version = "1.6";
	public static final String name = "Mithril mod";
	public static final String id = "mythril";
	@SidedProxy(clientSide = "me.dawars.mythril.proxy.ClientProxy", serverSide = "me.dawars.mythril.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static final CreativeTabs tabMithryl;
	public static Item.ToolMaterial ToolMaterialMythril;
	public static ItemArmor.ArmorMaterial ArmorMaterialMythril;
	public static Item itemMythrilSword;
	public static Item itemMythrilSpade;
	public static Item itemMythrilPickaxe;
	public static Item itemMythrilAxe;
	public static Item itemMythrilHoe;
	public static Item itemMythrilBow;
	public static Item itemMythrilIngot;
	public static Item itemMythrilChunk;
	public static Item itemMythrilChunkCombined;
	public static Item itemQuartzRod;
	public static Item itemMythrilChest;
	public static Item itemMythrilHelmet;
	public static Item itemMythrilPants;
	public static Item itemMythrilBoots;
	public static Item itemEnderNecklace;
	public static Item itemMythrilRing;
	public static Item itemWitherRing;
	public static Item itemFlute;
	public static Block blockMythrilOre;
	public static Block blockMythrilChunk;
	public static Block blockMythrilDecoBlock;
	public static Block blockMythrilPillar;
	public static int mythrilPillarRenderID;
	public static int mythrilArmorID;
	public static boolean isBaubles;

	public static MythrilMod getInstance() {
		return MythrilMod.instance;
	}

	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		MythrilMod.proxy.initIconRegistry();
		this.registerItem(MythrilMod.itemMythrilSword = new MythrilSword(MythrilMod.ToolMaterialMythril).setUnlocalizedName("mythril_katana").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilPickaxe = new MythrilPickaxe(MythrilMod.ToolMaterialMythril).setUnlocalizedName("mythril_pickaxe").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilAxe = new MythrilAxe(MythrilMod.ToolMaterialMythril).setUnlocalizedName("mythril_halberd").setTextureName("mythril:mythril_halberd").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilSpade = new MythrilSpade(MythrilMod.ToolMaterialMythril).setUnlocalizedName("mythril_spade").setTextureName("mythril:mythril_spade").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilHoe = new MythrilHoe(MythrilMod.ToolMaterialMythril).setUnlocalizedName("mythril_hoe").setTextureName("mythril:mythril_hoe").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilBow = new MythrilBow().setUnlocalizedName("mithril_bow").setTextureName("mythril:mithril_bow").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilIngot = new Item().setUnlocalizedName("mythril_ingot").setTextureName("mythril:mythril_ingot").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilChunk = new Item().setUnlocalizedName("mythril_chunk").setTextureName("mythril:mythril_chunk").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilChunkCombined = new Item().setUnlocalizedName("mythril_chunkcombined").setTextureName("mythril:mythril_chunkcombined").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemQuartzRod = new Item().setUnlocalizedName("quartz_rod").setTextureName("mythril:quartz_rod").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilHelmet = new MythrilArmor(MythrilMod.ArmorMaterialMythril, MythrilMod.mythrilArmorID, 0).setUnlocalizedName("mythril_helmet").setTextureName("mythril:mythril_helmet").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilChest = new MythrilArmor(MythrilMod.ArmorMaterialMythril, MythrilMod.mythrilArmorID, 1).setUnlocalizedName("mythril_chestplate").setTextureName("mythril:mythril_chestplate").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilPants = new MythrilArmor(MythrilMod.ArmorMaterialMythril, MythrilMod.mythrilArmorID, 2).setUnlocalizedName("mythril_pants").setTextureName("mythril:mythril_leggings").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilBoots = new MythrilArmor(MythrilMod.ArmorMaterialMythril, MythrilMod.mythrilArmorID, 3).setUnlocalizedName("mythril_boots").setTextureName("mythril:mythril_boots").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemEnderNecklace = new EnderNecklace().setUnlocalizedName("ender_amulet").setTextureName("mythril:mythril_accs1").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemMythrilRing = new MythrilRing().setUnlocalizedName("mythril_ring").setTextureName("mythril:mythril_ring1").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemWitherRing = new MythrilWitherRing().setUnlocalizedName("mythril_wither_ring").setTextureName("mythril:mythril_ring2").setCreativeTab(MythrilMod.tabMithryl));
		this.registerItem(MythrilMod.itemFlute = new MithrilWhistle().setUnlocalizedName("mythril_flute").setTextureName("mythril:mythril_flute").setCreativeTab(MythrilMod.tabMithryl));
		this.registerBlock(MythrilMod.blockMythrilOre = new MithrilOre(Material.rock).setHardness(50.0f).setResistance(2000.0f).setStepSound(Block.soundTypeStone).setBlockName("mythril_ore"));
		this.registerBlock(MythrilMod.blockMythrilChunk = new MithrilChunk(Material.rock).setHardness(3.0f).setResistance(15.0f).setStepSound(Block.soundTypePiston).setBlockName("mythril_chunk_block"));
		GameRegistry.registerBlock(MythrilMod.blockMythrilDecoBlock = new MithrilDecoBlock(Material.iron).setHardness(5.0f).setResistance(200.0f).setStepSound(Block.soundTypeMetal).setBlockName("mythril_block").setBlockTextureName("mythril:mythril_block").setCreativeTab(MythrilMod.tabMithryl), (Class)MithrilDecoItemBlock.class, MythrilMod.blockMythrilDecoBlock.getUnlocalizedName());
		GameRegistry.registerBlock(MythrilMod.blockMythrilPillar = new MithrilPillarBlock(Material.iron).setHardness(5.0f).setResistance(200.0f).setStepSound(Block.soundTypeMetal).setBlockName("mythril_pillar").setCreativeTab(MythrilMod.tabMithryl), (Class)PillarItemBlock.class, MythrilMod.blockMythrilPillar.getUnlocalizedName());
		GameRegistry.registerTileEntity((Class)TileMythrilPillar.class, "TileMythrilPillar");
		MinecraftForge.EVENT_BUS.register((Object)new CommonHandler());
		MythrilMod.proxy.registerRenderers();
	}

	@Mod.EventHandler
	public void init(final FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator((IWorldGenerator)new MythrilWorldGen(), 0);
	}

	@Mod.EventHandler
	public void modsLoaded(final FMLPostInitializationEvent event) {
		OreDictionary.registerOre("oreMithril", MythrilMod.blockMythrilOre);
		OreDictionary.registerOre("ingotMithril", MythrilMod.itemMythrilIngot);
		OreDictionary.registerOre("nuggetMithril", MythrilMod.itemMythrilChunk);
		OreDictionary.registerOre("stickQuartz", MythrilMod.itemQuartzRod);
		OreDictionary.registerOre("leather", Items.leather);
		if (Loader.isModLoaded("Baubles")) {
			MythrilMod.isBaubles = true;
		}
		final ItemStack swordStack = new ItemStack(MythrilMod.itemMythrilSword);
		swordStack.addEnchantment(Enchantment.knockback, 3);
		swordStack.addEnchantment(Enchantment.looting, 2);
		GameRegistry.addRecipe(new ShapedOreRecipe(swordStack, new Object[] { "L M", " M ", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(swordStack, new Object[] { " LM", " M ", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(swordStack, new Object[] { "  M", "LM ", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(swordStack, new Object[] { "  M", " ML", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(swordStack, new Object[] { "  M", " M ", "SL ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(swordStack, new Object[] { "  M", " M ", "S L", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		final ItemStack pickaxeStack = new ItemStack(MythrilMod.itemMythrilPickaxe);
		pickaxeStack.addEnchantment(Enchantment.fortune, 2);
		pickaxeStack.addEnchantment(Enchantment.unbreaking, 1);
		GameRegistry.addRecipe(new ShapedOreRecipe(pickaxeStack, new Object[] { "MMM", "LS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(pickaxeStack, new Object[] { "MMM", " SL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(pickaxeStack, new Object[] { "MMM", " S ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(pickaxeStack, new Object[] { "MMM", " S ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		final ItemStack axeStack = new ItemStack(MythrilMod.itemMythrilAxe);
		axeStack.addEnchantment(Enchantment.sharpness, 2);
		axeStack.addEnchantment(Enchantment.fortune, 3);
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MML", "MS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MM ", "MSL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MM ", "MS ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MM ", "MS ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		final ItemStack shovelStack = new ItemStack(MythrilMod.itemMythrilSpade);
		shovelStack.addEnchantment(Enchantment.silkTouch, 1);
		shovelStack.addEnchantment(Enchantment.unbreaking, 3);
		GameRegistry.addRecipe(new ShapedOreRecipe(shovelStack, new Object[] { "LM ", " S ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(shovelStack, new Object[] { " ML", " S ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(shovelStack, new Object[] { " M ", "LS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(shovelStack, new Object[] { " M ", " SL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(shovelStack, new Object[] { " M ", " S ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(shovelStack, new Object[] { " M ", " S ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		final ItemStack hoeStack = new ItemStack(MythrilMod.itemMythrilHoe);
		hoeStack.addEnchantment(Enchantment.unbreaking, 127);
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MML", " S ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", "LS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", " SL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", " S ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", " S ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ItemStack(MythrilMod.itemQuartzRod), new Object[] { "Q", "Q", "Q", 'Q', Items.quartz });
		final ItemStack bow = new ItemStack(MythrilMod.itemMythrilBow);
		bow.addEnchantment(Enchantment.sharpness, 8);
		bow.addEnchantment(Enchantment.infinity, 1);
		GameRegistry.addRecipe(new ShapedOreRecipe(bow, new Object[] { "QMM", "M S", "MSE", 'M', "stickQuartz", 'Q', "ingotMithril", 'E', Items.ender_pearl, 'S', Items.string }));
		final ItemStack helmet = new ItemStack(MythrilMod.itemMythrilHelmet);
		helmet.addEnchantment(Enchantment.respiration, 3);
		helmet.addEnchantment(Enchantment.aquaAffinity, 1);
		GameRegistry.addRecipe(new ShapedOreRecipe(helmet, new Object[] { "LIL", "I I", "DLD", 'D', "gemDiamond", 'I', "ingotMithril", 'L', "leather" }));
		final ItemStack chest = new ItemStack(MythrilMod.itemMythrilChest);
		chest.addEnchantment(Enchantment.thorns, 4);
		GameRegistry.addRecipe(new ShapedOreRecipe(chest, new Object[] { "I I", "ILI", "LDL", 'D', "gemDiamond", 'I', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MythrilMod.itemMythrilPants), new Object[] { "III", "I I", 'I', "ingotMithril" }));
		final ItemStack boots = new ItemStack(MythrilMod.itemMythrilBoots);
		boots.addEnchantment(Enchantment.featherFalling, 127);
		GameRegistry.addRecipe(new ShapedOreRecipe(boots, new Object[] { "I I", 'I', "ingotMithril" }));
		GameRegistry.addSmelting(MythrilMod.blockMythrilOre, new ItemStack(MythrilMod.itemMythrilIngot), 2.0f);
		GameRegistry.addSmelting(MythrilMod.itemMythrilChunkCombined, new ItemStack(MythrilMod.itemMythrilIngot), 2.0f);
		GameRegistry.addRecipe(new ShapelessOreRecipe(MythrilMod.itemMythrilChunkCombined, new Object[] { "nuggetMithril", "nuggetMithril", "nuggetMithril" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MythrilMod.itemEnderNecklace), new Object[] { "M M", " M ", " E ", 'M', "ingotMithril", 'E', Blocks.dragon_egg }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MythrilMod.itemMythrilRing), new Object[] { " M ", "M M", " M ", 'M', "ingotMithril" }));
		GameRegistry.addShapelessRecipe(new ItemStack(MythrilMod.itemWitherRing), new Object[] { MythrilMod.itemMythrilRing, Items.nether_star });
		GameRegistry.addRecipe(new ShapedOreRecipe(MythrilMod.itemFlute, new Object[] { "M  ", " M ", "  M", 'M', "ingotMithril" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MythrilMod.blockMythrilDecoBlock, 1, 0), new Object[] { "II", "II", 'I', "ingotMithril" }));
		GameRegistry.addShapelessRecipe(new ItemStack(MythrilMod.itemMythrilIngot, 4), new Object[] { new ItemStack(MythrilMod.blockMythrilDecoBlock, 1, 0) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MythrilMod.blockMythrilDecoBlock, 4, 1), new Object[] { "II", "II", 'I', new ItemStack(MythrilMod.blockMythrilDecoBlock, 1, 0) }));
		GameRegistry.addShapelessRecipe(new ItemStack(MythrilMod.blockMythrilDecoBlock, 1, 2), new Object[] { new ItemStack(MythrilMod.blockMythrilDecoBlock, 1, 1) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MythrilMod.blockMythrilPillar, 1, 0), new Object[] { "III", " Q ", "III", 'I', "ingotMithril", 'Q', Items.quartz }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MythrilMod.blockMythrilPillar, 1, 1), new Object[] { "III", " I ", "III", 'I', "ingotMithril" }));
	}

	private void registerItem(final Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	private void registerBlock(final Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	static {
		tabMithryl = new CreativeTabs(CreativeTabs.getNextID(), "mythril") {
			@SideOnly(Side.CLIENT)
			public ItemStack getIconItemStack() {
				final ItemStack stack = new ItemStack(MythrilMod.itemMythrilSword);
				stack.addEnchantment(Enchantment.sharpness, 1);
				return stack;
			}

			public Item getTabIconItem() {
				return this.getIconItemStack().getItem();
			}
		};
		MythrilMod.ToolMaterialMythril = EnumHelper.addToolMaterial("MYTHRIL", 3, 2084, 12.0f, 10.0f, 22);
		MythrilMod.ArmorMaterialMythril = EnumHelper.addArmorMaterial("MYTHRIL", 66, new int[] { 3, 8, 6, 3 }, 25);
		MythrilMod.mythrilArmorID = 0;
		MythrilMod.isBaubles = false;
	}
}