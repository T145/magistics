package T145.magistics.client.render.tiles;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.client.render.models.ModelChthonianFurnace;
import T145.magistics.tiles.TileChthonianFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderChthonianFurnace extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderChthonianFurnace();
	private final ModelChthonianFurnace model = new ModelChthonianFurnace();

	public void renderTileEntityAt(TileChthonianFurnace furnace, float x, float y, float z) {
		GL11.glPushMatrix();
		bindTexture(new ResourceLocation("magistics", "textures/models/chthonian_furnace.png"));
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glTranslatef(x, y + 1F, z + 1F);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		switch (furnace.facing) {
		case 2:
			GL11.glRotatef(180, 0F, 1F, 0F);
			break;
		case 4:
			GL11.glRotatef(90, 0F, 1F, 0F);
			break;
		case 5:
			GL11.glRotatef(-90, 0F, 1F, 0F);
			break;
		}

		GL11.glTranslatef(0F, -1F, 0F);
		model.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		renderTileEntityAt((TileChthonianFurnace) tile, (float) x, (float) y, (float) z);
	}
}