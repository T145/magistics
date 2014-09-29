package T145.magistics.client.renderers.tile;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.Random;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.lib.TextureHelper;
import T145.magistics.common.tiles.TileChestHungryMetal;

import com.google.common.primitives.SignedBytes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.IronChestType;

@SideOnly(Side.CLIENT)
public class TileChestHungryMetalRenderer extends TileEntitySpecialRenderer {
	public Random random = new Random();
	public RenderItem itemRenderer = new RenderItem() {
		@Override
		public byte getMiniBlockCount(ItemStack stack, byte original) {
			return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 15) + 1);
		}

		@Override
		public byte getMiniItemCount(ItemStack stack, byte original) {
			return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 7) + 1);
		}

		@Override
		public boolean shouldBob() {
			return false;
		}

		@Override
		public boolean shouldSpreadItems() {
			return false;
		}
	};
	public ModelChest chestModel = new ModelChest();

	public static float[][] shifts = { { 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F }, { 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F }, { 0.7F, 0.1F, 0.3F }, { 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F } };

	@Override
	public void renderTileEntityAt(TileEntity chestentity, double x, double y, double z, float partialTick) {
		renderChestAt((TileChestHungryMetal) chestentity, x, y, z, partialTick);
	}

	public TileChestHungryMetalRenderer() {
		itemRenderer.setRenderManager(RenderManager.instance);
	}

	public void renderChestAt(TileChestHungryMetal chest, double x, double y, double z, float partialTick) {
		if (chest == null)
			return;
		int facing = chest.getFacing();

		BlockChestHungryMetal.Types type = BlockChestHungryMetal.Types.values()[chest.getBlockMetadata()];
		ResourceLocation loc = TextureHelper.ironChestTextures.get(type);
		if (loc != null)
			bindTexture(loc);

		glPushMatrix();
		glEnable(GL12.GL_RESCALE_NORMAL);
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		glScalef(1.0F, -1F, -1F);
		glTranslatef(0.5F, 0.5F, 0.5F);
		int mod = 0;
		switch (facing) {
		case 2:
			mod = 180;
			break;
		case 4:
			mod = 90;
			break;
		case 5:
			mod = -90;
			break;
		}
		glRotatef(mod, 0.0F, 1.0F, 0.0F);
		glTranslatef(-0.5F, -0.5F, -0.5F);
		float lidangle = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTick;
		lidangle = 1.0F - lidangle;
		lidangle = 1.0F - lidangle * lidangle * lidangle;
		chestModel.chestLid.rotateAngleX = -(lidangle * (float) Math.PI / 2.0F);
		chestModel.renderAll();
		glDisable(GL12.GL_RESCALE_NORMAL);
		glPopMatrix();
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (IronChestType.values()[chest.getBlockMetadata()].isTransparent() && chest.getDistanceFrom(field_147501_a.field_147560_j, field_147501_a.field_147561_k, field_147501_a.field_147558_l) < 128d) {
			random.setSeed(254L);
			float shiftX, shiftY, shiftZ, blockScale = 0.7F;
			int shift = 0;
			if (chest.getTopItemStacks()[1] == null) {
				shift = 8;
				blockScale = 0.85F;
			}
			glPushMatrix();
			glDisable(GL11.GL_LIGHTING);
			glTranslatef((float) x, (float) y, (float) z);
			EntityItem customitem = new EntityItem(field_147501_a.field_147550_f);
			customitem.hoverStart = 0f;
			for (ItemStack item : chest.getTopItemStacks()) {
				if (shift > shifts.length)
					break;
				if (item == null) {
					shift++;
					continue;
				}
				shiftX = shifts[shift][0];
				shiftY = shifts[shift][1];
				shiftZ = shifts[shift][2];
				shift++;
				glPushMatrix();
				glTranslatef(shiftX, shiftY, shiftZ);
				glRotatef((float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL), 0.0F, 1.0F, 0.0F);
				glScalef(blockScale, blockScale, blockScale);
				customitem.setEntityItemStack(item);
				itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);
				glPopMatrix();
			}
			glEnable(GL11.GL_LIGHTING);
			glPopMatrix();
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}