package T145.magistics.client.renderers.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarPot;
import T145.magistics.common.tiles.craftingpillars.TilePillarPot;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderPotPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_SHOWOFFPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};

	private ModelRenderer bottom;
	private ModelRenderer pillarbottom;
	private ModelRenderer pillar;
	private ModelRenderer pillartop;
	private ModelRenderer top;

	private ModelRenderer pot;

	private ModelRenderer pillarBottom;

	private ModelRenderer BunnyTail1;
	private ModelRenderer BunnyTail2;
	private ModelRenderer BunnyTail3;
	private ModelRenderer BunnyEar1;
	private ModelRenderer BunnyEar2;

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

	private Random random;
	private RenderingHelper.ItemRender itemRenderer;
	private RenderingHelper.ItemRender resultRenderer;

	public RenderPotPillar()
	{
		if (Magistics.proxy.winter)
			this.TEXTURE_SHOWOFFPILLAR = new ResourceLocation("craftingpillars:textures/models/showoffPillarFrozen.png");
		else
			this.TEXTURE_SHOWOFFPILLAR = new ResourceLocation("craftingpillars:textures/models/showoffPillar.png");

		this.random = new Random();
		this.itemRenderer = new RenderingHelper.ItemRender(false, true);
		this.resultRenderer = new RenderingHelper.ItemRender(true, true);

		model.textureWidth = 128;
		model.textureHeight = 64;

		this.pillarBottom = new ModelRenderer(model, 0, 33);
		this.pillarBottom.addBox(0F, 0F, 0F, 12, 3, 12);
		this.pillarBottom.setRotationPoint(-6F, 21F, 6F);
		this.pillarBottom.setTextureSize(128, 64);
		this.pillarBottom.mirror = true;
		this.setRotation(this.pillarBottom, 0F, 1.570796F, 0F);

		this.bottom = new ModelRenderer(model, 0, 0);
		this.bottom.addBox(-8F, -1F, -8F, 16, 2, 16);
		this.bottom.setRotationPoint(0F, 23F, 0F);
		this.bottom.setTextureSize(128, 64);
		this.bottom.mirror = true;
		this.setRotation(this.bottom, 0F, 0F, 0F);
		this.pillarbottom = new ModelRenderer(model, 0, 18);
		this.pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		this.pillarbottom.setRotationPoint(0F, 21F, 0F);
		this.pillarbottom.setTextureSize(128, 64);
		this.pillarbottom.mirror = true;
		this.setRotation(this.pillarbottom, 0F, 0F, 0F);
		this.pillar = new ModelRenderer(model, 0, 33);
		this.pillar.addBox(-6F, 0F, -6F, 12, 10, 12);
		this.pillar.setRotationPoint(0F, 11F, 0F);
		this.pillar.setTextureSize(128, 64);
		this.pillar.mirror = true;
		this.setRotation(this.pillar, 0F, 0F, 0F);
		this.pillartop = new ModelRenderer(model, 0, 18);
		this.pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		this.pillartop.setRotationPoint(0F, 10F, 0F);
		this.pillartop.setTextureSize(128, 64);
		this.pillartop.mirror = true;
		this.setRotation(this.pillartop, 0F, 0F, 0F);
		this.top = new ModelRenderer(model, 64, 0);
		this.top.addBox(-8F, -1F, -8F, 16, 2, 16);
		this.top.setRotationPoint(0F, 9F, 0F);
		this.top.setTextureSize(128, 64);
		this.top.mirror = true;
		this.setRotation(this.top, 0F, 0F, 0F);

		this.pot = new ModelRenderer(model, 64, 18);
		this.pot.addBox(-3F, 0F, -3F, 6, 5, 6);
		this.pot.setRotationPoint(0F, 3F, 0F);
		this.pot.setTextureSize(128, 64);
		this.pot.mirror = true;
		this.setRotation(this.pot, 0F, 0F, 0F);

		if (Magistics.proxy.easter)
		{
			BunnyTail1 = new ModelRenderer(model, 0, 35);
			BunnyTail1.addBox(0F, 0F, 0F, 2, 4, 2);
			BunnyTail1.setRotationPoint(-1F, 18F, 7F);
			BunnyTail1.setTextureSize(128, 64);
			BunnyTail1.mirror = true;
			setRotation(BunnyTail1, 0F, 0F, 0F);
			BunnyTail2 = new ModelRenderer(model, 0, 33);
			BunnyTail2.addBox(0F, 0F, 0F, 4, 2, 2);
			BunnyTail2.setRotationPoint(-2F, 19F, 7F);
			BunnyTail2.setTextureSize(128, 64);
			BunnyTail2.mirror = true;
			setRotation(BunnyTail2, 0F, 0F, 0F);
			BunnyTail3 = new ModelRenderer(model, 0, 36);
			BunnyTail3.addBox(0F, 0F, 0F, 2, 2, 4);
			BunnyTail3.setRotationPoint(-1F, 19F, 6F);
			BunnyTail3.setTextureSize(128, 64);
			BunnyTail3.mirror = true;
			setRotation(BunnyTail3, 0F, 0F, 0F);
			BunnyEar1 = new ModelRenderer(model, 1, 18);
			BunnyEar1.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar1.setRotationPoint(3.5F, 9F, 9F);
			BunnyEar1.setTextureSize(128, 64);
			BunnyEar1.mirror = true;
			setRotation(BunnyEar1, 0F, 0F, 0F);
			BunnyEar2 = new ModelRenderer(model, 1, 18);
			BunnyEar2.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar2.setRotationPoint(-3.5F, 9F, 9F);
			BunnyEar2.setTextureSize(128, 64);
			BunnyEar2.mirror = true;
			setRotation(BunnyEar2, 0F, 0F, 0F);
		}

		// Winter
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

	public void render(float f)
	{

		if (Magistics.proxy.winter)
		{
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

		this.bottom.render(f);
		this.pillarbottom.render(f);
		this.pillar.render(f);
		this.pillartop.render(f);
		this.top.render(f);
		this.pot.render(f);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(180F, 1F, 0F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_SHOWOFFPILLAR);
		this.render(0.0625F);

		if (Magistics.proxy.easter)
		{
			glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

			f = 0.0625F;
			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
		}
		glPopMatrix();

		TilePillarPot pillarTile = (TilePillarPot) tile;

		if (pillarTile.getStackInSlot(0) != null)
		{
			if (pillarTile.showNum)
			{
				glPushMatrix();
				glTranslated(x + 0.5D, y + 1, z + 0.5D);

				glDisable(GL_LIGHTING);
				RenderingHelper
				.renderFloatingTextWithBackground(0, 0.9F, 0, 0.2F, pillarTile.getStackInSlot(0).getDisplayName(), Color.WHITE.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);

				glPopMatrix();
			}
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_SHOWOFFPILLAR);
		this.render(0.0625F);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		TilePillarPot tile = (TilePillarPot) blockAccess.getTileEntity(x, y, z);
		renderer.renderAllFaces = true;

		if (tile.getStackInSlot(0) != null)
		{
			Block plantBlock = Block.getBlockFromItem(tile.getStackInSlot(0).getItem());
			int metadata = tile.getStackInSlot(0).getItemDamage();

			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(plantBlock.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));

			int hexColor = plantBlock.colorMultiplier(renderer.blockAccess, x, y, z);
			float red = (hexColor >> 16 & 255) / 255.0F;
			float green = (hexColor >> 8 & 255) / 255.0F;
			float blue = (hexColor & 255) / 255.0F;
			tessellator.setColorOpaque_F(red, green, blue);

			float renderScale = 0.75F;

			if (plantBlock == Blocks.red_flower || plantBlock == Blocks.yellow_flower || plantBlock == Blocks.red_mushroom || plantBlock == Blocks.brown_mushroom)
				renderScale = 1.0F;


			renderer.drawCrossedSquares(plantBlock.getIcon(0, metadata), x, y + 1.2, z, renderScale);
		}

		renderer.renderAllFaces = false;

		return true;
	}

	private int setBrightness(IBlockAccess world, int x, int y, int z, Block block)
	{
		int brightness = block.getMixedBrightnessForBlock(world, x, y, z);

		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

		float f = 1.0F;

		int l = block.colorMultiplier(world, x, y, z);
		float f1 = (l >> 16 & 255) / 255.0F;
		float f2 = (l >> 8 & 255) / 255.0F;
		float f3 = (l & 255) / 255.0F;
		float f4;

		if (EntityRenderer.anaglyphEnable)
		{
			float f5 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			f4 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f5;
			f2 = f4;
			f3 = f6;
		}

		tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
		return brightness;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockPillarPot.renderID;
	}
}
