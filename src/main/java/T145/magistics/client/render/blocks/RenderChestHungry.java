package T145.magistics.client.render.blocks;

import T145.magistics.Magistics;
import T145.magistics.client.lib.Render;
import T145.magistics.tiles.devices.TileChestHungry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChestHungry extends TileEntitySpecialRenderer<TileChestHungry> {

	@Override
	public void renderTileEntityAt(TileChestHungry chest, double x, double y, double z, float partialTicks, int destroyStage) {
		Render.chest(new ResourceLocation(Magistics.MODID, "textures/models/chest_hungry/" + chest.getType().getName() + ".png"), chest.getFront(), chest.lidAngle, chest.prevLidAngle, x, y, z, partialTicks, destroyStage);
	}
}