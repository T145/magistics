package T145.magistics.client.render.blocks;

import java.util.Random;

import com.google.common.primitives.SignedBytes;

import T145.magistics.Magistics;
import T145.magistics.blocks.devices.BlockChestHungryMetal;
import T145.magistics.client.lib.Render;
import T145.magistics.tiles.devices.TileChestHungryMetal;
import cpw.mods.ironchest.common.blocks.chest.IronChestType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChestHungryMetal extends TileEntitySpecialRenderer<TileChestHungryMetal> {

	private Random random = new Random();
	private ModelChest model = new ModelChest();
	private RenderEntityItem itemRenderer;

	private static float[][] shifts = { { 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F }, { 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F }, { 0.7F, 0.1F, 0.3F }, { 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F } };
	private static EntityItem customItem = new EntityItem(null);

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
	public void renderTileEntityAt(TileChestHungryMetal chest, double x, double y, double z, float partialTicks, int destroyStage) {
		if (chest == null || chest.isInvalid()) {
			return;
		}

		IronChestType type = chest.getType();

		GlStateManager.pushMatrix();

		if (type.isTransparent()) {
			GlStateManager.disableCull();
		}

		Render.chest(getChestTextures()[type.ordinal()], chest.getFacing(), chest.lidAngle, chest.prevLidAngle, x, y, z, partialTicks, destroyStage);

		if (type.isTransparent()) {
			GlStateManager.enableCull();
		}

		GlStateManager.popMatrix();

		if (type.isTransparent() && chest.getDistanceSq(rendererDispatcher.entityX, rendererDispatcher.entityY, rendererDispatcher.entityZ) < 128D) {
			random.setSeed(254L);

			float shiftX;
			float shiftY;
			float shiftZ;
			int shift = 0;
			float blockScale = 0.70F;
			float timeD = (float) (360D * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) - partialTicks;

			if (chest.getTopItems().get(1).isEmpty()) {
				shift = 8;
				blockScale = 0.85F;
			}

			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y, (float) z);

			customItem.setWorld(getWorld());
			customItem.hoverStart = 0F;

			for (ItemStack item : chest.getTopItems()) {
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

				customItem.setItem(item);

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

				itemRenderer.doRender(customItem, 0D, 0D, 0D, 0F, partialTicks);

				GlStateManager.popMatrix();
			}

			GlStateManager.popMatrix();
		}
	}
}