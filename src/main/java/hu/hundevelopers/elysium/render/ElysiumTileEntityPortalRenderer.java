package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.tile.ElysianTileEntityPortal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

public class ElysiumTileEntityPortalRenderer extends TileEntitySpecialRenderer
{

	public void renderTileEntityPortalAt(ElysianTileEntityPortal tile)
	{
		glDisable(GL_LIGHTING);
		glDisable(GL_CULL_FACE);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glColor4f(1, 1, 1, tile.alpha);
		this.bindTexture(new ResourceLocation(Elysium.ID + ":textures/misc/beam.png"));

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
		ElysianTileEntityPortal portalTile = (ElysianTileEntityPortal) tile;

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
