package T145.magistics.client.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.client.gui.GuiThinkTank;
import T145.magistics.common.network.CommonProxy;
import T145.magistics.common.tiles.TileThinkTank;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		reg.registerRenderers();
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);

		switch (ID) {
		case 0:
			return new GuiThinkTank(player.inventory, (TileThinkTank) tile);
		default:
			return null;
		}
	}
}