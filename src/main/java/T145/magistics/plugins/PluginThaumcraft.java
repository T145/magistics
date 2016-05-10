package T145.magistics.plugins;

import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.plugins.PluginHandler.Plugin;
import T145.magistics.tiles.TileNetherFurnace;
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
		GameRegistry.registerBlock(BlockNetherFurnace.instanceActive, BlockNetherFurnace.instanceActive.getUnlocalizedName() + "_on");
		GameRegistry.registerBlock(BlockNetherFurnace.instanceInactive, BlockNetherFurnace.instanceInactive.getUnlocalizedName() + "_off");
	}

	@Override
	public void postInit() {}
}