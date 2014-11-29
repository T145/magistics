package T145.magistics.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	public void registerRenderInformation() {}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}
}