package T145.magistics.client.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderFacing<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

	protected float getAngleFromFront(int front) {
		switch (front) {
		case 2:
			return 180F;
		case 4:
			return -90F;
		case 5:
			return 90F;
		default:
			return 0F;
		}
	}
}