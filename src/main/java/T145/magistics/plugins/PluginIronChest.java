package T145.magistics.plugins;

import T145.magistics.api.internal.IMagisticsPlugin;
import T145.magistics.api.internal.MagisticsPlugin;
import T145.magistics.blocks.devices.BlockChestHungryMetal;
import T145.magistics.client.render.blocks.RenderChestHungryMetal;
import T145.magistics.core.Init.ClientRegistrationHandler;
import T145.magistics.tiles.devices.TileChestHungryMetal;
import cpw.mods.ironchest.common.blocks.chest.IronChestType;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@MagisticsPlugin(modid = "ironchest")
public class PluginIronChest implements IMagisticsPlugin {

	public static final BlockChestHungryMetal CHEST_HUNGRY_METAL = new BlockChestHungryMetal();;

	@Override
	public void preInit(FMLPreInitializationEvent event) {}

	@Override
	public void init(FMLInitializationEvent event) {}

	@Override
	public void postInit(FMLPostInitializationEvent event) {}

	@Override
	public void initClient() {
		for (IronChestType type : IronChestType.values()) {
			ClientRegistrationHandler.registerBlockModel(CHEST_HUNGRY_METAL, type.ordinal(), ClientRegistrationHandler.getVariantName(type));
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new RenderChestHungryMetal());
	}
}