package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.lib.TextureHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.TileEntityIronChest;
import cpw.mods.ironchest.client.TileEntityIronChestRenderer;

@SideOnly(Side.CLIENT)
public class TileChestHungryMetalRenderer extends TileEntityIronChestRenderer {
	public ModelChest chestModel = new ModelChest();

	@Override
	public void render(TileEntityIronChest tile, double x, double y, double z, float partialTick) {
		if (MinecraftForgeClient.getRenderPass() == 0) {
			super.render(tile, x, y, z, partialTick);
		} else if (tile != null) {
			int facing = tile.getFacing();

			BlockChestHungryMetal.Types type = BlockChestHungryMetal.Types.values()[tile.getBlockMetadata()];
			ResourceLocation loc = TextureHelper.ironChestTextures.get(type);
			if (loc != null)
				bindTexture(loc);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GL11.glScalef(1.0F, -1F, -1F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			switch (facing) {
			case 2:
				GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
				break;
			case 3:
				GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
				break;
			case 4:
				GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
				break;
			case 5:
				GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
				break;
			}
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
			lidangle = 1.0F - lidangle;
			lidangle = 1.0F - lidangle * lidangle * lidangle;
			chestModel.chestLid.rotateAngleX = -(lidangle * (float) Math.PI / 2.0F);
			chestModel.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}
}