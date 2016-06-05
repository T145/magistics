package T145.magistics.network;

import java.util.HashMap;
import java.util.Map;

import mantle.pulsar.config.ForgeCFG;
import mantle.pulsar.control.PulseManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.Magistics;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.containers.ContainerInfuserDark;
import T145.magistics.pulses.core.CorePulse;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;

public abstract class CommonProxy implements IGuiHandler {
	protected static PulseManager pulsar = new PulseManager(Magistics.MODID, new ForgeCFG(Magistics.MODID, "plugins"));
	protected static Map<String, CorePulse> pulses = new HashMap<String, CorePulse>();

	public void addPulse(String modid, CorePulse pulse) {
		if (Loader.isModLoaded(modid)) {
			pulsar.registerPulse(pulse);
		}

		if (pulsar.isPulseLoaded(modid)) {
			pulses.put(modid, pulse);
		}
	}

	public void preInit(FMLPreInitializationEvent event) {
		pulsar.preInit(event);
	}

	public void init(FMLInitializationEvent event) {
		pulsar.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		pulsar.postInit(event);
	}

	public abstract void registerRenderInformation();

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);

		switch (ID) {
		case 0:
			return new ContainerInfuser(player.inventory, (TileInfuser) tile);
		case 1:
			return new ContainerInfuserDark(player.inventory, (TileInfuserDark) tile);
		default:
			return null;
		}
	}

	@Override
	public abstract Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);
}