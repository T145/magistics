package T145.magistics.client.render.blocks;

import java.util.Random;

import com.google.common.primitives.SignedBytes;

import T145.magistics.Magistics;
import T145.magistics.blocks.devices.BlockChestHungryMetal;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChestHungryMetal extends TileEntitySpecialRenderer<TileEntityIronChest> {

	private Random random;
	private RenderEntityItem itemRenderer;
	private ModelChest model;

	private static float[][] shifts = { { 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F }, { 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F }, { 0.7F, 0.1F, 0.3F }, { 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F } };
	private static EntityItem customitem = new EntityItem(null);
	private static float halfPI = (float) (Math.PI / 2D);

	public RenderChestHungryMetal() {
		model = new ModelChest();
		random = new Random();
	}

	public static ResourceLocation[] getChestTextures() {
		ResourceLocation[] textures = new ResourceLocation[IronChestType.values().length];

		for (IronChestType type : IronChestType.values()) {
			if (type != type.WOOD) {
				textures[type.ordinal()] = new ResourceLocation(Magistics.MODID, "textures/models/chest_hungry/" + BlockChestHungryMetal.getSimpleChestName(type) + ".png");
			}
		}

		return textures;
	}

	@Override
	public void renderTileEntityAt(TileEntityIronChest te, double x, double y, double z, float partialTicks, int destroyStage) {
		if (te == null || te.isInvalid()) {
			return;
		}

		EnumFacing facing = te.getFacing();
		IronChestType type = te.getType();

		if (destroyStage >= 0) {
			bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4F, 4F, 1F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else {
			bindTexture(getChestTextures()[type.ordinal()]);
		}

		GlStateManager.pushMatrix();

		if (type == IronChestType.CRYSTAL) {
			GlStateManager.disableCull();
		}

		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate((float) x, (float) y + 1F, (float) z + 1F);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);

		switch (facing) {
		case NORTH:
			GlStateManager.rotate(180F, 0F, 1F, 0F);
			break;
		case SOUTH:
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			break;
		case WEST:
			GlStateManager.rotate(90F, 0F, 1F, 0F);
			break;
		case EAST:
			GlStateManager.rotate(270F, 0F, 1F, 0F);
			break;
		default:
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			break;
		}

		GlStateManager.translate(-0.5F, -0.5F, -0.5F);

		float lidangle = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
		lidangle = 1F - lidangle;
		lidangle = 1F - lidangle * lidangle * lidangle;

		if (type.isTransparent()) {
			GlStateManager.scale(1F, 0.99F, 1F);
		}

		model.chestLid.rotateAngleX = -lidangle * halfPI;
		model.renderAll();

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}

		if (type == IronChestType.CRYSTAL) {
			GlStateManager.enableCull();
		}

		GlStateManager.popMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);

		if (type.isTransparent() && te.getDistanceSq(rendererDispatcher.entityX, rendererDispatcher.entityY, rendererDispatcher.entityZ) < 128d) {
			random.setSeed(254L);

			float shiftX;
			float shiftY;
			float shiftZ;
			int shift = 0;
			float blockScale = 0.70F;
			float timeD = (float) (360D * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) - partialTicks;

			if (te.getTopItems().get(1).isEmpty()) {
				shift = 8;
				blockScale = 0.85F;
			}

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y, (float) z);

			customitem.setWorld(getWorld());
			customitem.hoverStart = 0F;

			for (ItemStack item : te.getTopItems()) {
				if (shift > shifts.length) {
					break;
				}

				if (item.isEmpty()) {
					shift++;
					continue;
				}

				shiftX = shifts[shift][0];
				shiftY = shifts[shift][1];
				shiftZ = shifts[shift][2];
				shift++;

				GlStateManager.pushMatrix();
				GlStateManager.translate(shiftX, shiftY, shiftZ);
				GlStateManager.rotate(timeD, 0F, 1F, 0F);
				GlStateManager.scale(blockScale, blockScale, blockScale);

				customitem.setEntityItemStack(item);

				if (itemRenderer == null) {
					itemRenderer = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()) {
						@Override
						public int getModelCount(ItemStack stack) {
							return SignedBytes.saturatedCast(Math.min(stack.getCount() / 32, 15) + 1);
						}

						@Override
						public boolean shouldBob() {
							return false;
						}

						@Override
						public boolean shouldSpreadItems() {
							return true;
						}
					};
				}

				itemRenderer.doRender(customitem, 0D, 0D, 0D, 0F, partialTicks);

				GlStateManager.popMatrix();
			}

			GlStateManager.popMatrix();
		}
	}
}