package T145.magistics.network;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.gui.GuiInfuserDark;
import T145.magistics.pulses.core.CorePulse;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderInformation() {
		for (CorePulse pulse : pulses.values()) {
			pulse.registerRenderInformation();
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (world instanceof WorldClient) {
			switch (ID) {
			case 0:
				return new GuiInfuser(player.inventory, (TileInfuser) tile);
			case 1:
				return new GuiInfuserDark(player.inventory, (TileInfuserDark) tile);
			}
		}

		return null;
	}
}