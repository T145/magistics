package T145.magistics.client.render.block;

import T145.magistics.common.tiles.TilePedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPedestal extends TileEntitySpecialRenderer<TilePedestal> {

	@Override
	public void render(TilePedestal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if (!te.isInvalid() && !te.getObservableStack().isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.7, z + 0.5);
			GlStateManager.translate(0, MathHelper.sin((te.getWorld().getTotalWorldTime() + partialTicks) / 10.0F) * 0.1F + 0.1F, 0);
			GlStateManager.scale(0.75, 0.75, 0.75);
			float angle = (te.getWorld().getTotalWorldTime() + partialTicks) / 20.0F * (180F / (float) Math.PI);
			GlStateManager.rotate(angle, 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().getRenderItem().renderItem(te.getObservableStack(), ItemCameraTransforms.TransformType.GROUND);
			GlStateManager.popMatrix();
		}
	}
}