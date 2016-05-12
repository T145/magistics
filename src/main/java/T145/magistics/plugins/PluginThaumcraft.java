package T145.magistics.plugins;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchCategories;
import T145.magistics.Magistics;
import T145.magistics.blocks.BlockChestHungryEnder;
import T145.magistics.blocks.BlockChestHungryTrapped;
import T145.magistics.blocks.BlockChthonianFurnace;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.client.render.blocks.RenderBlockChest;
import T145.magistics.client.render.blocks.RenderBlockChthonianFurnace;
import T145.magistics.client.render.tiles.RenderChestHungryEnder;
import T145.magistics.client.render.tiles.RenderChestHungryTrapped;
import T145.magistics.client.render.tiles.RenderChthonianFurnace;
import T145.magistics.plugins.PluginHandler.Plugin;
import T145.magistics.tiles.TileChestHungryEnder;
import T145.magistics.tiles.TileChestHungryTrapped;
import T145.magistics.tiles.TileChthonianFurnace;
import T145.magistics.tiles.TileNetherFurnace;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class PluginThaumcraft extends Plugin {
	public PluginThaumcraft() {
		super("Thaumcraft");
	}

	@Override
	public void preInit() {}

	@Override
	public void init() {
		GameRegistry.registerTileEntity(TileNetherFurnace.class, TileNetherFurnace.class.getSimpleName());
		GameRegistry.registerBlock(BlockNetherFurnace.ACTIVE, BlockNetherFurnace.ACTIVE.getUnlocalizedName() + "_on");
		GameRegistry.registerBlock(BlockNetherFurnace.INACTIVE, BlockNetherFurnace.INACTIVE.getUnlocalizedName() + "_off");
		GameRegistry.registerTileEntity(TileChestHungryTrapped.class, TileChestHungryTrapped.class.getSimpleName());
		GameRegistry.registerBlock(BlockChestHungryTrapped.INSTANCE, BlockChestHungryTrapped.INSTANCE.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileChestHungryEnder.class, TileChestHungryEnder.class.getSimpleName());
		GameRegistry.registerBlock(BlockChestHungryEnder.INSTANCE, BlockChestHungryEnder.INSTANCE.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileChthonianFurnace.class, TileChthonianFurnace.class.getSimpleName());
		GameRegistry.registerBlock(BlockChthonianFurnace.INSTANCE, BlockChthonianFurnace.INSTANCE.getUnlocalizedName());
	}

	@Override
	public void postInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryTrapped.class, RenderChestHungryTrapped.INSTANCE);
		RenderingRegistry.registerBlockHandler(new RenderBlockChest(BlockChestHungryTrapped.INSTANCE.getRenderType(), new TileChestHungryTrapped()));
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, RenderChestHungryEnder.INSTANCE);
		RenderingRegistry.registerBlockHandler(new RenderBlockChest(BlockChestHungryEnder.INSTANCE.getRenderType(), new TileChestHungryEnder()));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileChthonianFurnace.class, RenderChthonianFurnace.INSTANCE);
		RenderingRegistry.registerBlockHandler(new RenderBlockChthonianFurnace());
		ResearchCategories.registerCategory(Magistics.MODID, new ResourceLocation("magistics", "textures/gui/thaumonomicon/tab.png"), new ResourceLocation("magistics", "textures/gui/thaumonomicon/bg.png"));
	}
}