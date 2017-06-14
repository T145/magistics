package T145.magistics.plugins;

import T145.magistics.api.IMagisticsPlugin;
import T145.magistics.api.MagisticsPlugin;
import T145.magistics.blocks.devices.BlockChestHungryMetal;
import T145.magistics.client.lib.ModelBakery;
import T145.magistics.client.render.blocks.RenderChestHungryMetal;
import T145.magistics.tiles.devices.TileChestHungryMetal;
import cpw.mods.ironchest.common.blocks.chest.IronChestType;
import net.minecraft.block.Block;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@MagisticsPlugin(modid = "ironchest")
public class PluginIronChest implements IMagisticsPlugin {

	public static Block chestHungryMetal;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		chestHungryMetal = new BlockChestHungryMetal();
	}

	@Override
	public void init(FMLInitializationEvent event) {}

	@Override
	public void postInit(FMLPostInitializationEvent event) {}

	@Override
	@SideOnly(Side.CLIENT)
	public void initClient() {
		for (IronChestType type : IronChestType.values()) {
			ModelBakery.registerBlockModel(chestHungryMetal, type.ordinal(), ModelBakery.getVariantName(type));
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new RenderChestHungryMetal());
	}
}