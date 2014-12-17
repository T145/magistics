package T145.magistics.client.renderers.tile;

import java.lang.reflect.Field;

import net.minecraft.client.model.ModelChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.client.lib.TextureHelper;
import T145.magistics.common.blocks.BlockChestHungryIron;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.ironchest.TileEntityIronChest;
import cpw.mods.ironchest.client.TileEntityIronChestRenderer;

public class TileChestHungryIronRenderer extends TileEntityIronChestRenderer {
	private Field model = ReflectionHelper.findField(TileEntityIronChestRenderer.class, "model");

	@Override
	public void render(TileEntityIronChest tile, double x, double y, double z, float partialTick) {
		if (tile == null) {
			return;
		}
		int facing = tile.getFacing();

		BlockChestHungryIron.Types type = BlockChestHungryIron.Types.values()[tile.getBlockMetadata()];
		ResourceLocation loc = TextureHelper.ironChestTextures.get(type);
		if (loc != null)
			bindTexture(loc);

		GL11.glPushMatrix();
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		int k = 0;
		if (facing == 2) {
			k = 180;
		}
		if (facing == 3) {
			k = 0;
		}
		if (facing == 4) {
			k = 90;
		}
		if (facing == 5) {
			k = -90;
		}
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
		lidangle = 1.0F - lidangle;
		lidangle = 1.0F - lidangle * lidangle * lidangle;
		try {
			ModelChest chest = (ModelChest) model.get(this);
			chest.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
			chest.renderAll();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glPopMatrix();
	}
}