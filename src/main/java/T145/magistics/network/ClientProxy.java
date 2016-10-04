package T145.magistics.network;

import T145.magistics.api.pulses.CorePulse;
import T145.magistics.api.pulses.ServerPulse;
import T145.magistics.api.tiles.TileFurnace;
import T145.magistics.client.gui.GuiCrystalizer;
import T145.magistics.client.gui.GuiFurnace;
import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.gui.GuiInfuserDark;
import T145.magistics.tiles.TileCrystalizer;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		for (ServerPulse pulse : pulses) {
			if (pulse instanceof CorePulse) {
				CorePulse core = (CorePulse) pulse;
				core.registerRenderInformation();
			}
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
			case 2:
				return new GuiCrystalizer(player.inventory, (TileCrystalizer) tile);
			case 3:
				return new GuiFurnace(player.inventory, (TileFurnace) tile);
			}
		}

		return null;
	}
}