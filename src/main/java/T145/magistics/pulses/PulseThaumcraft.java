package T145.magistics.pulses;

import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.client.renderers.tile.TileArcaneLampRenderer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.items.wands.ItemWandCasting;
import T145.magistics.Magistics;
import T145.magistics.blocks.BlockArcaneLampRedstone;
import T145.magistics.blocks.BlockArcaneLampRedstoneItem;
import T145.magistics.blocks.BlockArcaneWood;
import T145.magistics.blocks.BlockChestHungryEnder;
import T145.magistics.blocks.BlockChestHungryTrapped;
import T145.magistics.blocks.BlockChthonianFurnace;
import T145.magistics.blocks.BlockEntropicDispenser;
import T145.magistics.blocks.BlockEverfullUrn;
import T145.magistics.blocks.BlockInfuser;
import T145.magistics.blocks.BlockInfuserItem;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.blocks.BlockVoidstone;
import T145.magistics.client.render.blocks.RenderBlockChest;
import T145.magistics.client.render.blocks.RenderBlockChthonianFurnace;
import T145.magistics.client.render.blocks.RenderBlockEverfullUrn;
import T145.magistics.client.render.blocks.RenderBlockInfuser;
import T145.magistics.client.render.blocks.RenderBlockLootCrate;
import T145.magistics.client.render.blocks.RenderBlockLootUrn;
import T145.magistics.client.render.tiles.RenderChestHungryEnder;
import T145.magistics.client.render.tiles.RenderChestHungryTrapped;
import T145.magistics.client.render.tiles.RenderChthonianFurnace;
import T145.magistics.client.render.tiles.RenderInfuser;
import T145.magistics.items.ItemDummy;
import T145.magistics.items.ItemShardDull;
import T145.magistics.items.ItemShardFragment;
import T145.magistics.items.relics.ItemDawnstone;
import T145.magistics.lib.CreativeTabCustom;
import T145.magistics.pulses.core.CorePulse;
import T145.magistics.research.RecipeHandler;
import T145.magistics.research.ResearchHandler;
import T145.magistics.tiles.TileArcaneLampRedstone;
import T145.magistics.tiles.TileChestHungryEnder;
import T145.magistics.tiles.TileChestHungryTrapped;
import T145.magistics.tiles.TileChthonianFurnace;
import T145.magistics.tiles.TileEverfullUrn;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import T145.magistics.tiles.TileNetherFurnace;
import T145.magistics.utils.RegistrationUtil;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Pulse(id = Thaumcraft.MODID, forced = true, modsRequired = Thaumcraft.MODID)
public class PulseThaumcraft extends CorePulse {
	@Handler
	public void preInit(FMLPreInitializationEvent event) {
		BiomeGenBase.getBiome(Config.biomeEerieID).getSpawnableList(EnumCreatureType.monster).add(new SpawnListEntry(EntityPech.class, 3, 1, 1));

		Config.allowCheatSheet = true;

		if (Magistics.configHandler.noFluidsInTCTabs) {
		}

		if (Magistics.configHandler.noTaintInTCTabs) {
		}
	}

	@Handler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(TileNetherFurnace.class, TileNetherFurnace.class.getSimpleName());
		GameRegistry.registerBlock(BlockNetherFurnace.ACTIVE, BlockNetherFurnace.ACTIVE.getUnlocalizedName() + "_on");
		GameRegistry.registerBlock(BlockNetherFurnace.INACTIVE, BlockNetherFurnace.INACTIVE.getUnlocalizedName() + "_off");

		GameRegistry.registerTileEntity(TileChthonianFurnace.class, TileChthonianFurnace.class.getSimpleName());
		GameRegistry.registerBlock(BlockChthonianFurnace.INSTANCE, BlockChthonianFurnace.INSTANCE.getUnlocalizedName());

		GameRegistry.registerTileEntity(TileChestHungryTrapped.class, TileChestHungryTrapped.class.getSimpleName());
		GameRegistry.registerBlock(BlockChestHungryTrapped.INSTANCE, BlockChestHungryTrapped.INSTANCE.getUnlocalizedName());

		GameRegistry.registerTileEntity(TileChestHungryEnder.class, TileChestHungryEnder.class.getSimpleName());
		GameRegistry.registerBlock(BlockChestHungryEnder.INSTANCE, BlockChestHungryEnder.INSTANCE.getUnlocalizedName());

		GameRegistry.registerTileEntity(TileArcaneLampRedstone.class, TileArcaneLampRedstone.class.getSimpleName());
		GameRegistry.registerBlock(BlockArcaneLampRedstone.ACTIVE, BlockArcaneLampRedstoneItem.class, BlockArcaneLampRedstone.ACTIVE.getUnlocalizedName() + "_on");
		GameRegistry.registerBlock(BlockArcaneLampRedstone.INACTIVE, BlockArcaneLampRedstoneItem.class, BlockArcaneLampRedstone.INACTIVE.getUnlocalizedName() + "_off");

		GameRegistry.registerTileEntity(TileEverfullUrn.class, TileEverfullUrn.class.getSimpleName());
		GameRegistry.registerBlock(BlockEverfullUrn.INSTANCE, BlockEverfullUrn.INSTANCE.getUnlocalizedName());
		GameRegistry.registerBlock(BlockVoidstone.INSTANCE, BlockVoidstone.INSTANCE.getUnlocalizedName());

