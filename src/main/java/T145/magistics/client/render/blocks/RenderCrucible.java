package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import T145.magistics.tiles.machines.TileCrucible;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrucible extends TileEntitySpecialRenderer<TileCrucible> {

	@Override
	public void renderTileEntityAt(@Nonnull TileCrucible crucible, double x, double y, double z, float partialTicks, int destroyStage) {
	}
}