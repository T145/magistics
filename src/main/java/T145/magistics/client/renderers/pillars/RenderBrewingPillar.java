package T145.magistics.client.renderers.pillars;

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
import T145.magistics.client.lib.craftingpillars.ExternalRenderer;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockPillarBrewing;
import T145.magistics.common.tiles.pillars.TilePillarBrewing;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBrewingPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	public ResourceLocation texture, frozenTexture;

	public static ModelBase model = new ModelBase() {};
	private ModelRenderer CraftingBottom, CraftingBotSlab,  Pillar,  WorkbenchSlab, WorkbenchTop, Holder1, Holder2, Holder3, Holder4,  Icicle1A, Icicle1B,  Icicle2A,  Icicle2B,  Icicle3A,  Icicle3B, Icicle4A, WreathB, WreathA,  WreathC, WreathD, WreathE,  WreathF,  WreathG, WreathH, WreathI,  WreathJ, Bow;
	private ExternalRenderer itemRenderer = new ExternalRenderer(false, false);

	public RenderBrewingPillar(String modelTexture) {
		texture = new ResourceLocation("craftingpillars:textures/models/" + modelTexture + ".png");
		renderPillar();
	}

	public RenderBrewingPillar(String modelTexture, String frozenModelTexture) {
		texture = new ResourceLocation("craftingpillars:textures/models/" + modelTexture + ".png");
		frozenTexture = new ResourceLocation("craftingpillars:textures/models/" + frozenModelTexture + ".png");
		renderPillar();
	}

	public void renderPillar() {
		if (Magistics.proxy.winter && frozenTexture != null)
			texture = frozenTexture;

		model.textureWidth = 128;
		model.textureHeight = 64;

		CraftingBottom = new ModelRenderer(model, 0, 0);
		CraftingBottom.addBox(0F, 0F, 0F, 16, 2, 16);
		CraftingBottom.setRotationPoint(-8F, 22F, -8F);
		CraftingBottom.setTextureSize(128, 64);
		CraftingBottom.mirror = true;
		setRotation(CraftingBottom, 0F, 0F, 0F);
		CraftingBotSlab = new ModelRenderer(model, 0, 18);
		CraftingBotSlab.addBox(0F, 0F, 0F, 14, 1, 14);
		CraftingBotSlab.setRotationPoint(-7F, 21F, -7F);
		CraftingBotSlab.setTextureSize(128, 64);
		CraftingBotSlab.mirror = true;
		setRotation(CraftingBotSlab, 0F, 0F, 0F);
		Pillar = new ModelRenderer(model, 0, 39);
		Pillar.addBox(0F, 0F, 0F, 6, 10, 6);
		Pillar.setRotationPoint(-3F, 11F, -3F);
		Pillar.setTextureSize(128, 64);
		Pillar.mirror = true;
		setRotation(Pillar, 0F, 0F, 0F);
		WorkbenchSlab = new ModelRenderer(model, 0, 46);
		WorkbenchSlab.addBox(0F, 0F, 0F, 8, 1, 8);
		WorkbenchSlab.setRotationPoint(-4F, 10F, -4F);
		WorkbenchSlab.setTextureSize(128, 64);
		WorkbenchSlab.mirror = true;
		setRotation(WorkbenchSlab, 0F, 0F, 0F);
		WorkbenchTop = new ModelRenderer(model, 73, 3);
		WorkbenchTop.addBox(0F, 0F, 0F, 10, 2, 10);
		WorkbenchTop.setRotationPoint(-5F, 8F, -5F);
		WorkbenchTop.setTextureSize(128, 64);
		WorkbenchTop.mirror = true;
		setRotation(WorkbenchTop, 0F, 0F, 0F);
		Holder2 = new ModelRenderer(model, 0, 0);
		Holder2.addBox(0F, 0F, 0F, 1, 1, 3);
		Holder2.setRotationPoint(-0.5F, 13F, 3F);
		Holder2.setTextureSize(128, 64);
		Holder2.mirror = true;
		setRotation(Holder2, 0F, 0F, 0F);
		Holder1 = new ModelRenderer(model, 0, 0);
		Holder1.addBox(0F, 0F, 0F, 3, 1, 1);
		Holder1.setRotationPoint(3F, 13F, -0.5F);
		Holder1.setTextureSize(128, 64);
		Holder1.mirror = true;
		setRotation(Holder1, 0F, 0F, 0F);
		Holder4 = new ModelRenderer(model, 0, 0);
		Holder4.addBox(-3F, 0F, 0F, 3, 1, 1);
		Holder4.setRotationPoint(-3F, 13F, -0.5F);
		Holder4.setTextureSize(128, 64);
		Holder4.mirror = true;
		setRotation(Holder4, 0F, 0F, 0F);
		Holder3 = new ModelRenderer(model, 0, 0);
		Holder3.addBox(0F, 0F, -3F, 1, 1, 3);
		Holder3.setRotationPoint(-0.5F, 13F, -3F);
		Holder3.setTextureSize(128, 64);
		Holder3.mirror = true;
		setRotation(Holder3, 0F, 0F, 0F);

		Icicle1A = new ModelRenderer(model, 108, 46);
		Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle1A.setRotationPoint(-4F, 14F, -1F);
		Icicle1A.setTextureSize(128, 64);
		Icicle1A.mirror = true;
		setRotation(Icicle1A, 0F, 0F, 0F);
		Icicle1B = new ModelRenderer(model, 112, 44);
		Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle1B.setRotationPoint(-4F, 16F, -1F);
		Icicle1B.setTextureSize(128, 64);
		Icicle1B.mirror = true;
		setRotation(Icicle1B, 0F, 0F, 0F);

		Icicle2A = new ModelRenderer(model, 108, 46);
		Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle2A.setRotationPoint(3F, 14F, -1F);
		Icicle2A.setTextureSize(128, 64);
		Icicle2A.mirror = true;
		setRotation(Icicle2A, 0F, 0F, 0F);
		Icicle2B = new ModelRenderer(model, 112, 44);
		Icicle2B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle2B.setRotationPoint(3F, 16F, 0F);
		Icicle2B.setTextureSize(128, 64);
		Icicle2B.mirror = true;
		setRotation(Icicle2B, 0F, 0F, 0F);

		Icicle3A = new ModelRenderer(model, 108, 46);
		Icicle3A.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle3A.setRotationPoint(-1F, 14F, 3F);
		Icicle3A.setTextureSize(128, 64);
		Icicle3A.mirror = true;
		setRotation(Icicle3A, 0F, 0F, 0F);
		Icicle3B = new ModelRenderer(model, 112, 44);
		Icicle3B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle3B.setRotationPoint(-0.5F, 15F, 3F);
		Icicle3B.setTextureSize(128, 64);
		Icicle3B.mirror = true;
		setRotation(Icicle3B, 0F, 0F, 0F);

		Icicle4A = new ModelRenderer(model, 112, 44);
		Icicle4A.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle4A.setRotationPoint(-0.5F, 14F, -4F);
		Icicle4A.setTextureSize(128, 64);
		Icicle4A.mirror = true;
		setRotation(Icicle4A, 0F, 0F, 0F);

		WreathA = new ModelRenderer(model, 86, 62);
		WreathA.addBox(0F, 0F, 0F, 4, 1, 1);
		WreathA.setRotationPoint(-2F, 11F, -4F);
		WreathA.setTextureSize(128, 64);
		WreathA.mirror = true;
		setRotation(WreathA, 0F, 0F, 0F);
		WreathB = new ModelRenderer(model, 82, 60);
		WreathB.addBox(0F, 0F, 0F, 6, 1, 1);
		WreathB.setRotationPoint(-3F, 12F, -4F);
		WreathB.setTextureSize(128, 64);
		WreathB.mirror = true;
		setRotation(WreathB, 0F, 0F, 0F);
		WreathC = new ModelRenderer(model, 88, 51);
		WreathC.addBox(0F, 0F, 0F, 2, 4, 1);
		WreathC.setRotationPoint(-4F, 13F, -4F);
		WreathC.setTextureSize(128, 64);
		WreathC.mirror = true;
		setRotation(WreathC, 0F, 0F, 0F);
		WreathD = new ModelRenderer(model, 90, 54);
		WreathD.addBox(0F, 0F, 0F, 2, 4, 1);
		WreathD.setRotationPoint(2F, 13F, -4F);
		WreathD.setTextureSize(128, 64);
		WreathD.mirror = true;
		setRotation(WreathD, 0F, 0F, 0F);
		WreathE = new ModelRenderer(model, 82, 46);
		WreathE.addBox(0F, 0F, 0F, 6, 1, 1);
		WreathE.setRotationPoint(-3F, 17F, -4F);
		WreathE.setTextureSize(128, 64);
		WreathE.mirror = true;
		setRotation(WreathE, 0F, 0F, 0F);
		WreathF = new ModelRenderer(model, 86, 49);
		WreathF.addBox(0F, 0F, 0F, 4, 1, 1);
		WreathF.setRotationPoint(-2F, 18F, -4F);
		WreathF.setTextureSize(128, 64);
		WreathF.mirror = true;
		setRotation(WreathF, 0F, 0F, 0F);
		WreathG = new ModelRenderer(model, 88, 38);
		WreathG.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathG.setRotationPoint(-2F, 13F, -4F);
		WreathG.setTextureSize(128, 64);
		WreathG.mirror = true;
		setRotation(WreathG, 0F, 0F, 0F);
		WreathH = new ModelRenderer(model, 88, 38);
		WreathH.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathH.setRotationPoint(1F, 13F, -4F);
		WreathH.setTextureSize(128, 64);
		WreathH.mirror = true;
		setRotation(WreathH, 0F, 0F, 0F);
		WreathI = new ModelRenderer(model, 88, 38);
		WreathI.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathI.setRotationPoint(-2F, 16F, -4F);
		WreathI.setTextureSize(128, 64);
		WreathI.mirror = true;
		setRotation(WreathI, 0F, 0F, 0F);
		WreathJ = new ModelRenderer(model, 88, 38);
		WreathJ.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathJ.setRotationPoint(1F, 16F, -4F);
		WreathJ.setTextureSize(128, 64);
		WreathJ.mirror = true;
		setRotation(WreathJ, 0F, 0F, 0F);
		Bow = new ModelRenderer(model, 120, 28);
		Bow.addBox(0F, 0F, 0F, 2, 2, 2);
		Bow.setRotationPoint(-1F, 11F, -5F);
		Bow.setTextureSize(128, 64);
		Bow.mirror = true;
		setRotation(Bow, 0F, 0F, 0F);
	}

	public void render(float f) {
		if (Magistics.proxy.winter) {
			Icicle1A.render(f);
			Icicle1B.render(f);
			Icicle2A.render(f);
			Icicle2B.render(f);
			Icicle3A.render(f);
			Icicle3B.render(f);
			Icicle4A.render(f);

			WreathB.render(f);
			WreathA.render(f);
			WreathC.render(f);
			WreathD.render(f);
			WreathE.render(f);
			WreathF.render(f);
			WreathG.render(f);
			WreathH.render(f);
			WreathI.render(f);
			WreathJ.render(f);
			Bow.render(f);
		}

		CraftingBottom.render(f);
		CraftingBotSlab.render(f);
		Pillar.render(f);
		WorkbenchSlab.render(f);
		WorkbenchTop.render(f);
		Holder2.render(f);
		Holder1.render(f);
		Holder4.render(f);
		Holder3.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(180F, 1F, 0F, 0F);
		glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		render(0.0625F);
		glPopMatrix();

		TilePillarBrewing pillarTile = (TilePillarBrewing) tile;
		EntityItem citem = new EntityItem(tile.getWorldObj());

		glPushMatrix();
		glTranslated(x + 0.5D, y, z + 0.5D);

		if (pillarTile.getStackInSlot(4) != null) {
			glPushMatrix();
			glScalef(1.1F, 1.1F, 1.1F);
			citem.hoverStart = -pillarTile.rot;
			citem.setEntityItemStack(pillarTile.getStackInSlot(4));
			itemRenderer.render(citem, 0F, 1F, 0F, pillarTile.showNum);
			glPopMatrix();
		}

		for (int i = 0; i < 4; i++)
			if (pillarTile.getStackInSlot(i) != null) {
				int rotI = i;
				if (i == 3)
					rotI = 0;
				if (i == 0)
					rotI = 3;
				glPushMatrix();
				glRotatef(-rotI * 90, 0, 1, 0);
				glTranslatef(0.4F, 0F, 0F);

				citem.hoverStart = 0F;
				citem.setEntityItemStack(pillarTile.getStackInSlot(i));
				itemRenderer.render(citem, 0, 0.45F, 0, false);

				if (pillarTile.canBrew() && pillarTile.getBrewTime() > 0 && !pillarTile.getStackInSlot(i).isItemEqual(new ItemStack(Items.glass_bottle))) {
					int j = pillarTile.getStackInSlot(i).getItemDamage(), k = TilePillarBrewing.getPotionResult(j, pillarTile.getStackInSlot(4));
					List list = Items.potionitem.getEffects(j), list1 = Items.potionitem.getEffects(k);
					if (((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) || !ItemPotion.isSplash(j) && ItemPotion.isSplash(k)) {
						glPushMatrix();
						glTranslatef(-.4F, 0, 0);
						glScalef(0.5F, 0.5F, 0.5F);

						citem.hoverStart = 0F;
						ItemStack processedItem = pillarTile.getStackInSlot(4).copy();
						processedItem.stackSize = 1;
						citem.setEntityItemStack(processedItem);
						itemRenderer.render(citem, -.1F + (float) Math.sqrt((1.3F - (pillarTile.getBrewTime() / 350F))) * .8F, 1.5F + pillarTile.getBrewTime() / 350F, 0.01F, false);
						glPopMatrix();
					}
				}
				glPopMatrix();

				if (pillarTile.showNum) {
					float subX = 0;
					float subZ = 0;

					if (rotI == 0)
						subX = 0.4F;
					if (rotI == 2)
						subX = -0.4F;
					if (rotI == 1)
						subZ = 0.4F;
					if (rotI == 3)
						subZ = -0.4F;

					glPushMatrix();
					glTranslatef(subX, 0F, subZ);

					glDisable(GL_LIGHTING);
					itemRenderer.renderFloatingTextWithBackground(0, 0.8F, 0, 0.1F, pillarTile.getStackInSlot(i).getDisplayName(), Color.WHITE.getRGB(), new Color(0F, 0F, 0F, 0.5F));
					glEnable(GL_LIGHTING);

					glPopMatrix();

				}
			}

		glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		render(0.0625F);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockPillarBrewing.renderID;
	}
}