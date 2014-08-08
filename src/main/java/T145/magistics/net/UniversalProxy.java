package T145.magistics.net;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.common.config.MagisticsConfig;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;

public class UniversalProxy implements IGuiHandler {
	public static int renderID[] = new int[MagisticsConfig.block.length - 1];

	public void registerRenderInformation() {
		for (int i = 0; i <= renderID.length; i++)
			renderID[i] = RenderingRegistry.getNextAvailableRenderId();
	}

	public void setTileRenderer(Class tile, TileEntitySpecialRenderer renderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
	}

	public void setBlockRenderer(ISimpleBlockRenderingHandler renderer) {
		RenderingRegistry.registerBlockHandler(renderer);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		switch (ID) {
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		switch (ID) {
		default:
			return null;
		}
	}
}