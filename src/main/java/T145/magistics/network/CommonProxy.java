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

public class CommonProxy implements IGuiHandler {
	protected static PulseManager pulser = new PulseManager(Magistics.MODID + "Modules", new ForgeCFG(Magistics.MODID, "Modules: Disabling these will disable a chunk of the mod"));
	protected static Map<String, CorePulse> pulses = new HashMap<String, CorePulse>();

	public void addPulse(String modid, CorePulse pulse) {
		if (Loader.isModLoaded(modid)) {
			pulser.registerPulse(pulse);
		}

		if (pulser.isPulseLoaded(modid)) {
			pulses.put(modid, pulse);
		}
	}

	public void preInit(FMLPreInitializationEvent event) {
		pulser.preInit(event);
	}

	public void init(FMLInitializationEvent event) {
		pulser.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		pulser.postInit(event);
	}

	public void registerRenderInformation() {}

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
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}