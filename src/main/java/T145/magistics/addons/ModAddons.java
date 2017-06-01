package T145.magistics.addons;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModAddons {

	public static List<AddonBase> addons = new ArrayList<AddonBase>();

	static {
		addons.add(new AddonIronChest());
	}

	public static void preInit(FMLPreInitializationEvent event) {
		for (AddonBase addon : addons) {
			if (addon.isLoaded()) {
				Magistics.LOGGER.info("Loaded addon: " + addon.getModId());
				addon.preInit(event);
			}
		}
	}

	public static void init(FMLInitializationEvent event) {
		for (AddonBase addon : addons) {
			if (addon.isLoaded()) {
				addon.init(event);
			}
		}
	}

	public static void postInit(FMLPostInitializationEvent event) {
		for (AddonBase addon : addons) {
			if (addon.isLoaded()) {
				addon.postInit(event);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		for (AddonBase addon : addons) {
			if (addon.isLoaded()) {
				addon.initClient();
			}
		}
	}
}