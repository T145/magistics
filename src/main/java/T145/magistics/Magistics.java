package T145.magistics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

import thaumcraft.common.Thaumcraft;
import T145.magistics.config.ConfigHandler;
import T145.magistics.lib.CreativeTabMagistics;
import T145.magistics.lib.events.WorldEventHandler;
import T145.magistics.network.CommonProxy;
import T145.magistics.pulses.PulseThaumcraft;
import T145.magistics.pulses.PulseWaila;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Magistics.MODID, name = Magistics.MODID, version = Magistics.VERSION, dependencies = "required-after:Thaumcraft", guiFactory = "T145.magistics.client.ModGuiFactory")
public class Magistics {
	public static final String MODID = "Magistics", VERSION = "$version";

	@Instance(MODID)
	public static Magistics instance;

	@SidedProxy(clientSide = "T145.magistics.network.ClientProxy", serverSide = "T145.magistics.network.CommonProxy")
	public static CommonProxy proxy;

	@Metadata
	private ModMetadata metadata;

	public static CreativeTabs tabMagistics = new CreativeTabMagistics(MODID);
	public static ConfigHandler configHandler;
	public static Logger logger;

	private void loadMetadata() {
		metadata.autogenerated = false;
		metadata.modId = MODID;
		metadata.name = MODID;
		metadata.version = VERSION;
		metadata.description = "Adding some logistics to Thaumcraft!";
		metadata.url = "https://github.com/T145/magistics";
		metadata.updateUrl = "https://github.com/T145/magistics/releases";
		metadata.authorList.add("T145");
		metadata.credits = "Special thanks to everyone who kept believing!";
		metadata.logoFile = "logo.png";
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		loadMetadata();

		proxy.addPulse(Thaumcraft.MODID, new PulseThaumcraft());
		proxy.addPulse("Waila", new PulseWaila());
		proxy.preInit(event);

		configHandler = new ConfigHandler(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(configHandler);
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
		proxy.init(event);
		proxy.registerRenderInformation();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}