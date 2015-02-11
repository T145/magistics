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

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.client.lib.Blobs;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTank;
import T145.magistics.common.tiles.craftingpillars.TileEntityTankPillar;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderTankPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_TANKPILLAR;

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

	private ModelRenderer Valve1;
	private ModelRenderer Valve2;
	private ModelRenderer Valve3;
	private ModelRenderer Valve4;

	private ModelRenderer Valve;

	private ModelRenderer BunnyTail1;
	private ModelRenderer BunnyTail2;
	private ModelRenderer BunnyTail3;
	private ModelRenderer BunnyEar1;
	private ModelRenderer BunnyEar2;

	private ModelRenderer Icicle1A;
	private ModelRenderer Icicle1B;
	private ModelRenderer Icicle1C;
	private ModelRenderer Icicle2A;
	private ModelRenderer Icicle2C;
	private ModelRenderer Icicle2B;
	private ModelRenderer Icicle3A;
	private ModelRenderer Icicle3B;
	private ModelRenderer Icicle3C;
	private ModelRenderer Icicle3D;
	private ModelRenderer Icicle4A;
	private ModelRenderer Icicle4B;

	private RenderingHelper.ItemRender resultRenderer;

	public RenderTankPillar()
	{
		if(Magistics.proxy.winter)
			this.TEXTURE_TANKPILLAR = new ResourceLocation("craftingpillars:textures/models/freezerPillarFrozen.png");
		else
			this.TEXTURE_TANKPILLAR = new ResourceLocation("craftingpillars:textures/models/freezerPillar.png");

		this.resultRenderer = new RenderingHelper.ItemRender(false, false);

		model.textureWidth = 128;
		model.textureHeight = 64;

		this.pillarbottom = new ModelRenderer(model, 0, 18);
		this.pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		this.pillarbottom.setRotationPoint(0F, 21F, 0F);
		this.pillarbottom.setTextureSize(128, 64);
		this.pillarbottom.mirror = true;
		this.setRotation(this.pillarbottom, 0F, 0F, 0F);
		this.pillartop = new ModelRenderer(model, 0, 18);
		this.pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		this.pillartop.setRotationPoint(0F, 10F, 0F);
		this.pillartop.setTextureSize(128, 64);
		this.pillartop.mirror = true;
		this.setRotation(this.pillartop, 0F, 0F, 0F);

		this.Corner1 = new ModelRenderer(model, 64, -2);
		this.Corner1.addBox(5F, 0F, 5F, 2, 10, 2);
		this.Corner1.setRotationPoint(0F, 11F, 0F);
		this.Corner1.setTextureSize(128, 64);
		this.Corner1.mirror = false;
		this.setRotation(this.Corner1, 0F, 0F, 0F);
		this.Corner2 = new ModelRenderer(model, 48, 8);
		this.Corner2.addBox(-7F, 0F, -7F, 2, 10, 2);
		this.Corner2.setRotationPoint(0F, 11F, 0F);
		this.Corner2.setTextureSize(128, 64);
		this.setRotation(this.Corner2, 0F, 0F, 0F);
		this.Corner2.mirror = false;
		this.Corner3 = new ModelRenderer(model, 56, -2);
		this.Corner3.addBox(5F, 0F, -7F, 2, 10, 2);
		this.Corner3.setRotationPoint(0F, 11F, 0F);
		this.Corner3.setTextureSize(128, 64);
		this.Corner3.mirror = false;
		this.setRotation(this.Corner3, 0F, 0F, 0F);
		this.Corner4 = new ModelRenderer(model, 48, -2);
		this.Corner4.addBox(-7F, 0F, 5F, 2, 10, 2);
		this.Corner4.setRotationPoint(0F, 11F, 0F);
		this.Corner4.setTextureSize(128, 64);
		this.Corner4.mirror = false;
		this.setRotation(this.Corner4, 0F, 0F, 0F);
		this.BottomTank = new ModelRenderer(model, 64, 36);
		this.BottomTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		this.BottomTank.setRotationPoint(0F, 22F, 0F);
		this.BottomTank.setTextureSize(128, 64);
		this.setRotation(this.BottomTank, 0F, 0F, 0F);
		this.BottomTank.mirror = false;
		this.TopTank = new ModelRenderer(model, 64, 18);
		this.TopTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		this.TopTank.setRotationPoint(0F, 8F, 0F);
		this.TopTank.setTextureSize(128, 64);
		this.TopTank.mirror = true;
		this.setRotation(this.TopTank, 0F, 0F, 0F);
		this.GlassPane1 = new ModelRenderer(model, 108, 54);
		this.GlassPane1.addBox(-5F, -4F, -6F, 10, 10, 0);
		this.GlassPane1.setRotationPoint(0F, 15F, 0F);
		this.GlassPane1.setTextureSize(128, 64);
		this.GlassPane1.mirror = true;
		this.setRotation(this.GlassPane1, 0F, 0F, 0F);
		this.GlassPane2 = new ModelRenderer(model, 108, 54);
		this.GlassPane2.addBox(-5F, -4F, 6F, 10, 10, 0);
		this.GlassPane2.setRotationPoint(0F, 15F, 0F);
		this.GlassPane2.setTextureSize(128, 64);
		this.GlassPane2.mirror = true;
		this.setRotation(this.GlassPane2, 0F, 0F, 0F);
		this.GlassPane4 = new ModelRenderer(model, 108, 54);
		this.GlassPane4.addBox(-5F, -4F, 6F, 10, 10, 0);
		this.GlassPane4.setRotationPoint(0F, 15F, 0F);
		this.GlassPane4.setTextureSize(128, 64);
		this.GlassPane4.mirror = true;
		this.setRotation(this.GlassPane4, 0F, 1.570796F, 0F);
		this.GlassPane3 = new ModelRenderer(model, 108, 54);
		this.GlassPane3.addBox(-5F, -4F, -6F, 10, 10, 0);
		this.GlassPane3.setRotationPoint(0F, 15F, 0F);
		this.GlassPane3.setTextureSize(128, 64);
		this.GlassPane3.mirror = true;
		this.setRotation(this.GlassPane3, 0F, 1.570796F, 0F);

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

		if(Magistics.proxy.winter)
		{
			this.Icicle1A = new ModelRenderer(model, 122, 38);
			this.Icicle1A.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle1A.setRotationPoint(6F, 10F, 7F);
			this.Icicle1A.setTextureSize(128, 64);
			this.Icicle1A.mirror = true;
			this.setRotation(this.Icicle1A, 0F, 0F, 0F);
			this.Icicle1B = new ModelRenderer(model, 122, 40);
			this.Icicle1B.addBox(0F, 0F, 0F, 1, 2, 1);
			this.Icicle1B.setRotationPoint(7F, 11F, 7F);
			this.Icicle1B.setTextureSize(128, 64);
			this.Icicle1B.mirror = true;
			this.setRotation(this.Icicle1B, 0F, 0F, 0F);
			this.Icicle1C = new ModelRenderer(model, 116, 52);
			this.Icicle1C.addBox(0F, 0F, 0F, 1, 1, 2);
			this.Icicle1C.setRotationPoint(7F, 10F, 5F);
			this.Icicle1C.setTextureSize(128, 64);
			this.Icicle1C.mirror = true;
			this.setRotation(this.Icicle1C, 0F, 0F, 0F);
			this.Icicle2A = new ModelRenderer(model, 122, 60);
			this.Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
			this.Icicle2A.setRotationPoint(7F, 10F, -7F);
			this.Icicle2A.setTextureSize(128, 64);
			this.Icicle2A.mirror = true;
			this.setRotation(this.Icicle2A, 0F, 0F, 0F);
			this.Icicle2B = new ModelRenderer(model, 122, 38);
			this.Icicle2B.addBox(0F, 0F, 0F, 2, 1, 1);
			this.Icicle2B.setRotationPoint(6F, 10F, -8F);
			this.Icicle2B.setTextureSize(128, 64);
			this.Icicle2B.mirror = true;
			this.setRotation(this.Icicle2B, 0F, 0F, 0F);
			this.Icicle2C = new ModelRenderer(model, 122, 44);
			this.Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle2C.setRotationPoint(7F, 12F, -7F);
			this.Icicle2C.setTextureSize(128, 64);
			this.Icicle2C.mirror = true;
			this.setRotation(this.Icicle2C, 0F, 0F, 0F);
			this.Icicle3A = new ModelRenderer(model, 106, 50);
			this.Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
			this.Icicle3A.setRotationPoint(-8F, 10F, -8F);
			this.Icicle3A.setTextureSize(128, 64);
			this.Icicle3A.mirror = true;
			this.setRotation(this.Icicle3A, 0F, 0F, 0F);
			this.Icicle3B = new ModelRenderer(model, 101, 50);
			this.Icicle3B.addBox(0F, 0F, 0F, 1, 1, 2);
			this.Icicle3B.setRotationPoint(-8F, 11F, -8F);
			this.Icicle3B.setTextureSize(128, 64);
			this.Icicle3B.mirror = true;
			this.setRotation(this.Icicle3B, 0F, 0F, 0F);
			this.Icicle3C = new ModelRenderer(model, 106, 50);
			this.Icicle3C.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle3C.setRotationPoint(-7F, 10F, -8F);
			this.Icicle3C.setTextureSize(128, 64);
			this.Icicle3C.mirror = true;
			this.setRotation(this.Icicle3C, 0F, 0F, 0F);
			this.Icicle3D = new ModelRenderer(model, 106, 46);
			this.Icicle3D.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle3D.setRotationPoint(-8F, 12F, -8F);
			this.Icicle3D.setTextureSize(128, 64);
			this.Icicle3D.mirror = true;
			this.setRotation(this.Icicle3D, 0F, 0F, 0F);
			this.Icicle4A = new ModelRenderer(model, 122, 35);
			this.Icicle4A.addBox(0F, 0F, 0F, 1, 1, 1);
			this.Icicle4A.setRotationPoint(-8F, 10F, 7F);
			this.Icicle4A.setTextureSize(128, 64);
			this.Icicle4A.mirror = true;
			this.setRotation(this.Icicle4A, 0F, 0F, 0F);
			this.Icicle4B = new ModelRenderer(model, 117, 43);
			this.Icicle4B.addBox(0F, 0F, 0F, 1, 2, 1);
			this.Icicle4B.setRotationPoint(-7F, 10F, 7F);
			this.Icicle4B.setTextureSize(128, 64);
			this.Icicle4B.mirror = true;
			this.setRotation(this.Icicle4B, 0F, 0F, 0F);
		}
	}

	public void render(float f)
	{
		if(Magistics.proxy.winter)
		{
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("craftingpillars:textures/models/furnacePillarFrozen.png"));
			this.Icicle1A.render(f);
			this.Icicle1B.render(f);
			this.Icicle1C.render(f);
			this.Icicle2A.render(f);
			this.Icicle2C.render(f);
			this.Icicle2B.render(f);
			this.Icicle3A.render(f);
			this.Icicle3B.render(f);
			this.Icicle3C.render(f);
			this.Icicle3D.render(f);
			this.Icicle4A.render(f);
			this.Icicle4B.render(f);
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_TANKPILLAR);
		}

		this.pillarbottom.render(f);
		this.pillartop.render(f);
		this.Corner1.render(f);
		this.Corner2.render(f);
		this.Corner3.render(f);
		this.Corner4.render(f);
		this.BottomTank.render(f);
		this.TopTank.render(f);

		this.GlassPane1.render(f);
		this.GlassPane2.render(f);
		this.GlassPane4.render(f);
		this.GlassPane3.render(f);

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

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_TANKPILLAR);
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

		TileEntityTankPillar tank = ((TileEntityTankPillar) tile);

		if (tank.showNum && !tank.isEmpty)
		{
			glPushMatrix();
			glTranslated(x + 0.5D, y + 1, z + 0.5D);

			glDisable(GL_LIGHTING);
			RenderingHelper.renderFloatingTextWithBackground(0, 0.35F, 0, 0.3F, tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid().getLocalizedName(),
					Color.WHITE.getRGB(), new Color(0F, 0F, 0F, 0.5F));
			RenderingHelper.renderFloatingTextWithBackground(0, 0.2F, 0, 0.2F, tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount + " Unit", Color.WHITE.getRGB(), new Color(
					0F, 0F, 0F, 0.5F));
			glEnable(GL_LIGHTING);
			glPopMatrix();

		}

		if (tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid == null || tank.isEmpty)
			return;
		EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
		if (player.getDistanceSq(tile.xCoord, tile.yCoord, tile.zCoord) < 128)
		{
			glPushMatrix();
			glTranslated(x, y, z);
			glPushAttrib(GL_ENABLE_BIT);
			glDisable(GL_LIGHTING);
			glTranslatef(0.005F, 0.005F, 0.005F);
			glScalef(0.99F, 0.99F, 0.99F);

			float[][][] field = Blobs.fieldStrength(tank.blobs);
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

			IIcon icon = tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid().getIcon();

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
			glPopAttrib();
			glPopMatrix();
		} else
		{

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
		glRotatef(90F, 0F, 1F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_TANKPILLAR);
		this.render(0.0625F);
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
		return BlockPillarTank.renderID;
	}
}
