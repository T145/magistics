package T145.magistics.net;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.client.renderers.block.BlockChestHungryModRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryModRenderer;
import T145.magistics.common.config.MagisticsConfig;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;

public class UniversalProxy implements IGuiHandler {
	public static int renderID[] = new int[MagisticsConfig.block.length - 1];

	private static Object blockRenderer[] = new Object[] {
		new BlockChestHungryModRenderer()
	}, tileRenderer[] = new Object[] {
		new TileChestHungryModRenderer()
	};

	public void registerRenderInformation() {
		for (int i = 0; i <= renderID.length; i++)
			renderID[i] = RenderingRegistry.getNextAvailableRenderId();
		for (int j = 0; j <= blockRenderer.length; j++)
			RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler) blockRenderer[j]);
		for (int k = 0; k <= tileRenderer.length; k++)
			ClientRegistry.bindTileEntitySpecialRenderer(MagisticsConfig.tile[k], (TileEntitySpecialRenderer) tileRenderer[k]);
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