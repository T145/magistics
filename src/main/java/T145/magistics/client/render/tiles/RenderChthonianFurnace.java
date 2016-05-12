package T145.magistics.client.render.tiles;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;
import T145.magistics.client.render.models.ModelChthonianFurnace;
import T145.magistics.tiles.TileChthonianFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChthonianFurnace extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderChthonianFurnace();
	private ModelChthonianFurnace model = new ModelChthonianFurnace();

	public void renderTileEntityAt(TileChthonianFurnace table, double par2, double par4, double par6, float par8) {
		GL11.glPushMatrix();
		UtilsFX.bindTexture("magistics", "textures/models/chthonian_furnace.png");
		GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 1.5F, (float) par6 + 0.5F);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		model.renderAll();
		GL11.glPopMatrix();
	}

	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderTileEntityAt((TileChthonianFurnace) par1TileEntity, par2, par4, par6, par8);
	}
}
