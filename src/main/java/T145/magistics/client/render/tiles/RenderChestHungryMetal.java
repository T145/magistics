package T145.magistics.client.render.tiles;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.client.lib.WorldChestRenderHelper;
import T145.magistics.tiles.TileChestHungryMetal;

import com.google.common.primitives.SignedBytes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;

@SideOnly(Side.CLIENT)
public class RenderChestHungryMetal extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderChestHungryMetal();

	private static float[][] shifts = {
		{ 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F },
		{ 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F }, { 0.7F, 0.1F, 0.3F },
		{ 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F }
	};

	private RenderItem itemRenderer;

	public RenderChestHungryMetal() {
		itemRenderer = new RenderItem() {
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

		itemRenderer.setRenderManager(RenderManager.instance);
	}

	public static String getSimpleChestName(IronChestType type) {
		return type.name().toLowerCase().replaceAll("[0-9]", "").replaceAll("chest", "");
	}

	public static ResourceLocation[] getChestTextures() {
		ResourceLocation[] textures = new ResourceLocation[IronChestType.values().length];

		for (IronChestType type : IronChestType.values()) {
			if (type != type.WOOD) {
				textures[type.ordinal()] = new ResourceLocation("magistics", "textures/models/chest_hungry/" + getSimpleChestName(type) + ".png");
			}
		}

		return textures;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (tile instanceof TileEntityIronChest) {
			TileChestHungryMetal chest = (TileChestHungryMetal) tile;
			bindTexture(getChestTextures()[chest.getBlockMetadata()]);
			WorldChestRenderHelper.preRenderChest((float) x, (float) y, (float) z);

			if (chest.hasWorldObj()) {
				WorldChestRenderHelper.rotateChest(chest.getFacing());
			}

			WorldChestRenderHelper.postRenderChest(chest.prevLidAngle, chest.lidAngle, partialTick);

			if (chest.hasWorldObj()) {
				renderCrystalChest(chest, (float) x, (float) y, (float) z);
			}
		}
	}

	private void renderCrystalChest(TileEntityIronChest tile, float x, float y, float z) {
		if (tile.getType().isTransparent() && tile.getDistanceFrom(field_147501_a.field_147560_j, field_147501_a.field_147561_k, field_147501_a.field_147558_l) < 128d) {
			float shiftX;
			float shiftY;
			float shiftZ;
			int shift = 0;
			float blockScale = 0.70F;
			float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);

			if (tile.getTopItemStacks()[1] == null) {
				shift = 8;
				blockScale = 0.85F;
			}

			GL11.glPushMatrix();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glTranslatef((float) x, (float) y, (float) z);

			EntityItem item = new EntityItem(field_147501_a.field_147550_f);
			item.hoverStart = 0F;

			for (ItemStack stack : tile.getTopItemStacks()) {
				if (shift > shifts.length) {
					break;
				}

				if (stack == null) {
					shift++;
					continue;
				}

				shiftX = shifts[shift][0];
				shiftY = shifts[shift][1];
				shiftZ = shifts[shift][2];
				shift++;
				GL11.glPushMatrix();
				GL11.glTranslatef(shiftX, shiftY, shiftZ);
				GL11.glRotatef(timeD, 0F, 1F, 0F);
				GL11.glScalef(blockScale, blockScale, blockScale);
				item.setEntityItemStack(stack);
				itemRenderer.doRender(item, 0, 0, 0, 0, 0);
				GL11.glPopMatrix();
			}

			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
	}
}