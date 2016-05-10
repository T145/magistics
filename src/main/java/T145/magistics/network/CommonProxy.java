package T145.magistics.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.plugins.PluginHandler;
import T145.magistics.tiles.TileNetherFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
		default:
			return null;
		}
	}
}