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
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.client.lib.pillars.ExternalRenderer;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.pillars.BlockPillarFurnace;
import T145.magistics.common.tiles.pillars.TilePillarFurnace;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFurnacePillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	private ResourceLocation texture, frozenTexture;

	public static ModelBase model = new ModelBase() {};
	private ModelRenderer CraftingBottom, CraftingBotSlab, Pillar1, WorkbenchSlab, WorkbenchTop, Pillar2, Pillar3, Pillar4, BunnyTail1, BunnyTail2, BunnyTail3, BunnyEar1, BunnyEar2, Icicle1A, Icicle1B, Icicle1C, Icicle2A, Icicle2C, Icicle2B, Icicle3A, Icicle3B, Icicle3C, Icicle3D, Icicle4A, Icicle4B;
	private ExternalRenderer itemRenderer = new ExternalRenderer(false, true), resultRenderer = new ExternalRenderer(false, false);

	public RenderFurnacePillar(String modelTexture) {
		texture = new ResourceLocation("magistics:textures/models/pillars/" + modelTexture + ".png");
		renderPillar();
	}

	public RenderFurnacePillar(String modelTexture, String frozenModelTexture) {
		texture = new ResourceLocation("magistics:textures/models/pillars/" + modelTexture + ".png");
		frozenTexture = new ResourceLocation("magistics:textures/models/pillars/" + frozenModelTexture + ".png");
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
		Pillar1 = new ModelRenderer(model, 2, 43);
		Pillar1.addBox(0F, 0F, 0F, 2, 10, 2);
		Pillar1.setRotationPoint(-6F, 11F, -6F);
		Pillar1.setTextureSize(128, 64);
		Pillar1.mirror = true;
		setRotation(Pillar1, 0F, 0F, 0F);
		WorkbenchSlab = new ModelRenderer(model, 0, 18);
		WorkbenchSlab.addBox(0F, 0F, 0F, 14, 1, 14);
		WorkbenchSlab.setRotationPoint(-7F, 10F, -7F);
		WorkbenchSlab.setTextureSize(128, 64);
		WorkbenchSlab.mirror = true;
		setRotation(WorkbenchSlab, 0F, 0F, 0F);
		WorkbenchTop = new ModelRenderer(model, 64, 0);
		WorkbenchTop.addBox(0F, 0F, 0F, 16, 2, 16);
		WorkbenchTop.setRotationPoint(-8F, 8F, -8F);
		WorkbenchTop.setTextureSize(128, 64);
		WorkbenchTop.mirror = true;
		setRotation(WorkbenchTop, 0F, 0F, 0F);
		Pillar2 = new ModelRenderer(model, 2, 43);
		Pillar2.addBox(-2F, 0F, -2F, 2, 10, 2);
		Pillar2.setRotationPoint(6F, 11F, -6F);
		Pillar2.setTextureSize(128, 64);
		Pillar2.mirror = true;
		setRotation(Pillar2, 0F, 1.570796F, 0F);
		Pillar3 = new ModelRenderer(model, 2, 43);
		Pillar3.addBox(0F, 0F, 0F, 2, 10, 2);
		Pillar3.setRotationPoint(-6F, 11F, 6F);
		Pillar3.setTextureSize(128, 64);
		Pillar3.mirror = true;
		setRotation(Pillar3, 0F, 1.570796F, 0F);
		Pillar4 = new ModelRenderer(model, 2, 43);
		Pillar4.addBox(-2F, 0F, -2F, 2, 10, 2);
		Pillar4.setRotationPoint(6F, 11F, 6F);
		Pillar4.setTextureSize(128, 64);
		Pillar4.mirror = true;
		setRotation(Pillar4, 0F, 0F, 0F);

		if (Magistics.proxy.easter) {
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

		Icicle1A = new ModelRenderer(model, 122, 38);
		Icicle1A.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle1A.setRotationPoint(5F, 11F, 6F);
		Icicle1A.setTextureSize(128, 64);
		Icicle1A.mirror = true;
		setRotation(Icicle1A, 0F, 0F, 0F);
		Icicle1B = new ModelRenderer(model, 122, 40);
		Icicle1B.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle1B.setRotationPoint(6F, 12F, 6F);
		Icicle1B.setTextureSize(128, 64);
		Icicle1B.mirror = true;
		setRotation(Icicle1B, 0F, 0F, 0F);
		Icicle1C = new ModelRenderer(model, 116, 52);
		Icicle1C.addBox(0F, 0F, 0F, 1, 1, 2);
		Icicle1C.setRotationPoint(6F, 11F, 4F);
		Icicle1C.setTextureSize(128, 64);
		Icicle1C.mirror = true;
		setRotation(Icicle1C, 0F, 0F, 0F);
		Icicle2A = new ModelRenderer(model, 122, 60);
		Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle2A.setRotationPoint(6F, 11F, -6F);
		Icicle2A.setTextureSize(128, 64);
		Icicle2A.mirror = true;
		setRotation(Icicle2A, 0F, 0F, 0F);
		Icicle2B = new ModelRenderer(model, 122, 38);
		Icicle2B.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle2B.setRotationPoint(5F, 11F, -7F);
		Icicle2B.setTextureSize(128, 64);
		Icicle2B.mirror = true;
		setRotation(Icicle2B, 0F, 0F, 0F);
		Icicle2C = new ModelRenderer(model, 122, 44);
		Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle2C.setRotationPoint(6F, 13F, -6F);
		Icicle2C.setTextureSize(128, 64);
		Icicle2C.mirror = true;
		setRotation(Icicle2C, 0F, 0F, 0F);
		Icicle3A = new ModelRenderer(model, 106, 50);
		Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
		Icicle3A.setRotationPoint(-7F, 11F, -7F);
		Icicle3A.setTextureSize(128, 64);
		Icicle3A.mirror = true;
		setRotation(Icicle3A, 0F, 0F, 0F);
		Icicle3B = new ModelRenderer(model, 101, 50);
		Icicle3B.addBox(0F, 0F, 0F, 1, 1, 2);
		Icicle3B.setRotationPoint(-7F, 12F, -7F);
		Icicle3B.setTextureSize(128, 64);
		Icicle3B.mirror = true;
		setRotation(Icicle3B, 0F, 0F, 0F);
		Icicle3C = new ModelRenderer(model, 106, 50);
		Icicle3C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle3C.setRotationPoint(-6F, 11F, -7F);
		Icicle3C.setTextureSize(128, 64);
		Icicle3C.mirror = true;
		setRotation(Icicle3C, 0F, 0F, 0F);
		Icicle3D = new ModelRenderer(model, 106, 46);
		Icicle3D.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle3D.setRotationPoint(-7F, 13F, -7F);
		Icicle3D.setTextureSize(128, 64);
		Icicle3D.mirror = true;
		setRotation(Icicle3D, 0F, 0F, 0F);
		Icicle4A = new ModelRenderer(model, 122, 35);
		Icicle4A.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle4A.setRotationPoint(-7F, 11F, 6F);
		Icicle4A.setTextureSize(128, 64);
		Icicle4A.mirror = true;
		setRotation(Icicle4A, 0F, 0F, 0F);
		Icicle4B = new ModelRenderer(model, 117, 43);
		Icicle4B.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle4B.setRotationPoint(-6F, 11F, 6F);
		Icicle4B.setTextureSize(128, 64);
		Icicle4B.mirror = true;
		setRotation(Icicle4B, 0F, 0F, 0F);
	}

	public void render(float f) {
		if (Magistics.proxy.winter) {
			Icicle1A.render(f);
			Icicle1B.render(f);
			Icicle1C.render(f);
			Icicle2A.render(f);
			Icicle2C.render(f);
			Icicle2B.render(f);
			Icicle3A.render(f);
			Icicle3B.render(f);
			Icicle3C.render(f);
			Icicle3D.render(f);
			Icicle4A.render(f);
			Icicle4B.render(f);
		}

		CraftingBottom.render(f);
		CraftingBotSlab.render(f);
		Pillar1.render(f);
		WorkbenchSlab.render(f);
		WorkbenchTop.render(f);
		Pillar2.render(f);
		Pillar3.render(f);
		Pillar4.render(f);
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

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		render(0.0625F);

		if (Magistics.proxy.easter) {
			glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

			f = 0.0625F;
			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
		}
		glPopMatrix();

		TilePillarFurnace pillarTile = (TilePillarFurnace) tile;
		EntityItem citem = new EntityItem(tile.getWorldObj());

		glPushMatrix();

		glTranslated(x + 0.5D, y, z + 0.5D);
		glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

		// Input
		if (pillarTile.getStackInSlot(0) != null) {
			glPushMatrix();
			citem.hoverStart = 0F;
			citem.setEntityItemStack(pillarTile.getStackInSlot(0));
			resultRenderer.render(citem, 0F, 1.125F, 0F, false);
			glPopMatrix();
		}

		// Output
		if (pillarTile.getStackInSlot(2) != null) {
			glPushMatrix();
			glTranslatef(0F, 1.75F, 0F);
			citem.hoverStart = 0F;
			citem.setEntityItemStack(pillarTile.getStackInSlot(2));
			resultRenderer.render(citem, 0F, 0F, 0F, false);
			glPopMatrix();
		}

		// processed item
		if (pillarTile.canSmelt() && pillarTile.burnTime > 0) {
			glPushMatrix();
			glTranslatef(0F, 1.75F - pillarTile.cookTime / 150F, 0F);
			citem.hoverStart = 0F;
			citem.setEntityItemStack(FurnaceRecipes.smelting().getSmeltingResult(pillarTile.getStackInSlot(0)));
			resultRenderer.render(citem, 0.01F, 0F, 0.01F, false);
			glPopMatrix();
		}

		// Fuel
		if (pillarTile.getStackInSlot(1) != null) {
			citem.hoverStart = 0F;
			citem.setEntityItemStack(pillarTile.getStackInSlot(1));
			itemRenderer.render(citem, 0F, 0.3F, 0F, false);
		}
		glPopMatrix();

		if (pillarTile.showNum) {
			glPushMatrix();
			glTranslated(x + 0.5D, y, z + 0.5D);

			if (pillarTile.getStackInSlot(0) != null) {
				glDisable(GL_LIGHTING);
				resultRenderer.renderFloatingTextWithBackground(0, 1.425F, 0, 0.4F, "" + pillarTile.getStackInSlot(0).stackSize, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);
			}
			if (pillarTile.getStackInSlot(1) != null) {
				glDisable(GL_LIGHTING);
				resultRenderer.renderFloatingTextWithBackground(0, 0.7F, 0, 0.4F, "" + pillarTile.getStackInSlot(1).stackSize, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);
			}
			if (pillarTile.getStackInSlot(2) != null) {
				glDisable(GL_LIGHTING);
				resultRenderer.renderFloatingTextWithBackground(0, 2.15F, 0, 0.4F, "" + pillarTile.getStackInSlot(2).stackSize, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);
			}
			glPopMatrix();
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		glRotatef(90F, 0F, 1F, 0F);
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
		return BlockPillarFurnace.renderID;
	}
}