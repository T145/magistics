package T145.magistics.client.renderers.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarExtend;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderExtendPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_WORKPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};

	// fields
	private ModelRenderer bottom;
	private ModelRenderer bottoms;
	private ModelRenderer tops;
	private ModelRenderer top;
	private ModelRenderer pillar;
	private ModelRenderer pillarTop;
	private ModelRenderer pillarBottom;
	private ModelRenderer pillarEast;
	private ModelRenderer pillarWest;
	private ModelRenderer pillarNorth;
	private ModelRenderer pillarSouth;

	private ModelRenderer Icicle1A;
	private ModelRenderer Icicle1B;
	private ModelRenderer Icicle1C;
	private ModelRenderer Icicle2A;
	private ModelRenderer Icicle2B;
	private ModelRenderer Icicle2C;
	private ModelRenderer Icicle3A;
	private ModelRenderer Icicle3B;
	private ModelRenderer Icicle4A;
	private ModelRenderer Icicle4B;
	private ModelRenderer Icicle5A;
	private ModelRenderer Icicle5B;
	private ModelRenderer Icicle5C;
	private ModelRenderer Icicle6A;
	private ModelRenderer Icicle6B;
	private ModelRenderer Icicle6C;
	private ModelRenderer Icicle7A;
	private ModelRenderer Icicle7B;
	private ModelRenderer Icicle7C;
	private ModelRenderer Icicle8A;
	private ModelRenderer Icicle8B;
	private ModelRenderer Icicle8C;
	private ModelRenderer Icicle8D;

	private ModelRenderer Icicle9A;
	private ModelRenderer Icicle9B;
	private ModelRenderer Icicle10A;
	private ModelRenderer Icicle10B;
	private ModelRenderer Icicle10C;
	private ModelRenderer Icicle11A;
	private ModelRenderer Icicle11B;
	private ModelRenderer Icicle11C;

	public RenderExtendPillar()
	{

		if(Magistics.proxy.winter)
			this.TEXTURE_WORKPILLAR = new ResourceLocation("craftingpillars:textures/models/extendPillarFrozen.png");
		else
			this.TEXTURE_WORKPILLAR = new ResourceLocation("craftingpillars:textures/models/extendPillar.png");

		model.textureWidth = 128;
		model.textureHeight = 64;

		this.bottom = new ModelRenderer(model, 0, 0);
		this.bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		this.bottom.setRotationPoint(-8F, 22F, -8F);
		this.bottom.setTextureSize(128, 64);
		this.bottom.mirror = true;
		this.setRotation(this.bottom, 0F, 0F, 0F);
		this.bottoms = new ModelRenderer(model, 0, 18);
		this.bottoms.addBox(0F, 0F, 0F, 14, 1, 14);
		this.bottoms.setRotationPoint(-7F, 21F, -7F);
		this.bottoms.setTextureSize(128, 64);
		this.bottoms.mirror = true;
		this.setRotation(this.bottoms, 0F, 0F, 0F);
		this.tops = new ModelRenderer(model, 0, 18);
		this.tops.addBox(0F, 0F, 0F, 14, 1, 14);
		this.tops.setRotationPoint(-7F, 10F, -7F);
		this.tops.setTextureSize(128, 64);
		this.tops.mirror = true;
		this.setRotation(this.tops, 0F, 0F, 0F);
		this.top = new ModelRenderer(model, 64, 0);
		this.top.addBox(0F, 0F, 0F, 16, 2, 16);
		this.top.setRotationPoint(-8F, 8F, -8F);
		this.top.setTextureSize(128, 64);
		this.top.mirror = true;
		this.setRotation(this.top, 0F, 0F, 0F);
		this.pillar = new ModelRenderer(model, 0, 33);
		this.pillar.addBox(0F, 0F, 0F, 12, 10, 12);
		this.pillar.setRotationPoint(-6F, 11F, 6F);
		this.pillar.setTextureSize(128, 64);
		this.pillar.mirror = true;
		this.setRotation(this.pillar, 0F, 1.570796F, 0F);
		this.pillarTop = new ModelRenderer(model, 0, 33);
		this.pillarTop.addBox(0F, 0F, 0F, 12, 3, 12);
		this.pillarTop.setRotationPoint(-6F, 8F, 6F);
		this.pillarTop.setTextureSize(128, 64);
		this.pillarTop.mirror = true;
		this.setRotation(this.pillarTop, 0F, 1.570796F, 0F);
		this.pillarBottom = new ModelRenderer(model, 0, 33);
		this.pillarBottom.addBox(0F, 0F, 0F, 12, 3, 12);
		this.pillarBottom.setRotationPoint(-6F, 21F, 6F);
		this.pillarBottom.setTextureSize(128, 64);
		this.pillarBottom.mirror = true;
		this.setRotation(this.pillarBottom, 0F, 1.570796F, 0F);
		this.pillarEast = new ModelRenderer(model, 0, 33);
		this.pillarEast.addBox(0F, 0F, 0F, 12, 2, 12);
		this.pillarEast.setRotationPoint(-8F, 22F, 6F);
		this.pillarEast.setTextureSize(128, 64);
		this.pillarEast.mirror = true;
		this.setRotation(this.pillarEast, 1.570796F, 1.570796F, 0F);
		this.pillarWest = new ModelRenderer(model, 0, 33);
		this.pillarWest.addBox(0F, 0F, 0F, 12, 2, 12);
		this.pillarWest.setRotationPoint(8F, 10F, 6F);
		this.pillarWest.setTextureSize(128, 64);
		this.pillarWest.mirror = true;
		this.setRotation(this.pillarWest, -1.570796F, 1.570796F, 0F);
		this.pillarNorth = new ModelRenderer(model, 0, 33);
		this.pillarNorth.addBox(0F, 0F, 0F, 12, 2, 12);
		this.pillarNorth.setRotationPoint(-6F, 10F, -8F);
		this.pillarNorth.setTextureSize(128, 64);
		this.pillarNorth.mirror = true;
		this.setRotation(this.pillarNorth, 1.570796F, 0F, 1.570796F);
		this.pillarSouth = new ModelRenderer(model, 0, 33);
		this.pillarSouth.addBox(0F, 0F, 0F, 12, 2, 12);
		this.pillarSouth.setRotationPoint(-6F, 22F, 8F);
		this.pillarSouth.setTextureSize(128, 64);
		this.pillarSouth.mirror = true;
		this.setRotation(this.pillarSouth, -1.570796F, 0F, -1.570796F);

		this.Icicle1A = new ModelRenderer(model, 122, 60);
		this.Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle1A.setRotationPoint(6F, 11F, -5F);
		this.Icicle1A.setTextureSize(128, 64);
		this.Icicle1A.mirror = true;
		this.setRotation(this.Icicle1A, 0F, 0F, 0F);
		this.Icicle1B = new ModelRenderer(model, 124, 58);
		this.Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle1B.setRotationPoint(6F, 11F, -3F);
		this.Icicle1B.setTextureSize(128, 64);
		this.Icicle1B.mirror = true;
		this.setRotation(this.Icicle1B, 0F, 0F, 0F);
		this.Icicle1C = new ModelRenderer(model, 124, 56);
		this.Icicle1C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle1C.setRotationPoint(6F, 13F, -4F);
		this.Icicle1C.setTextureSize(128, 64);
		this.Icicle1C.mirror = true;
		this.setRotation(this.Icicle1C, 0F, 0F, 0F);
		this.Icicle2A = new ModelRenderer(model, 122, 50);
		this.Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle2A.setRotationPoint(6F, 11F, 0F);
		this.Icicle2A.setTextureSize(128, 64);
		this.Icicle2A.mirror = true;
		this.setRotation(this.Icicle2A, 0F, 0F, 0F);
		this.Icicle2B = new ModelRenderer(model, 124, 47);
		this.Icicle2B.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle2B.setRotationPoint(6F, 13F, 0F);
		this.Icicle2B.setTextureSize(128, 64);
		this.Icicle2B.mirror = true;
		this.setRotation(this.Icicle2B, 0F, 0F, 0F);
		this.Icicle2C = new ModelRenderer(model, 124, 54);
		this.Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle2C.setRotationPoint(6F, 11F, -1F);
		this.Icicle2C.setTextureSize(128, 64);
		this.Icicle2C.mirror = true;
		this.setRotation(this.Icicle2C, 0F, 0F, 0F);
		this.Icicle3A = new ModelRenderer(model, 120, 43);
		this.Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
		this.Icicle3A.setRotationPoint(6F, 11F, 3F);
		this.Icicle3A.setTextureSize(128, 64);
		this.Icicle3A.mirror = true;
		this.setRotation(this.Icicle3A, 0F, 0F, 0F);
		this.Icicle3B = new ModelRenderer(model, 124, 40);
		this.Icicle3B.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle3B.setRotationPoint(6F, 12F, 4F);
		this.Icicle3B.setTextureSize(128, 64);
		this.Icicle3B.mirror = true;
		this.setRotation(this.Icicle3B, 0F, 0F, 0F);
		this.Icicle4A = new ModelRenderer(model, 122, 38);
		this.Icicle4A.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle4A.setRotationPoint(3F, 11F, 6F);
		this.Icicle4A.setTextureSize(128, 64);
		this.Icicle4A.mirror = true;
		this.setRotation(this.Icicle4A, 0F, 0F, 0F);
		this.Icicle4B = new ModelRenderer(model, 124, 36);
		this.Icicle4B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle4B.setRotationPoint(4F, 12F, 6F);
		this.Icicle4B.setTextureSize(128, 64);
		this.Icicle4B.mirror = true;
		this.setRotation(this.Icicle4B, 0F, 0F, 0F);
		this.Icicle5A = new ModelRenderer(model, 114, 61);
		this.Icicle5A.addBox(0F, 0F, 0F, 3, 2, 1);
		this.Icicle5A.setRotationPoint(-1F, 11F, 6F);
		this.Icicle5A.setTextureSize(128, 64);
		this.Icicle5A.mirror = true;
		this.setRotation(this.Icicle5A, 0F, 0F, 0F);
		this.Icicle5B = new ModelRenderer(model, 116, 59);
		this.Icicle5B.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle5B.setRotationPoint(-1F, 13F, 6F);
		this.Icicle5B.setTextureSize(128, 64);
		this.Icicle5B.mirror = true;
		this.setRotation(this.Icicle5B, 0F, 0F, 0F);
		this.Icicle5C = new ModelRenderer(model, 120, 56);
		this.Icicle5C.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle5C.setRotationPoint(0F, 14F, 6F);
		this.Icicle5C.setTextureSize(128, 64);
		this.Icicle5C.mirror = true;
		this.setRotation(this.Icicle5C, 0F, 0F, 0F);
		this.Icicle6A = new ModelRenderer(model, 114, 54);
		this.Icicle6A.addBox(0F, 0F, 0F, 4, 1, 1);
		this.Icicle6A.setRotationPoint(-5F, 11F, 6F);
		this.Icicle6A.setTextureSize(128, 64);
		this.Icicle6A.mirror = true;
		this.setRotation(this.Icicle6A, 0F, 0F, 0F);
		this.Icicle6B = new ModelRenderer(model, 116, 52);
		this.Icicle6B.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle6B.setRotationPoint(-4F, 12F, 6F);
		this.Icicle6B.setTextureSize(128, 64);
		this.Icicle6B.mirror = true;
		this.setRotation(this.Icicle6B, 0F, 0F, 0F);
		this.Icicle6C = new ModelRenderer(model, 118, 50);
		this.Icicle6C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle6C.setRotationPoint(-4F, 13F, 6F);
		this.Icicle6C.setTextureSize(128, 64);
		this.Icicle6C.mirror = true;
		this.setRotation(this.Icicle6C, 0F, 0F, 0F);
		this.Icicle7A = new ModelRenderer(model, 104, 59);
		this.Icicle7A.addBox(0F, 0F, 0F, 1, 1, 4);
		this.Icicle7A.setRotationPoint(-7F, 11F, 1F);
		this.Icicle7A.setTextureSize(128, 64);
		this.Icicle7A.mirror = true;
		this.setRotation(this.Icicle7A, 0F, 0F, 0F);
		this.Icicle7B = new ModelRenderer(model, 114, 46);
		this.Icicle7B.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle7B.setRotationPoint(-7F, 12F, 2F);
		this.Icicle7B.setTextureSize(128, 64);
		this.Icicle7B.mirror = true;
		this.setRotation(this.Icicle7B, 0F, 0F, 0F);
		this.Icicle7C = new ModelRenderer(model, 116, 44);
		this.Icicle7C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle7C.setRotationPoint(-7F, 14F, 2F);
		this.Icicle7C.setTextureSize(128, 64);
		this.Icicle7C.mirror = true;
		this.setRotation(this.Icicle7C, 0F, 0F, 0F);
		this.Icicle8A = new ModelRenderer(model, 104, 54);
		this.Icicle8A.addBox(0F, 0F, 0F, 1, 1, 4);
		this.Icicle8A.setRotationPoint(-7F, 11F, -5F);
		this.Icicle8A.setTextureSize(128, 64);
		this.Icicle8A.mirror = true;
		this.setRotation(this.Icicle8A, 0F, 0F, 0F);
		this.Icicle8B = new ModelRenderer(model, 106, 50);
		this.Icicle8B.addBox(0F, 0F, 0F, 1, 1, 3);
		this.Icicle8B.setRotationPoint(-7F, 12F, -4F);
		this.Icicle8B.setTextureSize(128, 64);
		this.Icicle8B.mirror = true;
		this.setRotation(this.Icicle8B, 0F, 0F, 0F);
		this.Icicle8C = new ModelRenderer(model, 108, 46);
		this.Icicle8C.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle8C.setRotationPoint(-7F, 13F, -4F);
		this.Icicle8C.setTextureSize(128, 64);
		this.Icicle8C.mirror = true;
		this.setRotation(this.Icicle8C, 0F, 0F, 0F);
		this.Icicle8D = new ModelRenderer(model, 112, 44);
		this.Icicle8D.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle8D.setRotationPoint(-7F, 15F, -3F);
		this.Icicle8D.setTextureSize(128, 64);
		this.Icicle8D.mirror = true;
		this.setRotation(this.Icicle8D, 0F, 0F, 0F);


		this.Icicle9A = new ModelRenderer(model, 122, 38);
		this.Icicle9A.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle9A.setRotationPoint(3F, 11F, -7F);
		this.Icicle9A.setTextureSize(128, 64);
		this.Icicle9A.mirror = true;
		this.setRotation(this.Icicle9A, 0F, 0F, 0F);
		this.Icicle9B = new ModelRenderer(model, 124, 36);
		this.Icicle9B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle9B.setRotationPoint(4F, 12F, -7F);
		this.Icicle9B.setTextureSize(128, 64);
		this.Icicle9B.mirror = true;
		this.setRotation(this.Icicle9B, 0F, 0F, 0F);
		this.Icicle10A = new ModelRenderer(model, 114, 61);
		this.Icicle10A.addBox(0F, 0F, 0F, 3, 2, 1);
		this.Icicle10A.setRotationPoint(-1F, 11F, -7F);
		this.Icicle10A.setTextureSize(128, 64);
		this.Icicle10A.mirror = true;
		this.setRotation(this.Icicle10A, 0F, 0F, 0F);
		this.Icicle10B = new ModelRenderer(model, 116, 59);
		this.Icicle10B.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle10B.setRotationPoint(-1F, 13F, -7F);
		this.Icicle10B.setTextureSize(128, 64);
		this.Icicle10B.mirror = true;
		this.setRotation(this.Icicle10B, 0F, 0F, 0F);
		this.Icicle10C = new ModelRenderer(model, 120, 56);
		this.Icicle10C.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle10C.setRotationPoint(0F, 14F, -7F);
		this.Icicle10C.setTextureSize(128, 64);
		this.Icicle10C.mirror = true;
		this.setRotation(this.Icicle10C, 0F, 0F, 0F);
		this.Icicle11A = new ModelRenderer(model, 114, 54);
		this.Icicle11A.addBox(0F, 0F, 0F, 4, 1, 1);
		this.Icicle11A.setRotationPoint(-5F, 11F, -7F);
		this.Icicle11A.setTextureSize(128, 64);
		this.Icicle11A.mirror = true;
		this.setRotation(this.Icicle11A, 0F, 0F, 0F);
		this.Icicle11B = new ModelRenderer(model, 116, 52);
		this.Icicle11B.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle11B.setRotationPoint(-4F, 12F, -7F);
		this.Icicle11B.setTextureSize(128, 64);
		this.Icicle11B.mirror = true;
		this.setRotation(this.Icicle11B, 0F, 0F, 0F);
		this.Icicle11C = new ModelRenderer(model, 118, 50);
		this.Icicle11C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle11C.setRotationPoint(-4F, 13F, -7F);
		this.Icicle11C.setTextureSize(128, 64);
		this.Icicle11C.mirror = true;
		this.setRotation(this.Icicle11C, 0F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render(float f, boolean bt, boolean bb, boolean be, boolean bw, boolean bn, boolean bs)
	{
		if(bb)
		{
			this.pillarBottom.render(f);
		}
		else
		{
			this.bottom.render(f);
			this.bottoms.render(f);
		}


		this.pillar.render(f);


		if(bw)
			this.pillarEast.render(f);
		if(be)
			this.pillarWest.render(f);
		if(bs)
			this.pillarNorth.render(f);
		if(bn)
			this.pillarSouth.render(f);

		if(bt)
		{
			this.pillarTop.render(f);
		}
		else
		{
			if(Magistics.proxy.winter)
			{
				//Icicles
				this.Icicle1A.render(f);
				this.Icicle1B.render(f);
				this.Icicle1C.render(f);
				this.Icicle2A.render(f);
				this.Icicle2B.render(f);
				this.Icicle2C.render(f);
				this.Icicle3A.render(f);
				this.Icicle3B.render(f);
				this.Icicle4A.render(f);
				this.Icicle4B.render(f);
				this.Icicle5A.render(f);
				this.Icicle5B.render(f);
				this.Icicle5C.render(f);
				this.Icicle6A.render(f);
				this.Icicle6B.render(f);
				this.Icicle6C.render(f);
				this.Icicle7A.render(f);
				this.Icicle7B.render(f);
				this.Icicle7C.render(f);
				this.Icicle8A.render(f);
				this.Icicle8B.render(f);
				this.Icicle8C.render(f);
				this.Icicle8D.render(f);

				this.Icicle9A.render(f);
				this.Icicle9B.render(f);
				this.Icicle10A.render(f);
				this.Icicle10B.render(f);
				this.Icicle10C.render(f);
				this.Icicle11A.render(f);
				this.Icicle11B.render(f);
				this.Icicle11C.render(f);
			}

			this.tops.render(f);
			this.top.render(f);
		}

	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		glPushMatrix();
		glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
		Block idTop, idBottom, idEast, idWest, idNorth, idSouth;
		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		if(meta == 0)
		{
			idTop = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord+1, tile.zCoord);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord-1, tile.zCoord);
			idEast = tile.getWorldObj().getBlock(tile.xCoord+1, tile.yCoord, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord-1, tile.yCoord, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord-1);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord+1);
		}
		else if(meta == 1)
		{
			glRotatef(90F, 1F, 0F, 0F);
			idTop = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord+1);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord-1);
			idEast = tile.getWorldObj().getBlock(tile.xCoord+1, tile.yCoord, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord-1, tile.yCoord, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord+1, tile.zCoord);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord-1, tile.zCoord);
		}
		else
		{
			glRotatef(90F, 0F, 0F, 1F);
			idTop = tile.getWorldObj().getBlock(tile.xCoord-1, tile.yCoord, tile.zCoord);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord+1, tile.yCoord, tile.zCoord);
			idEast = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord+1, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord-1, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord-1);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord+1);
		}
		boolean top = (idTop == ConfigObjects.blockBasePillar);
		//				|| (idTop == CraftingPillars.blockShowOffPillar.blockID)
		//				|| (idTop == CraftingPillars.blockCraftingPillar.blockID)
		//				|| (idTop == CraftingPillars.blockFurnacePillar.blockID)
		//				|| (idTop == CraftingPillars.blockBrewingPillar.blockID);
		boolean bottom = (idBottom == ConfigObjects.blockBasePillar);
		//				|| (idBottom == CraftingPillars.blockShowOffPillar.blockID)
		//				|| (idBottom == CraftingPillars.blockCraftingPillar.blockID)
		//				|| (idBottom == CraftingPillars.blockFurnacePillar.blockID)
		//				|| (idBottom == CraftingPillars.blockBrewingPillar.blockID);
		boolean east = (idEast == ConfigObjects.blockBasePillar);
		//				|| (idEast == CraftingPillars.blockShowOffPillar.blockID)
		//				|| (idEast == CraftingPillars.blockCraftingPillar.blockID);
		boolean west = (idWest == ConfigObjects.blockBasePillar);
		//				|| (idWest == CraftingPillars.blockShowOffPillar.blockID)
		//				|| (idWest == CraftingPillars.blockCraftingPillar.blockID);
		boolean north = (idNorth == ConfigObjects.blockBasePillar);
		//				|| (idNorth == CraftingPillars.blockShowOffPillar.blockID)
		//				|| (idNorth == CraftingPillars.blockCraftingPillar.blockID);
		boolean south = (idSouth == ConfigObjects.blockBasePillar);
		//				|| (idSouth == CraftingPillars.blockShowOffPillar.blockID)
		//				|| (idSouth == CraftingPillars.blockCraftingPillar.blockID);
		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR);
		glTranslatef(0.0F, 1.0F, 0.0F);
		glRotatef(180F, 1F, 0F, 0F);
		/*
		System.out.println("====== Meta "+meta+" ======");
		System.out.println("Top: "+top);
		System.out.println("Bottom: "+bottom);
		System.out.println("East: "+east);
		System.out.println("West: "+west);
		System.out.println("North: "+north);
		System.out.println("South: "+south);
		 */
		this.render(0.0625F, top, bottom, east, west, north, south);
		glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR);
		this.render(0.0625F, false, false, false, false, false, false);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockPillarExtend.renderID;
	}
}
