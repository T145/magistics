package T145.magistics.client.render;

import javax.annotation.Nonnull;

import T145.magistics.tiles.TileConduit;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConduit extends TileEntitySpecialRenderer<TileConduit> {

	@Override
	public void renderTileEntityAt(@Nonnull TileConduit infuser, double x, double y, double z, float partialTicks, int destroyStage) {
		
	}
}