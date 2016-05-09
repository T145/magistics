package T145.magistics.network;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.client.gui.GuiModifiedFurnace;
import T145.magistics.tiles.TileNetherFurnace;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (world instanceof WorldClient) {
			switch (ID) {
			case 0:
				return new GuiModifiedFurnace(player.inventory, (TileNetherFurnace) world.getTileEntity(x, y, z));
			}
		}
		return null;
	}
}