package T145.magistics.client.renderers.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarCrafting;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TilePillarCrafting;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCraftingPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_WORKPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};

	private ModelRenderer bottom;
	private ModelRenderer pillarbottom;
	private ModelRenderer pillar;
	private ModelRenderer pillartop;
	private ModelRenderer top;

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
	private ModelRenderer WreathA;
	private ModelRenderer WreathB;
	private ModelRenderer WreathC;
	private ModelRenderer WreathD;
	private ModelRenderer WreathE;
	private ModelRenderer WreathF;
	private ModelRenderer WreathG;
	private ModelRenderer WreathH;
	private ModelRenderer WreathI;
	private ModelRenderer WreathJ;
	private ModelRenderer Bow;

	private ModelRenderer BunnyTail1;
	private ModelRenderer BunnyTail2;
	private ModelRenderer BunnyTail3;
	private ModelRenderer BunnyEar1;
	private ModelRenderer BunnyEar2;

	private ModelRenderer pillarBottom;

	private static final RenderingHelper.ItemRender itemRenderer = new RenderingHelper.ItemRender(false, true);
	private static final RenderingHelper.ItemRender resultRenderer = new RenderingHelper.ItemRender(true, true);

	public RenderCraftingPillar()
	{
		if (Magistics.proxy.winter)
			this.TEXTURE_WORKPILLAR = new ResourceLocation("craftingpillars:textures/models/craftingPillarFrozen.png");
		else
			this.TEXTURE_WORKPILLAR = new ResourceLocation("craftingpillars:textures/models/craftingPillar.png");

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
		this.WreathA = new ModelRenderer(model, 86, 62);
		this.WreathA.addBox(0F, 0F, 0F, 4, 1, 1);
		this.WreathA.setRotationPoint(-2F, 12F, -7F);
		this.WreathA.setTextureSize(128, 64);
		this.WreathA.mirror = true;
		this.setRotation(this.WreathA, 0F, 0F, 0F);
		this.WreathB = new ModelRenderer(model, 82, 60);
		this.WreathB.addBox(0F, 0F, 0F, 6, 1, 1);
		this.WreathB.setRotationPoint(-3F, 13F, -7F);
		this.WreathB.setTextureSize(128, 64);
		this.WreathB.mirror = true;
		this.setRotation(this.WreathB, 0F, 0F, 0F);
		this.WreathC = new ModelRenderer(model, 88, 51);
		this.WreathC.addBox(0F, 0F, 0F, 2, 4, 1);
		this.WreathC.setRotationPoint(-4F, 14F, -7F);
		this.WreathC.setTextureSize(128, 64);
		this.WreathC.mirror = true;
		this.setRotation(this.WreathC, 0F, 0F, 0F);
		this.WreathD = new ModelRenderer(model, 90, 54);
		this.WreathD.addBox(0F, 0F, 0F, 2, 4, 1);
		this.WreathD.setRotationPoint(2F, 14F, -7F);
		this.WreathD.setTextureSize(128, 64);
		this.WreathD.mirror = true;
		this.setRotation(this.WreathD, 0F, 0F, 0F);
		this.WreathE = new ModelRenderer(model, 82, 46);
		this.WreathE.addBox(0F, 0F, 0F, 6, 1, 1);
		this.WreathE.setRotationPoint(-3F, 18F, -7F);
		this.WreathE.setTextureSize(128, 64);
		this.WreathE.mirror = true;
		this.setRotation(this.WreathE, 0F, 0F, 0F);
		this.WreathF = new ModelRenderer(model, 86, 49);
		this.WreathF.addBox(0F, 0F, 0F, 4, 1, 1);
		this.WreathF.setRotationPoint(-2F, 19F, -7F);
		this.WreathF.setTextureSize(128, 64);
		this.WreathF.mirror = true;
		this.setRotation(this.WreathF, 0F, 0F, 0F);
		this.WreathG = new ModelRenderer(model, 88, 38);
		this.WreathG.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathG.setRotationPoint(-2F, 14F, -7F);
		this.WreathG.setTextureSize(128, 64);
		this.WreathG.mirror = true;
		this.setRotation(this.WreathG, 0F, 0F, 0F);
		this.WreathH = new ModelRenderer(model, 88, 38);
		this.WreathH.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathH.setRotationPoint(1F, 14F, -7F);
		this.WreathH.setTextureSize(128, 64);
		this.WreathH.mirror = true;
		this.setRotation(this.WreathH, 0F, 0F, 0F);
		this.WreathI = new ModelRenderer(model, 88, 38);
		this.WreathI.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathI.setRotationPoint(-2F, 17F, -7F);
		this.WreathI.setTextureSize(128, 64);
		this.WreathI.mirror = true;
		this.setRotation(this.WreathI, 0F, 0F, 0F);
		this.WreathJ = new ModelRenderer(model, 88, 38);
		this.WreathJ.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathJ.setRotationPoint(1F, 17F, -7F);
		this.WreathJ.setTextureSize(128, 64);
		this.WreathJ.mirror = true;
		this.setRotation(this.WreathJ, 0F, 0F, 0F);
		this.Bow = new ModelRenderer(model, 120, 28);
		this.Bow.addBox(0F, -1F, -2F, 2, 2, 2);
		this.Bow.setRotationPoint(-1F, 14F, -6F);
		this.Bow.setTextureSize(128, 64);
		this.Bow.mirror = true;
		this.setRotation(this.Bow, 0F, 0F, 0F);
	}

	public void render(float f)
	{
		this.bottom.render(f);
		this.pillarbottom.render(f);
		this.pillar.render(f);

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
			this.WreathA.render(f);
			this.WreathB.render(f);
			this.WreathC.render(f);
			this.WreathD.render(f);
			this.WreathE.render(f);
			this.WreathF.render(f);
			this.WreathG.render(f);
			this.WreathH.render(f);
			this.WreathI.render(f);
			this.WreathJ.render(f);
			this.Bow.render(f);
		}


		this.pillartop.render(f);
		this.top.render(f);
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
		glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR);
		this.render(0.0625F);
		if (Magistics.proxy.easter)
		{
			f = 0.0625F;
			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
		}
		glPopMatrix();

		TilePillarCrafting workTile = (TilePillarCrafting) tile;
		EntityItem citem = new EntityItem(tile.getWorldObj());
		citem.hoverStart = ConfigObjects.floatingItems ? workTile.rot : 0F;

		glPushMatrix();
		glTranslated(x, y, z);
		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 3; k++)
			{
				if (workTile.getStackInSlot(i * 3 + k) != null)
				{
					citem.setEntityItemStack(workTile.getStackInSlot(i * 3 + k));
					glPushMatrix();
					glTranslated(0.1875D + i * 0.3125D, 1D + 0.1875D / 3D, 0.1875D + k * 0.3125D);
					glScalef(0.5F, 0.5F, 0.5F);
					RenderCraftingPillar.itemRenderer.render(citem, 0F, 0F, 0F, workTile.showNum);
					glPopMatrix();
				}
			}
		}

		if (workTile.getStackInSlot(workTile.getSizeInventory()) != null)
		{
			glPushMatrix();
			citem.hoverStart = -workTile.rot;
			citem.setEntityItemStack(workTile.getStackInSlot(workTile.getSizeInventory()));
			RenderCraftingPillar.resultRenderer.render(citem, 0.5F, 1.5F, 0.5F, workTile.showNum);
			glPopMatrix();
		}

		glPopMatrix();

		if (workTile.getStackInSlot(10) != null)
		{
			glPushMatrix();
			citem.hoverStart = 0;

			glColor4f(1, 1, 1, 1);
			glTranslated(x, y, z);
			glTranslated(0.5D, 0.5D, 0.5D);
			glRotatef(-90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

			if (Magistics.proxy.winter)
				glTranslatef(0, -0.05F, 0.47F);
			else
				glTranslatef(0, 0, 0.4F);

			glRotatef(-45, 0, 0, 1);
			glScalef(1.1F, 1.1F, 1.1F);

			glTranslatef(0, -0.28F, 0);

			citem.setEntityItemStack(workTile.getStackInSlot(10));
			RenderCraftingPillar.resultRenderer.render(citem, 0F, 0F, 0F, false);

			glPopMatrix();

			//			if (workTile.getStackInSlot(workTile.getSizeInventory()) != null)
			//			{
			//				glPushMatrix();
			//				glTranslated(x, y + 1F, z);
			//
			//				float r = 0.6F;
			//
			//				AspectList aspectsRecipe = workTile.aspectsForRecipe;
			//
			////				if (aspectsRecipe != null)
			////				{
			//					glDisable(GL_LIGHTING);
			//					for (int i = 0; i < Aspect.getPrimalAspects().size(); i++)
			//					{
			//						Aspect aspect = Aspect.getPrimalAspects().get(i);
			//						for (int j = 0; j < aspectsRecipe.size(); j++)
			//						{
			//							if (aspectsRecipe.getAspects()[j] != null)
			//							{
			//								FMLLog.info("is " + aspectsRecipe.getAspects()[j].getName() + " equals " + aspect.getName());
			////								if (aspectsRecipe.getAspects()[j].getName().equals(aspect.getName()))
			////								{
			//									float theta = 2.0f * 3.1415926f * (float) i / (float) Aspect.getPrimalAspects().size();// get the current angle
			//
			//									float xC = (float) (r * Math.cos(theta));// calculate the x component
			//									float zC = (float) (r * Math.sin(theta));// calculate the z component
			//
			//									RenderingHelper.renderFacingQuad(0.5F + xC, 0.5F, 0.5F + zC, 1F, aspect.getImage(), new Color(aspect.getColor()));
			//									break;
			////								}
			//							}
			//						}
			//					}
			//					glEnable(GL_LIGHTING);
			////				}
			//				glPopMatrix();
			//			}
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
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR);
		this.render(0.0625F);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockPillarCrafting.renderID;
	}
}
