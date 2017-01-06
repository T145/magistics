package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.Magistics;
import T145.magistics.client.lib.StructBox;
import T145.magistics.client.lib.StructUV;
import T145.magistics.client.lib.events.IconAtlas;
import T145.magistics.client.lib.utils.UtilsFX;
import T145.magistics.tiles.TileConduit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConduit extends TileEntitySpecialRenderer<TileConduit> {

	public StructBox up = new StructBox(0.375, 0.625, 0.375, 0.625, 1.0, 0.625,
			new StructUV[] { new StructUV(12, 12, 16, 16, 16, 16), new StructUV(12, 12, 16, 16, 16, 16),
					new StructUV(12, 12, 16, 6, 16, 16), new StructUV(12, 12, 16, 6, 16, 16),
					new StructUV(12, 12, 16, 6, 16, 16), new StructUV(12, 12, 16, 6, 16, 16) });
	public StructBox down = new StructBox(0.375, 0.375, 0.375, 0.625, 0, 0.625,
			new StructUV[] { new StructUV(12, 12, 16, 16, 16, 16), new StructUV(12, 12, 16, 16, 16, 16),
					new StructUV(12, 12, 16, 6, 16, 16), new StructUV(12, 12, 16, 6, 16, 16),
					new StructUV(12, 12, 16, 6, 16, 16), new StructUV(12, 12, 16, 6, 16, 16) });
	public StructBox north = new StructBox(0.375, 0.375, 0.375, 0.625, 0.625, 0,
			new StructUV[] { new StructUV(0, 12, 6, 16, 16, 16), new StructUV(0, 12, 6, 16, 16, 16),
					new StructUV(12, 12, 16, 16, 16, 16), new StructUV(12, 12, 16, 16, 16, 16),
					new StructUV(0, 12, 6, 16, 16, 16), new StructUV(0, 12, 6, 16, 16, 16) });
	public StructBox south = new StructBox(0.375, 0.375, 0.625, 0.625, 0.625, 1.0,
			new StructUV[] { new StructUV(6, 12, 0, 16, 16, 16), new StructUV(0, 12, 6, 16, 16, 16),
					new StructUV(12, 12, 16, 16, 16, 16), new StructUV(12, 12, 16, 16, 16, 16),
					new StructUV(0, 12, 6, 16, 16, 16), new StructUV(0, 12, 6, 16, 16, 16) });
	public StructBox west = new StructBox(0.375, 0.375, 0.375, 0, 0.625, 0.625,
			new StructUV[] { new StructUV(12, 12, 16, 6, 16, 16), new StructUV(12, 12, 16, 6, 16, 16),
					new StructUV(0, 12, 6, 16, 16, 16), new StructUV(0, 12, 6, 16, 16, 16),
					new StructUV(12, 12, 16, 16, 16, 16), new StructUV(12, 12, 16, 16, 16, 16) });
	public StructBox east = new StructBox(0.625, 0.375, 0.375, 1.0, 0.625, 0.625,
			new StructUV[] { new StructUV(12, 12, 16, 6, 16, 16), new StructUV(12, 12, 16, 6, 16, 16),
					new StructUV(0, 12, 6, 16, 16, 16), new StructUV(0, 12, 6, 16, 16, 16),
					new StructUV(12, 12, 16, 16, 16, 16), new StructUV(12, 12, 16, 16, 16, 16) });

	@Override
	public void renderTileEntityFast(@Nonnull TileConduit conduit, double x, double y, double z, float partialTicks, int destroyStage, VertexBuffer buffer) {
		if (conduit.hasConnection(EnumFacing.UP)) {
			UtilsFX.addBoxWithSprite(buffer, up.x1 + x, up.y1 + y, up.z1 + z, up.x2 + x, up.y2 + y, up.z2 + z, IconAtlas.INSTANCE.spriteConduit, up.textures, new int[] { 1, 1, 1, 1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.DOWN)) {
			UtilsFX.addBoxWithSprite(buffer, down.x1 + x, down.y1 + y, down.z1 + z, down.x2 + x, down.y2 + y, down.z2 + z, IconAtlas.INSTANCE.spriteConduit, down.textures, new int[] { -1, -1, 1, 1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.NORTH)) {
			UtilsFX.addBoxWithSprite(buffer, north.x1 + x, north.y1 + y, north.z1 + z, north.x2 + x, north.y2 + y, north.z2 + z, IconAtlas.INSTANCE.spriteConduit, north.textures, new int[] { 1, 1, 1, 1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.SOUTH)) {
			UtilsFX.addBoxWithSprite(buffer, south.x1 + x, south.y1 + y, south.z1 + z, south.x2 + x, south.y2 + y, south.z2 + z, IconAtlas.INSTANCE.spriteConduit, south.textures, new int[] { 1, 1, -1, -1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.EAST)) {
			UtilsFX.addBoxWithSprite(buffer, east.x1 + x, east.y1 + y, east.z1 + z, east.x2 + x, east.y2 + y, east.z2 + z, IconAtlas.INSTANCE.spriteConduit, east.textures, new int[] { 1, 1, 1, 1, -1, -1 });
		}

		if (conduit.hasConnection(EnumFacing.WEST)) {
			UtilsFX.addBoxWithSprite(buffer, west.x1 + x, west.y1 + y, west.z1 + z, west.x2 + x, west.y2 + y, west.z2 + z, IconAtlas.INSTANCE.spriteConduit, west.textures, new int[] { 1, 1, 1, 1, 1, 1 });
		}
	}

	@Override
	public void renderTileEntityAt(@Nonnull TileConduit conduit, double x, double y, double z, float partialTicks, int destroyStage) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Magistics.MODID, "textures/blocks/conduit.png"));
		GlStateManager.disableCull();
		Tessellator tess = Tessellator.getInstance();
		VertexBuffer buffer = tess.getBuffer();

		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

		if (conduit.hasConnection(EnumFacing.UP)) {
			UtilsFX.addBox(buffer, up.x1 + x, up.y1 + y, up.z1 + z, up.x2 + x, up.y2 + y, up.z2 + z, up.textures, new int[] { 1, 1, 1, 1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.DOWN)) {
			UtilsFX.addBox(buffer, down.x1 + x, down.y1 + y, down.z1 + z, down.x2 + x, down.y2 + y, down.z2 + z, down.textures, new int[] { -1, -1, 1, 1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.NORTH)) {
			UtilsFX.addBox(buffer, north.x1 + x, north.y1 + y, north.z1 + z, north.x2 + x, north.y2 + y, north.z2 + z, north.textures, new int[] { 1, 1, 1, 1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.SOUTH)) {
			UtilsFX.addBox(buffer, south.x1 + x, south.y1 + y, south.z1 + z, south.x2 + x, south.y2 + y, south.z2 + z, south.textures, new int[] { 1, 1, -1, -1, 1, 1 });
		}

		if (conduit.hasConnection(EnumFacing.EAST)) {
			UtilsFX.addBox(buffer, east.x1 + x, east.y1 + y, east.z1 + z, east.x2 + x, east.y2 + y, east.z2 + z, east.textures, new int[] { 1, 1, 1, 1, -1, -1 });
		}

		if (conduit.hasConnection(EnumFacing.WEST)) {
			UtilsFX.addBox(buffer, west.x1 + x, west.y1 + y, west.z1 + z, west.x2 + x, west.y2 + y, west.z2 + z, west.textures, new int[] { 1, 1, 1, 1, 1, 1 });
		}

		tess.draw();
		GlStateManager.enableCull();
	}
}