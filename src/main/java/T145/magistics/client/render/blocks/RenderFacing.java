package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderFacing<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

	@Override
	public abstract void renderTileEntityAt(@Nonnull T tile, double x, double y, double z, float partialTicks, int destroyStage);

	protected int getAngleFromFront(int front, boolean opposite) {
		switch (front) {
		case 2:
			return 180;
		case 4:
			return opposite ? 90 : -90;
		case 5:
			return opposite ? -90 : 90;
		default:
			return 0;
		}
	}
}