		GameRegistry.registerTileEntity(TileInfuser.class, TileInfuser.class.getSimpleName());
		GameRegistry.registerTileEntity(TileInfuserDark.class, TileInfuserDark.class.getSimpleName());
		GameRegistry.registerBlock(BlockInfuser.INSTANCE, BlockInfuserItem.class, BlockInfuser.INSTANCE.getUnlocalizedName());

		GameRegistry.registerBlock(BlockEntropicDispenser.INSTANCE, BlockEntropicDispenser.INSTANCE.getUnlocalizedName());

		GameRegistry.registerBlock(BlockArcaneWood.INSTANCE, BlockArcaneWood.INSTANCE.getUnlocalizedName());

		OreDictionary.registerOre("logWood", BlockArcaneWood.INSTANCE);
		ThaumcraftApi.portableHoleBlackList.add(BlockArcaneWood.INSTANCE);

		GameRegistry.registerItem(ItemShardFragment.INSTANCE, ItemShardFragment.INSTANCE.getUnlocalizedName());
		GameRegistry.registerItem(ItemShardDull.INSTANCE, ItemShardDull.INSTANCE.getUnlocalizedName());
		GameRegistry.registerItem(ItemDawnstone.INSTANCE, ItemDawnstone.INSTANCE.getUnlocalizedName());
		RegistrationUtil.registerItem(Thaumcraft.MODID, ItemDummy.INFERNAL_FURNACE);
	}

	@Handler
	public void postInit(FMLPostInitializationEvent event) {
		if (Magistics.configHandler.sortTCTabsContent) {
			CreativeTabs tabTCBasics = new CreativeTabCustom(Magistics.MODID.toLowerCase() + ".basics", ConfigItems.itemThaumonomicon);
			ConfigItems.itemThaumonomicon.setCreativeTab(tabTCBasics);

			ItemStack sceptre = new ItemStack(ConfigItems.itemWandCasting, 1, (int) (ConfigItems.WAND_CAP_THAUMIUM.getCraftCost() * ConfigItems.WAND_ROD_SILVERWOOD.getCraftCost() * 1.5F));
			((ItemWandCasting) sceptre.getItem()).setCap(sceptre, ConfigItems.WAND_CAP_THAUMIUM);
			((ItemWandCasting) sceptre.getItem()).setRod(sceptre, ConfigItems.WAND_ROD_SILVERWOOD);

			CreativeTabs tabTCThaumaturgy = new CreativeTabCustom(Magistics.MODID.toLowerCase() + ".thaumaturgy", sceptre);

			CreativeTabs tabTCAlchemy = new CreativeTabCustom(Magistics.MODID.toLowerCase() + ".alchemy", new ItemStack(ConfigBlocks.blockMetalDevice, 1, 0));

			CreativeTabs tabTCArtifice = new CreativeTabCustom(Magistics.MODID.toLowerCase() + ".artifice", new ItemStack(ConfigBlocks.blockTable, 1, 15));

			CreativeTabs tabTCGolemancy = new CreativeTabCustom(Magistics.MODID.toLowerCase() + ".golemancy", ConfigItems.itemGolemPlacer);

			CreativeTabs tabTCEldritch = new CreativeTabCustom(Magistics.MODID.toLowerCase() + ".eldritch", new ItemStack(ConfigItems.itemEldritchObject, 1, 1));
		}

		RecipeHandler.registerRecipes();
		ResearchHandler.registerResearch();
	}

	@Override
	public void registerRenderInformation() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryTrapped.class, RenderChestHungryTrapped.INSTANCE);
		RenderingRegistry.registerBlockHandler(new RenderBlockChest(BlockChestHungryTrapped.INSTANCE.getRenderType(), new TileChestHungryTrapped()));

		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, RenderChestHungryEnder.INSTANCE);
		RenderingRegistry.registerBlockHandler(new RenderBlockChest(BlockChestHungryEnder.INSTANCE.getRenderType(), new TileChestHungryEnder()));

		ClientRegistry.bindTileEntitySpecialRenderer(TileArcaneLampRedstone.class, new TileArcaneLampRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChthonianFurnace.class, RenderChthonianFurnace.INSTANCE);
		RenderingRegistry.registerBlockHandler(RenderBlockChthonianFurnace.INSTANCE);
		RenderingRegistry.registerBlockHandler(RenderBlockEverfullUrn.INSTANCE);

		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, RenderInfuser.INSTANCE);
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuserDark.class, RenderInfuser.INSTANCE);
		RenderingRegistry.registerBlockHandler(RenderBlockInfuser.INSTANCE);

		int newBlockLootCrateRI = RenderingRegistry.getNextAvailableRenderId();
		ConfigBlocks.blockLootCrateRI = newBlockLootCrateRI;
		RenderingRegistry.registerBlockHandler(new RenderBlockLootCrate(newBlockLootCrateRI));

		int newBlockLootUrnRI = RenderingRegistry.getNextAvailableRenderId();
		ConfigBlocks.blockLootUrnRI = newBlockLootUrnRI;
		RenderingRegistry.registerBlockHandler(new RenderBlockLootUrn(newBlockLootUrnRI));
	}
}