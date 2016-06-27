package T145.magistics.client.render.tiles;

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

import T145.magistics.tiles.TileChestHungryMetal;

import com.google.common.primitives.SignedBytes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.IronChestType;

@SideOnly(Side.CLIENT)
public class RenderChestHungryMetal extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderChestHungryMetal();

	protected final ModelChest model = new ModelChest();
	private Random rand = new Random();

	private RenderItem itemRenderer;
	private static float shifts[][] = {
		{ 0.3F, 0.45F, 0.3F },
		{ 0.7F, 0.45F, 0.3F },
		{ 0.3F, 0.45F, 0.7F },
		{ 0.7F, 0.45F, 0.7F },
		{ 0.3F, 0.1F, 0.3F },
		{ 0.7F, 0.1F, 0.3F },
		{ 0.3F, 0.1F, 0.7F },
		{ 0.7F, 0.1F, 0.7F },
		{ 0.5F, 0.32F, 0.5F }
	};

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

		for (IronChestType type : IronChestType.values())
			if (type != type.WOOD)
				textures[type.ordinal()] = new ResourceLocation("magistics", "textures/models/chest_hungry/" + getSimpleChestName(type) + ".png");

		return textures;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (tile != null && tile instanceof TileChestHungryMetal) {
			TileChestHungryMetal chest = (TileChestHungryMetal) tile;
			int facing = chest.getFacing();
			IronChestType type = chest.getType();

			bindTexture(getChestTextures()[type.ordinal()]);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glTranslatef((float) x, (float) y + 1F, (float) z + 1F);
			GL11.glScalef(1F, -1F, -1F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);

			short angle;
			switch (facing) {
			case 2:
				angle = 180;
				break;
			case 4:
				angle = 90;
				break;
			case 5:
				angle = -90;
				break;
			default:
				angle = 0;
				break;
			}

			GL11.glRotatef(angle, 0F, 1F, 0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			float lidAngle = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTick;
			lidAngle = 1F - lidAngle;
			lidAngle = 1F - lidAngle * lidAngle * lidAngle;

			model.chestLid.rotateAngleX = -((lidAngle * (float) Math.PI) / 2F);
			model.renderAll();

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();

			GL11.glColor4f(1F, 1F, 1F, 1F);

			if (type.isTransparent() && chest.getDistanceFrom(field_147501_a.field_147560_j, field_147501_a.field_147561_k, field_147501_a.field_147558_l) < 128d) {
				rand.setSeed(254L);
				float shiftX, shiftY, shiftZ, blockScale = 0.7F;
				int shift = 0;

				if (chest.getTopItemStacks()[1] == null) {
					shift = 8;
					blockScale = 0.85F;
				}

				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glTranslatef((float) x, (float) y, (float) z);

				EntityItem item = new EntityItem(field_147501_a.field_147550_f);
				item.hoverStart = 0f;

				for (ItemStack stack : chest.getTopItemStacks()) {
					if (shift > shifts.length)
						break;

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
					GL11.glRotatef((float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL), 0F, 1F, 0F);
					GL11.glScalef(blockScale, blockScale, blockScale);

					item.setEntityItemStack(stack);
					itemRenderer.doRender(item, 0, 0, 0, 0, 0);

					GL11.glPopMatrix();
				}

				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
				GL11.glColor4f(1F, 1F, 1F, 1F);
			}
		}
	}
}