package T145.magistics.client.renderers.elysium;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex3d;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import T145.magistics.common.tiles.TileElysianPortal;

public class ElysiumTileEntityPortalRenderer extends TileEntitySpecialRenderer
{

	public void renderTileEntityPortalAt(TileElysianPortal tile)
	{
		glDisable(GL_LIGHTING);
		glDisable(GL_CULL_FACE);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glColor4f(1, 1, 1, tile.alpha);
		this.bindTexture(new ResourceLocation("elysium:textures/misc/beam.png"));

		int faces = 16;
		glBegin(GL_QUADS);
		for(int i = 0; i < faces; i++)
		{
			glTexCoord2f(0, tile.texPos);
			glVertex3d(tile.radius*Math.cos(Math.PI*2*i/faces), -0.5, tile.radius*Math.sin(Math.PI*2*i/faces));
			glTexCoord2f(0, 100+tile.texPos);
			glVertex3d(tile.radius*Math.cos(Math.PI*2*i/faces), 99.5, tile.radius*Math.sin(Math.PI*2*i/faces));
			glTexCoord2f(16/faces, 100+tile.texPos);
			glVertex3d(tile.radius*Math.cos(Math.PI*2*(i+1)/faces), 99.5, tile.radius*Math.sin(Math.PI*2*(i+1)/faces));
			glTexCoord2f(16/faces, tile.texPos);
			glVertex3d(tile.radius*Math.cos(Math.PI*2*(i+1)/faces), -0.5, tile.radius*Math.sin(Math.PI*2*(i+1)/faces));
		}
		glEnd();

		glDisable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		glEnable(GL_LIGHTING);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float par8)
	{
		TileElysianPortal portalTile = (TileElysianPortal) tile;

		if(portalTile.canstay)
		{
			glPushMatrix();
				glTranslated(x+0.5, y, z+0.5);
				glRotatef(portalTile.rotation, 0, 1, 0);
				this.renderTileEntityPortalAt(portalTile);
			glPopMatrix();
		}
	}
}
