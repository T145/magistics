package T145.magistics.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import T145.magistics.client.renderers.block.BlockChestHungryEnderItemRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryMetalItemRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.config.MagisticsConfig;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	public static IItemRenderer[] itemRenderers = {
		new BlockChestHungryEnderItemRenderer(),
		new BlockChestHungryMetalItemRenderer()
	};

	@Override
	public void registerRenderInformation() {
		for (int i = 0; i <= itemRenderers.length - 1; i++)
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MagisticsConfig.blocks[i]), itemRenderers[i]);
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}