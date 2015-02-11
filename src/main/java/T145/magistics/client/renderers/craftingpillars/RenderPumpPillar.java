package T145.magistics.client.renderers.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.client.lib.Blobs;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarPump;
import T145.magistics.common.tiles.craftingpillars.TileEntityPumpPillar;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderPumpPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private static final ResourceLocation TEXTURE_DRILL = new ResourceLocation("craftingpillars:textures/models/pump_drill.png");

	private ResourceLocation TEXTURE_PUMPPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};
	private ModelRenderer pillarbottom;
	private ModelRenderer pillartop;
	private ModelRenderer Corner1;
	private ModelRenderer Corner2;
	private ModelRenderer Corner3;
	private ModelRenderer Corner4;
	private ModelRenderer BottomTank;
	private ModelRenderer TopTank;
	private ModelRenderer GlassPane1;
	private ModelRenderer GlassPane2;
	private ModelRenderer GlassPane4;
	private ModelRenderer GlassPane3;
	private ModelRenderer Edge1;
	private ModelRenderer Edge2;
	private ModelRenderer Edge3;
	private ModelRenderer Edge4;

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

	public IModelCustom drill;

	private Random random;
	private RenderingHelper.ItemRender itemRenderer;
	private RenderingHelper.ItemRender resultRenderer;

	public RenderPumpPillar()
	{
		if (Magistics.proxy.winter)
			this.TEXTURE_PUMPPILLAR = new ResourceLocation("craftingpillars:textures/models/freezerPillarFrozen.png");
		else
			this.TEXTURE_PUMPPILLAR = new ResourceLocation("craftingpillars:textures/models/freezerPillar.png");

		this.drill = AdvancedModelLoader.loadModel(new ResourceLocation("craftingpillars:textures/models/pump_bottom.obj"));

		this.random = new Random();
		this.itemRenderer = new RenderingHelper.ItemRender(false, true);
		this.resultRenderer = new RenderingHelper.ItemRender(true, true);

		model.textureWidth = 128;
		model.textureHeight = 64;

		pillarbottom = new ModelRenderer(model, 0, 18);
		pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillarbottom.setRotationPoint(0F, 17F, 0F);
		pillarbottom.setTextureSize(128, 64);
		pillarbottom.mirror = true;
		setRotation(pillarbottom, 0F, 0F, 0F);
		pillartop = new ModelRenderer(model, 0, 18);
		pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillartop.setRotationPoint(0F, 10F, 0F);
		pillartop.setTextureSize(128, 64);
		pillartop.mirror = true;
		setRotation(pillartop, 0F, 0F, 0F);
		Corner1 = new ModelRenderer(model, 64, -2);
		Corner1.addBox(5F, 0F, 5F, 2, 6, 2);
		Corner1.setRotationPoint(0F, 11F, 0F);
		Corner1.setTextureSize(128, 64);
		Corner1.mirror = true;
		setRotation(Corner1, 0F, 0F, 0F);
		Corner2 = new ModelRenderer(model, 48, 8);
		Corner2.addBox(-7F, 0F, -7F, 2, 6, 2);
		Corner2.setRotationPoint(0F, 11F, 0F);
		Corner2.setTextureSize(128, 64);
		Corner2.mirror = true;
		setRotation(Corner2, 0F, 0F, 0F);
		Corner3 = new ModelRenderer(model, 56, -2);
		Corner3.addBox(5F, 0F, -7F, 2, 6, 2);
		Corner3.setRotationPoint(0F, 11F, 0F);
		Corner3.setTextureSize(128, 64);
		Corner3.mirror = true;
		setRotation(Corner3, 0F, 0F, 0F);
		Corner4 = new ModelRenderer(model, 48, -2);
		Corner4.addBox(-7F, 0F, 5F, 2, 6, 2);
		Corner4.setRotationPoint(0F, 11F, 0F);
		Corner4.setTextureSize(128, 64);
		Corner4.mirror = true;
		setRotation(Corner4, 0F, 0F, 0F);

		BottomTank = new ModelRenderer(model, 64, 36);
		BottomTank.mirror = true;
		BottomTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		BottomTank.setRotationPoint(0F, 18F, 0F);
		BottomTank.setTextureSize(128, 64);
		BottomTank.mirror = true;
		setRotation(BottomTank, 0F, 0F, 0F);
		BottomTank.mirror = false;
		TopTank = new ModelRenderer(model, 64, 18);
		TopTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		TopTank.setRotationPoint(0F, 8F, 0F);
		TopTank.setTextureSize(128, 64);
		TopTank.mirror = true;
		setRotation(TopTank, 0F, 0F, 0F);
		GlassPane1 = new ModelRenderer(model, 108, 54);
		GlassPane1.addBox(-5F, -4F, -6F, 10, 6, 0);
		GlassPane1.setRotationPoint(0F, 15F, 0F);
		GlassPane1.setTextureSize(128, 64);
		GlassPane1.mirror = true;
		setRotation(GlassPane1, 0F, 0F, 0F);
		GlassPane2 = new ModelRenderer(model, 108, 54);
		GlassPane2.addBox(-5F, -4F, 6F, 10, 6, 0);
		GlassPane2.setRotationPoint(0F, 15F, 0F);
		GlassPane2.setTextureSize(128, 64);
		GlassPane2.mirror = true;
		setRotation(GlassPane2, 0F, 0F, 0F);
		GlassPane4 = new ModelRenderer(model, 108, 54);
		GlassPane4.addBox(-5F, -4F, 6F, 10, 6, 0);
		GlassPane4.setRotationPoint(0F, 15F, 0F);
		GlassPane4.setTextureSize(128, 64);
		GlassPane4.mirror = true;
		setRotation(GlassPane4, 0F, 1.570796F, 0F);
		GlassPane3 = new ModelRenderer(model, 108, 54);
		GlassPane3.addBox(-5F, -4F, -6F, 10, 6, 0);
		GlassPane3.setRotationPoint(0F, 15F, 0F);
		GlassPane3.setTextureSize(128, 64);
		GlassPane3.mirror = true;
		setRotation(GlassPane3, 0F, 1.570796F, 0F);
		Edge1 = new ModelRenderer(model, 0, 46);
		Edge1.addBox(0F, 0F, 0F, 1, 2, 16);
		Edge1.setRotationPoint(-8F, 6F, -8F);
		Edge1.setTextureSize(128, 64);
		Edge1.mirror = true;
		setRotation(Edge1, 0F, 0F, 0F);
		Edge2 = new ModelRenderer(model, 0, 46);
		Edge2.addBox(0F, 0F, 0F, 1, 2, 16);
		Edge2.setRotationPoint(7F, 6F, -8F);
		Edge2.setTextureSize(128, 64);
		Edge2.mirror = true;
		setRotation(Edge2, 0F, 0F, 0F);
		Edge3 = new ModelRenderer(model, 35, 61);
		Edge3.addBox(0F, 0F, 0F, 14, 2, 1);
		Edge3.setRotationPoint(-7F, 6F, -8F);
		Edge3.setTextureSize(128, 64);
		Edge3.mirror = true;
		setRotation(Edge3, 0F, 0F, 0F);
		Edge4 = new ModelRenderer(model, 35, 61);
		Edge4.addBox(0F, 0F, 0F, 14, 2, 1);
		Edge4.setRotationPoint(-7F, 6F, 7F);
		Edge4.setTextureSize(128, 64);
		Edge4.mirror = true;
		setRotation(Edge4, 0F, 0F, 0F);


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
		if (Magistics.proxy.winter)
		{
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

		if(Magistics.proxy.easter)
		{

			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
		}

		pillarbottom.render(f);
		pillartop.render(f);
		Corner1.render(f);
		Corner2.render(f);
		Corner3.render(f);
		Corner4.render(f);
		BottomTank.render(f);
		TopTank.render(f);
		GlassPane1.render(f);
		GlassPane2.render(f);
		GlassPane4.render(f);
		GlassPane3.render(f);
		Edge1.render(f);
		Edge2.render(f);
		Edge3.render(f);
		Edge4.render(f);
	}


	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_PUMPPILLAR);
		this.render(0.0625F);

		glRotatef(180F, 1F, 0F, 0F);

		glTranslatef(0, -1.5F, 0);
		glScalef(0.025F, 0.025F, 0.025F);

		glDisable(GL_LIGHTING);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE_DRILL);
		this.drill.renderAll();
		glEnable(GL_LIGHTING);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		TileEntityPumpPillar workTile = (TileEntityPumpPillar) tile;

		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(-90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);
		glRotatef(180F, 1F, 0F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_PUMPPILLAR);
		this.render(0.0625F);
		EntityItem citem = new EntityItem(tile.getWorldObj());

		// Fuel FIXME: NUMBER
		if (workTile.getStackInSlot(0) != null)
		{			
			glRotatef(180F, 1F, 0F, 0F);
			glRotatef(180F, 0, 1F, 0F);

			glTranslated(0D, -0.65D, 0D);

			citem.hoverStart = 0F;
			citem.setEntityItemStack(workTile.getStackInSlot(0));
			this.itemRenderer.render(citem, 0F, 0.3F, 0F, workTile.showNum);
		}
		glPopMatrix();


		glPushMatrix();
		glTranslated(x + 0.5F, y + 1.02F, z + 0.5F);
		glRotatef(workTile.rot, 0, 1, 0);
		glTranslatef(0, -1F, 0);
		glScalef(0.025F, 0.025F, 0.025F);
		glDisable(GL_LIGHTING);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE_DRILL);
		this.drill.renderAll();
		glEnable(GL_LIGHTING);
		glPopMatrix();


		if (workTile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid == null || workTile.isEmpty)
			return;
		EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
		if (player.getDistanceSq(tile.xCoord, tile.yCoord, tile.zCoord) < 128)
		{
			glPushMatrix();
			glTranslated(x, y, z);
			glPushAttrib(GL_ENABLE_BIT);
			glDisable(GL_LIGHTING);

			//			glEnable(GL_BLEND);
			//			glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

			glTranslatef(0.005F, 0.005F, 0.005F);
			glScalef(0.99F, 0.99F, 0.99F);

			float[][][] field = Blobs.fieldStrength(workTile.blobs);
			for (int i = 0; i < 16; i++)
				for (int j = 0; j < 16; j++)
					for (int k = 0; k < 16; k++)
						if ((int) field[i][j][k] > 0
								&& (i != 0 && (int) field[i - 1][j][k] != 0 && i != 15 && (int) field[i + 1][j][k] != 0 && j != 0 && (int) field[i][j - 1][k] != 0 && j != 15
								&& (int) field[i][j + 1][k] != 0 && k != 0 && (int) field[i][j][k - 1] != 0 && k != 15 && (int) field[i][j][k + 1] != 0))
						{
							field[i][j][k] = 0F;
						}

			FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture);

			IIcon icon = workTile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid().getIcon();

			float xMin = icon.getMinU();
			float xMax = icon.getMaxU();
			float yMin = icon.getMinV();
			float yMax = icon.getMaxV();

			float iconSize = (xMax-xMin)/16;

			glBegin(GL_QUADS);

			for (int i = 0; i < 16; i++)
				for (int j = 0; j < 16; j++)
					for (int k = 0; k < 16; k++)
						if ((int) field[i][j][k] > 0)
						{
							if (j == 15 || (int) field[i][j + 1][k] == 0)
							{
								glTexCoord2f(xMin + i * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (k+1) * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + (k+1) * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + k * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k) / 16F);
							}

							if (j == 0 || (int) field[i][j - 1][k] == 0)
							{
								glTexCoord2f(xMin + i * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (k+1) * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + (k+1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + k * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k + 1) / 16F);
							}

							if (k == 15 || (int) field[i][j][k + 1] == 0)
							{
								glTexCoord2f(xMin + i * iconSize, yMin + j * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (j+1) * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + (j+1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k + 1) / 16F);
							}

							if (k == 0 || (int) field[i][j][k - 1] == 0)
							{
								glTexCoord2f(xMin + i * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (j+1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + (j+1) * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i+1) * iconSize, yMin + j * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k) / 16F);
							}

							if (i == 15 || (int) field[i + 1][j][k] == 0)
							{
								glTexCoord2f(xMin + k * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + k * iconSize, yMin + (j+1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (k+1) * iconSize, yMin + (j+1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (k+1) * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k) / 16F);
							}

							if (i == 0 || (int) field[i - 1][j][k] == 0)
							{
								glTexCoord2f(xMin + j * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + j * iconSize, yMin + (k+1) * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (j+1) * iconSize, yMin + (k+1) * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (j+1) * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k) / 16F);
							}
						}

			glEnd();
			//			glDisable(GL_BLEND);
			glPopAttrib();
			glPopMatrix();
		} else
		{

		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockPillarPump.renderID;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
