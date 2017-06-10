package T145.magistics.client.lib;

import java.awt.Color;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public class TexturedQuad {

	public static final VertexFormat VERTEXFORMAT_POS_TEX_CO_LM_NO = new VertexFormat()
			.addElement(DefaultVertexFormats.POSITION_3F)
			.addElement(DefaultVertexFormats.TEX_2F)
			.addElement(DefaultVertexFormats.COLOR_4UB)
			.addElement(DefaultVertexFormats.TEX_2S)
			.addElement(DefaultVertexFormats.NORMAL_3B)
			.addElement(DefaultVertexFormats.PADDING_1B);

	public PositionTextureVertex[] vertexPositions;
	public int nVertices;
	private boolean invertNormal;
	private boolean flipped = false;

	public TexturedQuad(PositionTextureVertex[] vertices) {
		vertexPositions = vertices;
		nVertices = vertices.length;
	}

	public TexturedQuad(PositionTextureVertex[] vertices, int texcoordU1, int texcoordV1, int texcoordU2, int texcoordV2, float textureWidth, float textureHeight) {
		this(vertices);
		float f2 = 0.0F / textureWidth;
		float f3 = 0.0F / textureHeight;
		vertices[0] = vertices[0].setTexturePosition(texcoordU2 / textureWidth - f2, texcoordV1 / textureHeight + f3);
		vertices[1] = vertices[1].setTexturePosition(texcoordU1 / textureWidth + f2, texcoordV1 / textureHeight + f3);
		vertices[2] = vertices[2].setTexturePosition(texcoordU1 / textureWidth + f2, texcoordV2 / textureHeight - f3);
		vertices[3] = vertices[3].setTexturePosition(texcoordU2 / textureWidth - f2, texcoordV2 / textureHeight - f3);
	}

	public void flipFace() {
		flipped = true;

		PositionTextureVertex[] avertex = new PositionTextureVertex[vertexPositions.length];

		for (int i = 0; i < vertexPositions.length; i++) {
			avertex[i] = vertexPositions[(vertexPositions.length - i - 1)];
		}

		vertexPositions = avertex;
	}

	public void draw(VertexBuffer renderer, float scale, int bright, int color, float alpha) {
		if (bright != -99) {
			renderer.begin(7, VERTEXFORMAT_POS_TEX_CO_LM_NO);
		} else {
			renderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
		}

		Color c = new Color(color);

		int aa = bright;
		int j = aa >> 16 & 0xFFFF;
		int k = aa & 0xFFFF;

		for (int i = 0; i < 4; i++) {
			PositionTextureVertex vertex = vertexPositions[i];

			if (bright != -99) {
				renderer.pos(vertex.vector3D.xCoord * scale, vertex.vector3D.yCoord * scale, vertex.vector3D.zCoord * scale)
				.tex(vertex.texturePositionX, vertex.texturePositionY)
				.color(c.getRed(), c.getGreen(), c.getBlue(), (int) (alpha * 255.0F)).lightmap(j, k)
				.normal(0.0F, 0.0F, 1.0F).endVertex();
			} else {
				renderer.pos(vertex.vector3D.xCoord * scale, vertex.vector3D.yCoord * scale, vertex.vector3D.zCoord * scale)
				.tex(vertex.texturePositionX, vertex.texturePositionY)
				.color(c.getRed(), c.getGreen(), c.getBlue(), (int) (alpha * 255.0F)).normal(0.0F, 0.0F, 1.0F)
				.endVertex();
			}
		}

		Tessellator.getInstance().draw();
	}

	public void draw(VertexBuffer renderer, float scale, int bright, int[] color, float[] alpha) {
		if (bright != -99) {
			renderer.begin(7, VERTEXFORMAT_POS_TEX_CO_LM_NO);
		} else {
			renderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
		}

		int aa = bright;
		int j = aa >> 16 & 0xFFFF;
		int k = aa & 0xFFFF;

		for (int i = 0; i < 4; i++) {
			int idx = flipped ? 3 - i : i;
			Color c = new Color(color[idx]);
			PositionTextureVertex vertex = vertexPositions[i];

			if (bright != -99) {
				renderer.pos(vertex.vector3D.xCoord * scale, vertex.vector3D.yCoord * scale, vertex.vector3D.zCoord * scale)
				.tex(vertex.texturePositionX, vertex.texturePositionY)
				.color(c.getRed(), c.getGreen(), c.getBlue(), (int) (alpha[idx] * 255.0F)).lightmap(j, k)
				.normal(0.0F, 0.0F, 1.0F).endVertex();
			} else {
				renderer.pos(vertex.vector3D.xCoord * scale, vertex.vector3D.yCoord * scale, vertex.vector3D.zCoord * scale)
				.tex(vertex.texturePositionX, vertex.texturePositionY)
				.color(c.getRed(), c.getGreen(), c.getBlue(), (int) (alpha[idx] * 255.0F))
				.normal(0.0F, 0.0F, 1.0F).endVertex();
			}
		}

		Tessellator.getInstance().draw();
	}
}