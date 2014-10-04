package T145.magistics.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import T145.magistics.common.config.MagisticsConfig;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Magistics.modid, version = "0.6.5", guiFactory = "T145.magistics.client.gui.config.MagisticsConfigGuiFactory", dependencies = "after:Thaumcraft")
public class Magistics {
	public static final String modid = "Magistics";

	@Instance(modid)
	public static Magistics instance;

	@SidedProxy(clientSide = "T145.magistics.client.ClientProxy", serverSide = "T145.magistics.common.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid.toLowerCase()) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(MagisticsConfig.blocks[0]);
		}
	};

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.modID.equals(modid))
			MagisticsConfig.sync();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		FMLCommonHandler.instance().bus().register(instance);
		MagisticsConfig.preInit(e.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MagisticsConfig.init();
		proxy.registerRenderInformation();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		MagisticsConfig.postInit();
	}
}