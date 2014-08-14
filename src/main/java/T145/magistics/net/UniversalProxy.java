package T145.magistics.net;

import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.client.renderers.tile.ChestHungryEnderRenderHelper;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.common.config.MagisticsConfig;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.IGuiHandler;

public class UniversalProxy implements IGuiHandler {
	public static int renderID[] = new int[MagisticsConfig.blocks.length];

	public void registerRenderInformation() {
		TileEntityRendererChestHelper.instance = new ChestHungryEnderRenderHelper();
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
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