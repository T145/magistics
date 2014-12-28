package T145.magistics.client.renderers.tile;

import java.util.Random;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.client.lib.TextureHelper;
import T145.magistics.common.tiles.TileChestHungryMetal;

import com.google.common.primitives.SignedBytes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.client.TileEntityIronChestRenderer;

@SideOnly(Side.CLIENT)
public class TileChestHungryMetalRenderer extends TileEntityIronChestRenderer {
	private ModelChest model = new ModelChest();
	private Random rand = new Random();
	private RenderItem itemRenderer = new RenderItem() {
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

	private static float[][] shifts = { { 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F }, { 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F }, { 0.7F, 0.1F, 0.3F }, { 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F } };

	public TileChestHungryMetalRenderer() {
		itemRenderer.setRenderManager(RenderManager.instance);
	}

	public void render(TileChestHungryMetal tile, double x, double y, double z, float partialTick) {
		if (tile == null)
			return;
		int facing = 3;
		IronChestType type = tile.getType();
		if (tile != null && tile.hasWorldObj())
			facing = tile.getFacing();

		bindTexture(TextureHelper.ironChestTextures[type.ordinal()]);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glTranslatef((float) x, (float) y + 1F, (float) z + 1F);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		int k = 0;
		if (facing == 2)
			k = 180;
		if (facing == 4)
			k = 90;
		if (facing == 5)
			k = -90;
		GL11.glRotatef(k, 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
		lidangle = 1F - lidangle;
		lidangle = 1F - lidangle * lidangle * lidangle;
		model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2F);
		model.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		if (type.isTransparent() && tile.getDistanceFrom(field_147501_a.field_147560_j, field_147501_a.field_147561_k, field_147501_a.field_147558_l) < 128d) {
			rand.setSeed(254L);
			float shiftX, shiftY, shiftZ, blockScale = 0.7F;
			int shift = 0;
			if (tile.getTopItemStacks()[1] == null) {
				shift = 8;
				blockScale = 0.85F;
			}
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glTranslatef((float) x, (float) y, (float) z);
			EntityItem customitem = new EntityItem(field_147501_a.field_147550_f);
			customitem.hoverStart = 0F;
			for (ItemStack item : tile.getTopItemStacks()) {
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
				GL11.glPushMatrix();
				GL11.glTranslatef(shiftX, shiftY, shiftZ);
				GL11.glRotatef((float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL), 0F, 1F, 0F);
				GL11.glScalef(blockScale, blockScale, blockScale);
				customitem.setEntityItemStack(item);
				itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);
				GL11.glPopMatrix();
			}
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick) {
		render((TileChestHungryMetal) tileentity, x, y, z, partialTick);
	}
}