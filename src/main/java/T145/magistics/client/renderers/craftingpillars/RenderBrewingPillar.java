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
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarBrewing;
import T145.magistics.common.tiles.craftingpillars.TilePillarBrewing;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBrewingPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_BREWINGPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};

	private ModelRenderer CraftingBottom;
	private ModelRenderer CraftingBotSlab;
	private ModelRenderer Pillar;
	private ModelRenderer WorkbenchSlab;
	private ModelRenderer WorkbenchTop;
	private ModelRenderer Holder1;
	private ModelRenderer Holder2;
	private ModelRenderer Holder3;
	private ModelRenderer Holder4;


	private ModelRenderer Icicle1A;
	private ModelRenderer Icicle1B;

	private ModelRenderer Icicle2A;
	private ModelRenderer Icicle2B;

	private ModelRenderer Icicle3A;
	private ModelRenderer Icicle3B;

	private ModelRenderer Icicle4A;

	private ModelRenderer WreathB;
	private ModelRenderer WreathA;
	private ModelRenderer WreathC;
	private ModelRenderer WreathD;
	private ModelRenderer WreathE;
	private ModelRenderer WreathF;
	private ModelRenderer WreathG;
	private ModelRenderer WreathH;
	private ModelRenderer WreathI;
	private ModelRenderer WreathJ;
	private ModelRenderer Bow;

	private RenderingHelper.ItemRender itemRenderer;

	public RenderBrewingPillar()
	{
		if(Magistics.proxy.winter)
			this.TEXTURE_BREWINGPILLAR = new ResourceLocation("craftingpillars:textures/models/brewingPillarFrozen.png");
		else
			this.TEXTURE_BREWINGPILLAR = new ResourceLocation("craftingpillars:textures/models/brewingPillar.png");

		this.itemRenderer = new RenderingHelper.ItemRender(false, false);

		model.textureWidth = 128;
		model.textureHeight = 64;

		this.CraftingBottom = new ModelRenderer(model, 0, 0);
		this.CraftingBottom.addBox(0F, 0F, 0F, 16, 2, 16);
		this.CraftingBottom.setRotationPoint(-8F, 22F, -8F);
		this.CraftingBottom.setTextureSize(128, 64);
		this.CraftingBottom.mirror = true;
		this.setRotation(this.CraftingBottom, 0F, 0F, 0F);
		this.CraftingBotSlab = new ModelRenderer(model, 0, 18);
		this.CraftingBotSlab.addBox(0F, 0F, 0F, 14, 1, 14);
		this.CraftingBotSlab.setRotationPoint(-7F, 21F, -7F);
		this.CraftingBotSlab.setTextureSize(128, 64);
		this.CraftingBotSlab.mirror = true;
		this.setRotation(this.CraftingBotSlab, 0F, 0F, 0F);
		this.Pillar = new ModelRenderer(model, 0, 39);
		this.Pillar.addBox(0F, 0F, 0F, 6, 10, 6);
		this.Pillar.setRotationPoint(-3F, 11F, -3F);
		this.Pillar.setTextureSize(128, 64);
		this.Pillar.mirror = true;
		this.setRotation(this.Pillar, 0F, 0F, 0F);
		this.WorkbenchSlab = new ModelRenderer(model, 0, 46);
		this.WorkbenchSlab.addBox(0F, 0F, 0F, 8, 1, 8);
		this.WorkbenchSlab.setRotationPoint(-4F, 10F, -4F);
		this.WorkbenchSlab.setTextureSize(128, 64);
		this.WorkbenchSlab.mirror = true;
		this.setRotation(this.WorkbenchSlab, 0F, 0F, 0F);
		this.WorkbenchTop = new ModelRenderer(model, 73, 3);
		this.WorkbenchTop.addBox(0F, 0F, 0F, 10, 2, 10);
		this.WorkbenchTop.setRotationPoint(-5F, 8F, -5F);
		this.WorkbenchTop.setTextureSize(128, 64);
		this.WorkbenchTop.mirror = true;
		this.setRotation(this.WorkbenchTop, 0F, 0F, 0F);
		this.Holder2 = new ModelRenderer(model, 0, 0);
		this.Holder2.addBox(0F, 0F, 0F, 1, 1, 3);
		this.Holder2.setRotationPoint(-0.5F, 13F, 3F);
		this.Holder2.setTextureSize(128, 64);
		this.Holder2.mirror = true;
		this.setRotation(this.Holder2, 0F, 0F, 0F);
		this.Holder1 = new ModelRenderer(model, 0, 0);
		this.Holder1.addBox(0F, 0F, 0F, 3, 1, 1);
		this.Holder1.setRotationPoint(3F, 13F, -0.5F);
		this.Holder1.setTextureSize(128, 64);
		this.Holder1.mirror = true;
		this.setRotation(this.Holder1, 0F, 0F, 0F);
		this.Holder4 = new ModelRenderer(model, 0, 0);
		this.Holder4.addBox(-3F, 0F, 0F, 3, 1, 1);
		this.Holder4.setRotationPoint(-3F, 13F, -0.5F);
		this.Holder4.setTextureSize(128, 64);
		this.Holder4.mirror = true;
		this.setRotation(this.Holder4, 0F, 0F, 0F);
		this.Holder3 = new ModelRenderer(model, 0, 0);
		this.Holder3.addBox(0F, 0F, -3F, 1, 1, 3);
		this.Holder3.setRotationPoint(-0.5F, 13F, -3F);
		this.Holder3.setTextureSize(128, 64);
		this.Holder3.mirror = true;
		this.setRotation(this.Holder3, 0F, 0F, 0F);

		this.Icicle1A = new ModelRenderer(model, 108, 46);
		this.Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle1A.setRotationPoint(-4F, 14F, -1F);
		this.Icicle1A.setTextureSize(128, 64);
		this.Icicle1A.mirror = true;
		this.setRotation(this.Icicle1A, 0F, 0F, 0F);
		this.Icicle1B = new ModelRenderer(model, 112, 44);
		this.Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle1B.setRotationPoint(-4F, 16F, -1F);
		this.Icicle1B.setTextureSize(128, 64);
		this.Icicle1B.mirror = true;
		this.setRotation(this.Icicle1B, 0F, 0F, 0F);

		this.Icicle2A = new ModelRenderer(model, 108, 46);
		this.Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle2A.setRotationPoint(3F, 14F, -1F);
		this.Icicle2A.setTextureSize(128, 64);
		this.Icicle2A.mirror = true;
		this.setRotation(this.Icicle2A, 0F, 0F, 0F);
		this.Icicle2B = new ModelRenderer(model, 112, 44);
		this.Icicle2B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle2B.setRotationPoint(3F, 16F, 0F);
		this.Icicle2B.setTextureSize(128, 64);
		this.Icicle2B.mirror = true;
		this.setRotation(this.Icicle2B, 0F, 0F, 0F);

		this.Icicle3A = new ModelRenderer(model, 108, 46);
		this.Icicle3A.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle3A.setRotationPoint(-1F, 14F, 3F);
		this.Icicle3A.setTextureSize(128, 64);
		this.Icicle3A.mirror = true;
		this.setRotation(this.Icicle3A, 0F, 0F, 0F);
		this.Icicle3B = new ModelRenderer(model, 112, 44);
		this.Icicle3B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle3B.setRotationPoint(-0.5F, 15F, 3F);
		this.Icicle3B.setTextureSize(128, 64);
		this.Icicle3B.mirror = true;
		this.setRotation(this.Icicle3B, 0F, 0F, 0F);

		this.Icicle4A = new ModelRenderer(model, 112, 44);
		this.Icicle4A.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle4A.setRotationPoint(-0.5F, 14F, -4F);
		this.Icicle4A.setTextureSize(128, 64);
		this.Icicle4A.mirror = true;
		this.setRotation(this.Icicle4A, 0F, 0F, 0F);


		this.WreathA = new ModelRenderer(model, 86, 62);
		this.WreathA.addBox(0F, 0F, 0F, 4, 1, 1);
		this.WreathA.setRotationPoint(-2F, 11F, -4F);
		this.WreathA.setTextureSize(128, 64);
		this.WreathA.mirror = true;
		this.setRotation(this.WreathA, 0F, 0F, 0F);
		this.WreathB = new ModelRenderer(model, 82, 60);
		this.WreathB.addBox(0F, 0F, 0F, 6, 1, 1);
		this.WreathB.setRotationPoint(-3F, 12F, -4F);
		this.WreathB.setTextureSize(128, 64);
		this.WreathB.mirror = true;
		this.setRotation(this.WreathB, 0F, 0F, 0F);
		this.WreathC = new ModelRenderer(model, 88, 51);
		this.WreathC.addBox(0F, 0F, 0F, 2, 4, 1);
		this.WreathC.setRotationPoint(-4F, 13F, -4F);
		this.WreathC.setTextureSize(128, 64);
		this.WreathC.mirror = true;
		this.setRotation(this.WreathC, 0F, 0F, 0F);
		this.WreathD = new ModelRenderer(model, 90, 54);
		this.WreathD.addBox(0F, 0F, 0F, 2, 4, 1);
		this.WreathD.setRotationPoint(2F, 13F, -4F);
		this.WreathD.setTextureSize(128, 64);
		this.WreathD.mirror = true;
		this.setRotation(this.WreathD, 0F, 0F, 0F);
		this.WreathE = new ModelRenderer(model, 82, 46);
		this.WreathE.addBox(0F, 0F, 0F, 6, 1, 1);
		this.WreathE.setRotationPoint(-3F, 17F, -4F);
		this.WreathE.setTextureSize(128, 64);
		this.WreathE.mirror = true;
		this.setRotation(this.WreathE, 0F, 0F, 0F);
		this.WreathF = new ModelRenderer(model, 86, 49);
		this.WreathF.addBox(0F, 0F, 0F, 4, 1, 1);
		this.WreathF.setRotationPoint(-2F, 18F, -4F);
		this.WreathF.setTextureSize(128, 64);
		this.WreathF.mirror = true;
		this.setRotation(this.WreathF, 0F, 0F, 0F);
		this.WreathG = new ModelRenderer(model, 88, 38);
		this.WreathG.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathG.setRotationPoint(-2F, 13F, -4F);
		this.WreathG.setTextureSize(128, 64);
		this.WreathG.mirror = true;
		this.setRotation(this.WreathG, 0F, 0F, 0F);
		this.WreathH = new ModelRenderer(model, 88, 38);
		this.WreathH.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathH.setRotationPoint(1F, 13F, -4F);
		this.WreathH.setTextureSize(128, 64);
		this.WreathH.mirror = true;
		this.setRotation(this.WreathH, 0F, 0F, 0F);
		this.WreathI = new ModelRenderer(model, 88, 38);
		this.WreathI.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathI.setRotationPoint(-2F, 16F, -4F);
		this.WreathI.setTextureSize(128, 64);
		this.WreathI.mirror = true;
		this.setRotation(this.WreathI, 0F, 0F, 0F);
		this.WreathJ = new ModelRenderer(model, 88, 38);
		this.WreathJ.addBox(0F, 0F, 0F, 1, 1, 1);
		this.WreathJ.setRotationPoint(1F, 16F, -4F);
		this.WreathJ.setTextureSize(128, 64);
		this.WreathJ.mirror = true;
		this.setRotation(this.WreathJ, 0F, 0F, 0F);
		this.Bow = new ModelRenderer(model, 120, 28);
		this.Bow.addBox(0F, 0F, 0F, 2, 2, 2);
		this.Bow.setRotationPoint(-1F, 11F, -5F);
		this.Bow.setTextureSize(128, 64);
		this.Bow.mirror = true;
		this.setRotation(this.Bow, 0F, 0F, 0F);
	}

	public void render(float f)
	{
		if(Magistics.proxy.winter)
		{
			this.Icicle1A.render(f);
			this.Icicle1B.render(f);
			this.Icicle2A.render(f);
			this.Icicle2B.render(f);
			this.Icicle3A.render(f);
			this.Icicle3B.render(f);
			this.Icicle4A.render(f);

			this.WreathB.render(f);
			this.WreathA.render(f);
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

		this.CraftingBottom.render(f);
		this.CraftingBotSlab.render(f);
		this.Pillar.render(f);
		this.WorkbenchSlab.render(f);
		this.WorkbenchTop.render(f);
		this.Holder2.render(f);
		this.Holder1.render(f);
		this.Holder4.render(f);
		this.Holder3.render(f);
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

		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_BREWINGPILLAR);
		this.render(0.0625F);
		glPopMatrix();

		TilePillarBrewing pillarTile = (TilePillarBrewing) tile;
		EntityItem citem = new EntityItem(tile.getWorldObj());

		glPushMatrix();

		glTranslated(x+0.5D, y, z+0.5D);



		//Input
		if(pillarTile.getStackInSlot(4) != null)
		{
			glPushMatrix();
			glScalef(1.1F,  1.1F,  1.1F);
			citem.hoverStart = -pillarTile.rot;
			citem.setEntityItemStack(pillarTile.getStackInSlot(4));
			this.itemRenderer.render(citem, 0F, 1F, 0F, pillarTile.showNum);
			glPopMatrix();
		}

		//Bottles
		for(int i = 0; i < 4; i++)
		{
			if(pillarTile.getStackInSlot(i) != null)
			{
				int rotI = i;
				if(i == 3) rotI = 0;
				if(i == 0) rotI = 3;
				glPushMatrix();
				//rotate i * pi / 2 rad (i * 90 degree)
				glRotatef(-rotI*90, 0, 1, 0);
				glTranslatef(0.4F, 0F, 0F);

				citem.hoverStart = 0F;
				citem.setEntityItemStack(pillarTile.getStackInSlot(i));
				this.itemRenderer.render(citem, 0, 0.45F, 0, false);

				//processed item
				if (pillarTile.canBrew() && pillarTile.getBrewTime() > 0  && !pillarTile.getStackInSlot(i).isItemEqual(new ItemStack(Items.glass_bottle))) {
					int j = pillarTile.getStackInSlot(i).getItemDamage();
					int k = TilePillarBrewing.getPotionResult(j, pillarTile.getStackInSlot(4));
					List list = Items.potionitem.getEffects(j);
					List list1 = Items.potionitem.getEffects(k);
					if (((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) || !ItemPotion.isSplash(j) && ItemPotion.isSplash(k))
					{
						glPushMatrix();
						//									glTranslatef(-(float) (pillarTile.getBrewTime()/ 350F), 0.2F + (float) Math.sqrt(pillarTile.getBrewTime()/ 350F), 0);
						glTranslatef(-.4F, 0, 0);
						glScalef(0.5F, 0.5F, 0.5F);

						citem.hoverStart = 0F;
						ItemStack processedItem = pillarTile.getStackInSlot(4).copy();
						processedItem.stackSize = 1;
						citem.setEntityItemStack(processedItem);
						this.itemRenderer.render(citem, -.1F + (float)Math.sqrt((1.3F-(pillarTile.getBrewTime()/ 350F))/* *0.8F*/)*.8F, 1.5F + pillarTile.getBrewTime()/ 350F, 0.01F, false);
						glPopMatrix();
					}
				}
				glPopMatrix();

				if(pillarTile.showNum)
				{
					float subX = 0;
					float subZ = 0;

					if(rotI == 0)
						subX = 0.4F;
					if(rotI == 2)
						subX = -0.4F;
					if(rotI == 1)
						subZ = 0.4F;
					if(rotI == 3)
						subZ = -0.4F;


					glPushMatrix();
					glTranslatef(subX, 0F, subZ);

					glDisable(GL_LIGHTING);
					RenderingHelper.renderFloatingTextWithBackground(0, 0.8F, 0, 0.1F, pillarTile.getStackInSlot(i).getDisplayName(), Color.WHITE.getRGB(), new Color(0F, 0F, 0F, 0.5F));
					glEnable(GL_LIGHTING);

					glPopMatrix();

				}
			}
		}

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
		//		glRotatef(90F, 0F, 1F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_BREWINGPILLAR);
		this.render(0.0625F);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	// No TileEntity here can't use
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
		return BlockPillarBrewing.renderID;
	}
}
