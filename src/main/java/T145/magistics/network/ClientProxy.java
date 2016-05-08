package T145.magistics.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.client.renderers.block.BlockCrystalStorageRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(basicCrystalStorage.getRenderType(), true));
		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(brickCrystalStorage.getRenderType(), true));
		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(plateCrystalStorage.getRenderType(), true));
		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(platformCrystalStorage.getRenderType(), true));
		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(shieldCrystalStorage.getRenderType(), true));
		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(structureCrystalStorage.getRenderType(), false));

		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(lightEngineeringCrystal.getRenderType(), true));
		RenderingRegistry.registerBlockHandler(new BlockCrystalStorageRenderer(darkEngineeringCrystal.getRenderType(), true));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}