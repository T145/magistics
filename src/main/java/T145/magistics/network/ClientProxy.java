package T145.magistics.network;

import T145.magistics.client.fx.particles.ParticleGreenFlame;
import T145.magistics.client.fx.particles.ParticleSmallGreenFlame;
import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.render.RenderInfuser;
import T145.magistics.load.ModBlocks;
import T145.magistics.tiles.TileInfuser;
import net.minecraft.client.Minecraft;
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

	@Override
	public void createGreenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleGreenFlame(world, x, y, z, 0F, 0F, 0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}

	@Override
	public void createSmallGreenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleSmallGreenFlame(world, x, y, z, 0F, 0F, 0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}
}