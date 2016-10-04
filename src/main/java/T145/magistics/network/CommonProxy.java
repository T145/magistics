package T145.magistics.network;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.pulses.ServerPulse;
import T145.magistics.api.tiles.TileFurnace;
import T145.magistics.containers.ContainerCrystalizer;
import T145.magistics.containers.ContainerFurnace;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.containers.ContainerInfuserDark;
import T145.magistics.items.wands.WandManager;
import T145.magistics.tiles.TileCrystalizer;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {
	public WandManager wandManager = new WandManager();

	protected List<ServerPulse> pulses = new ArrayList<ServerPulse>();

	public void addPulse(ServerPulse pulse) {
		if (pulse.isLoaded()) {
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
		case 2:
			return new ContainerCrystalizer(player.inventory, (TileCrystalizer) tile);
		case 3:
			return new ContainerFurnace(player.inventory, (TileFurnace) tile);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}