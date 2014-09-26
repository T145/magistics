package T145.magistics.net;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.client.renderers.block.BlockChestHungryEnderRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryMetalItemRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.common.config.MagisticsConfig;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;

public class UniversalProxy implements IGuiHandler {
	public static int renderID[] = new int[MagisticsConfig.blocks.length];

	public void registerRenderInformation() {
		for (int i = 0; i <= renderID.length - 1; i++)
			renderID[i] = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new BlockChestHungryEnderRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MagisticsConfig.blocks[1]), new BlockChestHungryMetalItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
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