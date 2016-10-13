package T145.magistics.network;

import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.render.RenderInfuser;
import T145.magistics.load.ModBlocks;
import T145.magistics.tiles.TileInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		switch (ID) {
		case 0:
			return new GuiInfuser(player.inventory, (TileInfuser) world.getTileEntity(pos));
		default:
			return null;
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		ModBlocks.initModelsAndVariants();
		initRenderers();
	}

	private void initRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}