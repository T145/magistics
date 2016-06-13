package T145.magistics.network;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.containers.ContainerInfuserDark;
import T145.magistics.pulses.core.ServerPulse;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	protected List<ServerPulse> pulses = new ArrayList<ServerPulse>();

	public void addPulse(ServerPulse pulse) {
		if (Loader.isModLoaded(pulse.getModId())) {
			pulses.add(pulse);
		}
	}

	public void preInit(FMLPreInitializationEvent event) {
		for (ServerPulse pulse : pulses) {
			pulse.preInit(event);
		}
	}

	public void init(FMLInitializationEvent event) {
		for (ServerPulse pulse : pulses) {
			pulse.init(event);
		}
	}

	public void postInit(FMLPostInitializationEvent event) {
		for (ServerPulse pulse : pulses) {
			pulse.postInit(event);
		}
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