package T145.magistics.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModConfig;
import T145.magistics.common.containers.ContainerThinkTank;
import T145.magistics.common.lib.ModRegistry;
import T145.magistics.common.tiles.TileThinkTank;
import cpw.mods.fml.common.network.IGuiHandler;

public abstract class CommonProxy implements IGuiHandler {
	protected ModRegistry reg = Magistics.getRegistry();

	public abstract void registerRenderers();

	public void registerObjects() {
		reg.registerObjects();
		ModConfig.sendInterModComms();
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);

		switch (ID) {
		case 0:
			return new ContainerThinkTank(player.inventory, (TileThinkTank) tile);
		default:
			return null;
		}
	}

	@Override
	public abstract Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);
}