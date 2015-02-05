package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.tiles.TileSortingChestHungry;

import com.dynious.refinedrelocation.lib.Resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileSortingChestHungryRenderer extends TileEntitySpecialRenderer {
	public final ModelChest model = new ModelChest();
	public static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("thaumcraft", "textures/models/chesthungry.png");

	public void render(TileSortingChestHungry tile, double par2, double par4, double par6, float par8) {
		int i = 0;

		if (tile.hasWorldObj())
			i = tile.getFacing();

		if (MinecraftForgeClient.getRenderPass() == 0)
			bindTexture(RES_NORMAL_SINGLE);
		else
			bindTexture(Resources.MODEL_TEXTURE_OVERLAY_CHEST);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glTranslatef((float) par2, (float) par4 + 1F, (float) par6 + 1F);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		if (i == 2)
			short1 = 180;
		if (i == 3)
			short1 = 0;
		if (i == 4)
			short1 = 90;
		if (i == 5)
			short1 = -90;

		GL11.glRotatef((float) short1, 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * par8;

		f1 = 1F - f1;
		f1 = 1F - f1 * f1 * f1;
		model.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2F);
		model.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}

	public void renderTileEntityAt(TileEntity tile, double i, double j, double k, float tick) {
		render((TileSortingChestHungry) tile, i, j, k, tick);
	}
}