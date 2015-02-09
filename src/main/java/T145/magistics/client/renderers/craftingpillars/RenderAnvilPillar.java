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
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TileEntityAnvilPillar;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderAnvilPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	ModelRenderer CraftingBottom;
	ModelRenderer CraftingBotSlab;
	ModelRenderer WorkbenchSlab;
	ModelRenderer WorkbenchTop;
	ModelRenderer Pillar2;

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

	private ResourceLocation TEXTURE_ANVILPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};

	private Random random;
	private RenderingHelper.ItemRender itemRenderer;

	public RenderAnvilPillar()
	{
		this.random = new Random();
		this.itemRenderer = new RenderingHelper.ItemRender(false, true);

		if(Magistics.proxy.winter)
			this.TEXTURE_ANVILPILLAR = new ResourceLocation("craftingpillars:textures/models/anvilPillarFrozen.png");
		else
			this.TEXTURE_ANVILPILLAR = new ResourceLocation("craftingpillars:textures/models/anvilPillar.png");


		model.textureWidth = 128;
		model.textureHeight = 64;

		this.CraftingBottom = new ModelRenderer(model, 0, 0);
		this.CraftingBottom.addBox(-6F, 0F, 0F, 12, 2, 16);
		this.CraftingBottom.setRotationPoint(0F, 22F, -8F);
		this.CraftingBottom.setTextureSize(128, 64);
		this.CraftingBottom.mirror = true;
		this.setRotation(this.CraftingBottom, 0F, 0F, 0F);
		this.CraftingBotSlab = new ModelRenderer(model, 0, 18);
		this.CraftingBotSlab.addBox(-5F, 0F, 0F, 10, 1, 14);
		this.CraftingBotSlab.setRotationPoint(0F, 21F, -7F);
		this.CraftingBotSlab.setTextureSize(128, 64);
		this.CraftingBotSlab.mirror = true;
		this.setRotation(this.CraftingBotSlab, 0F, 0F, 0F);
		this.WorkbenchSlab = new ModelRenderer(model, 48, 19);
		this.WorkbenchSlab.addBox(-4F, 0F, 0F, 8, 1, 14);
		this.WorkbenchSlab.setRotationPoint(0F, 11F, -7F);
		this.WorkbenchSlab.setTextureSize(128, 64);
		this.WorkbenchSlab.mirror = true;
		this.setRotation(this.WorkbenchSlab, 0F, 0F, 0F);
		this.WorkbenchTop = new ModelRenderer(model, 68, 0);
		this.WorkbenchTop.addBox(-5F, 0F, 0F, 10, 3, 16);
		this.WorkbenchTop.setRotationPoint(0F, 8F, -8F);
		this.WorkbenchTop.setTextureSize(128, 64);
		this.WorkbenchTop.mirror = true;
		this.setRotation(this.WorkbenchTop, 0F, 0F, 0F);
		this.Pillar2 = new ModelRenderer(model, 0, 40);
		this.Pillar2.addBox(-4F, 0F, -3F, 8, 9, 6);
		this.Pillar2.setRotationPoint(0F, 12F, 0F);
		this.Pillar2.setTextureSize(128, 64);
		this.Pillar2.mirror = true;
		this.setRotation(this.Pillar2, 0F, 1.570796F, 0F);

		//Winter
		this.Icicle1A = new ModelRenderer(model, 122, 60);
		this.Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle1A.setRotationPoint(4F, 11F, -5F);
		this.Icicle1A.setTextureSize(128, 64);
		this.Icicle1A.mirror = true;
		this.setRotation(this.Icicle1A, 0F, 0F, 0F);
		this.Icicle1B = new ModelRenderer(model, 124, 58);
		this.Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle1B.setRotationPoint(4F, 11F, -3F);
		this.Icicle1B.setTextureSize(128, 64);
		this.Icicle1B.mirror = true;
		this.setRotation(this.Icicle1B, 0F, 0F, 0F);
		this.Icicle1C = new ModelRenderer(model, 124, 56);
		this.Icicle1C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle1C.setRotationPoint(4F, 13F, -4F);
		this.Icicle1C.setTextureSize(128, 64);
		this.Icicle1C.mirror = true;
		this.setRotation(this.Icicle1C, 0F, 0F, 0F);

		this.Icicle2A = new ModelRenderer(model, 122, 50);
		this.Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle2A.setRotationPoint(4F, 11F, 0F);
		this.Icicle2A.setTextureSize(128, 64);
		this.Icicle2A.mirror = true;
		this.setRotation(this.Icicle2A, 0F, 0F, 0F);
		this.Icicle2B = new ModelRenderer(model, 124, 47);
		this.Icicle2B.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle2B.setRotationPoint(4F, 13F, 0F);
		this.Icicle2B.setTextureSize(128, 64);
		this.Icicle2B.mirror = true;
		this.setRotation(this.Icicle2B, 0F, 0F, 0F);
		this.Icicle2C = new ModelRenderer(model, 124, 54);
		this.Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle2C.setRotationPoint(4F, 11F, -1F);
		this.Icicle2C.setTextureSize(128, 64);
		this.Icicle2C.mirror = true;
		this.setRotation(this.Icicle2C, 0F, 0F, 0F);

		this.Icicle3A = new ModelRenderer(model, 120, 43);
		this.Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
		this.Icicle3A.setRotationPoint(4F, 11F, 3F);
		this.Icicle3A.setTextureSize(128, 64);
		this.Icicle3A.mirror = true;
		this.setRotation(this.Icicle3A, 0F, 0F, 0F);
		this.Icicle3B = new ModelRenderer(model, 124, 40);
		this.Icicle3B.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle3B.setRotationPoint(4F, 12F, 4F);
		this.Icicle3B.setTextureSize(128, 64);
		this.Icicle3B.mirror = true;
		this.setRotation(this.Icicle3B, 0F, 0F, 0F);

		this.Icicle4A = new ModelRenderer(model, 122, 38);
		this.Icicle4A.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle4A.setRotationPoint(1F, 11F, 7F);
		this.Icicle4A.setTextureSize(128, 64);
		this.Icicle4A.mirror = true;
		this.setRotation(this.Icicle4A, 0F, 0F, 0F);
		this.Icicle4B = new ModelRenderer(model, 124, 36);
		this.Icicle4B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle4B.setRotationPoint(2F, 12F, 7F);
		this.Icicle4B.setTextureSize(128, 64);
		this.Icicle4B.mirror = true;
		this.setRotation(this.Icicle4B, 0F, 0F, 0F);

		this.Icicle5A = new ModelRenderer(model, 114, 61);
		this.Icicle5A.addBox(0F, 0F, 0F, 3, 2, 1);
		this.Icicle5A.setRotationPoint(-3F, 11F, 7F);
		this.Icicle5A.setTextureSize(128, 64);
		this.Icicle5A.mirror = true;
		this.setRotation(this.Icicle5A, 0F, 0F, 0F);
		this.Icicle5B = new ModelRenderer(model, 116, 59);
		this.Icicle5B.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle5B.setRotationPoint(-3F, 13F, 7F);
		this.Icicle5B.setTextureSize(128, 64);
		this.Icicle5B.mirror = true;
		this.setRotation(this.Icicle5B, 0F, 0F, 0F);
		this.Icicle5C = new ModelRenderer(model, 120, 56);
		this.Icicle5C.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle5C.setRotationPoint(-2F, 14F, 7F);
		this.Icicle5C.setTextureSize(128, 64);
		this.Icicle5C.mirror = true;
		this.setRotation(this.Icicle5C, 0F, 0F, 0F);

		this.Icicle6A = new ModelRenderer(model, 114, 54);
		this.Icicle6A.addBox(0F, 0F, 0F, 4, 1, 1);
		this.Icicle6A.setRotationPoint(-3F, 11F, -8F);
		this.Icicle6A.setTextureSize(128, 64);
		this.Icicle6A.mirror = true;
		this.setRotation(this.Icicle6A, 0F, 0F, 0F);
		this.Icicle6B = new ModelRenderer(model, 116, 52);
		this.Icicle6B.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle6B.setRotationPoint(-2F, 12F, -8F);
		this.Icicle6B.setTextureSize(128, 64);
		this.Icicle6B.mirror = true;
		this.setRotation(this.Icicle6B, 0F, 0F, 0F);
		this.Icicle6C = new ModelRenderer(model, 118, 50);
		this.Icicle6C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle6C.setRotationPoint(-2F, 13F, -8F);
		this.Icicle6C.setTextureSize(128, 64);
		this.Icicle6C.mirror = true;

		this.Icicle7A = new ModelRenderer(model, 122, 60);
		this.Icicle7A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle7A.setRotationPoint(-5F, 11F, -5F);
		this.Icicle7A.setTextureSize(128, 64);
		this.Icicle7A.mirror = true;
		this.setRotation(this.Icicle7A, 0F, 0F, 0F);
		this.Icicle7B = new ModelRenderer(model, 124, 58);
		this.Icicle7B.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle7B.setRotationPoint(-5F, 11F, -3F);
		this.Icicle7B.setTextureSize(128, 64);
		this.Icicle7B.mirror = true;
		this.setRotation(this.Icicle7B, 0F, 0F, 0F);
		this.Icicle7C = new ModelRenderer(model, 124, 56);
		this.Icicle7C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle7C.setRotationPoint(-5F, 13F, -4F);
		this.Icicle7C.setTextureSize(128, 64);
		this.Icicle7C.mirror = true;
		this.setRotation(this.Icicle7C, 0F, 0F, 0F);

		this.Icicle8A = new ModelRenderer(model, 122, 50);
		this.Icicle8A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle8A.setRotationPoint(-5F, 11F, 2F);
		this.Icicle8A.setTextureSize(128, 64);
		this.Icicle8A.mirror = true;
		this.setRotation(this.Icicle8A, 0F, 0F, 0F);
		this.Icicle8B = new ModelRenderer(model, 124, 47);
		this.Icicle8B.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle8B.setRotationPoint(-5F, 13F, 2F);
		this.Icicle8B.setTextureSize(128, 64);
		this.Icicle8B.mirror = true;
		this.setRotation(this.Icicle8B, 0F, 0F, 0F);
		this.Icicle8C = new ModelRenderer(model, 124, 54);
		this.Icicle8C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle8C.setRotationPoint(-5F, 11F, 1F);
		this.Icicle8C.setTextureSize(128, 64);
		this.Icicle8C.mirror = true;
		this.setRotation(this.Icicle8C, 0F, 0F, 0F);
	}

	public void render(TileEntity tileentity, float f)
	{
		if(Magistics.proxy.winter)
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
		}

		this.CraftingBottom.render(f);
		this.CraftingBotSlab.render(f);
		this.WorkbenchSlab.render(f);
		this.WorkbenchTop.render(f);
		this.Pillar2.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_ANVILPILLAR);
		this.render(null, 0.0625F);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return ConfigObjects.anvilPillarRenderID;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		TileEntityAnvilPillar anvil = (TileEntityAnvilPillar)tile;
		
		glPushMatrix();
			glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
			glRotatef(180F, 1F, 0F, 0F);
			glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 1), 0F, 1F, 0F);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_ANVILPILLAR);
			this.render(tile, 0.0625F);
		glPopMatrix();

		EntityItem citem = new EntityItem(tile.getWorldObj());
		citem.hoverStart = 0F;
		
		if(anvil.getStackInSlot(0) != null)
		{
			glPushMatrix();
				if(!(anvil.getStackInSlot(0).getItem() instanceof ItemBlock)) //if not block
				{
					glTranslated(x + 0.5D, y + 1.024D, z + 0.5D);
					glRotatef(-90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)+2), 0F, 1F, 0F);
					glTranslatef(0, 0, -0.125F);
					glRotatef(90F, 1F, 0F, 0F);
				}
				else
				{
					glTranslated(x + 0.5D, y + 1.125D, z + 0.5D);
					glRotatef(-90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)+2), 0F, 1F, 0F);
				}
				citem.setEntityItemStack(anvil.getStackInSlot(0));
				this.itemRenderer.render(citem, 0, 0, 0, false);
				
			glPopMatrix();
			byte cost = 0;
			
			for(int i = 0; i < 4; i++)
			{
				if(anvil.getStackInSlot(i+1) != null)
					cost++;
			}
			
			if(cost > 0 && anvil.showNum)
			{
				glPushMatrix();
					glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
					glDisable(GL_LIGHTING);
					RenderingHelper.renderFloatingTextWithBackground(0, 1, 0, 0.3F, cost + "", 8453920, new Color(0F, 0F, 0F, 0.5F));
					glEnable(GL_LIGHTING);
				
				glPopMatrix();
			}
		}
		
		for(int i = 0; i < anvil.getSizeInventory() - 1; i++)
		{
			if(anvil.getStackInSlot(i+1) != null)
			{
				if(!(anvil.getStackInSlot(i+1).getItem() instanceof ItemBlock)) //if not block
				{
					glPushMatrix();
						glTranslated(x+0.5D, y + 1.024D, z+0.5D);
						float scale = 0.5F;
						glScalef(scale, scale, scale);
						glRotatef(-90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)+2), 0F, 1F, 0F);

						//rotate i * pi / 2 rad (i * 90 degree)
						glRotatef(-i*90, 0, 1, 0);
						
						glTranslatef(0.5F, 0F, 0F);
						glRotatef(90F, 1F, 0F, 0F);
						
						glRotatef(90F * 3F, 0, 0, 1F);
	
						citem.setEntityItemStack(anvil.getStackInSlot(i+1));
						this.itemRenderer.render(citem, 0, 0, 0, false);
					
					glPopMatrix();
				}
				else
				{
					glPushMatrix();
					
						glTranslated(x+0.5D, y + 1.06D, z+0.5D);
						float scale = 0.5F;
						glScalef(scale, scale, scale);
						glRotatef(-90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)+2), 0F, 1F, 0F);

						//rotate i * pi / 2 rad (i * 90 degree)
						glRotatef(-i*90, 0, 1, 0);
						
						glTranslatef(0.5F, 0F, 0F);
						glRotatef(90F, 1F, 0F, 0F);
						
						glRotatef(90F * 2F, 0, 0, 1F);
						glRotatef(90F * 3F, 1, 0, 0);
						
						citem.setEntityItemStack(anvil.getStackInSlot(i+1));
						this.itemRenderer.render(citem, 0, 0, 0, false);
						
					glPopMatrix();
				}
			}
		}

		//16777215
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
}
