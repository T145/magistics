package T145.magistics.client.render.tiles;

import T145.magistics.client.lib.WorldChestRenderHelper;
import T145.magistics.tiles.TileChestHungryTrapped;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderChestHungryTrapped extends TileEntitySpecialRenderer {
	public static final RenderChestHungryTrapped INSTANCE = new RenderChestHungryTrapped();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (tile instanceof TileChestHungryTrapped) {
			TileChestHungryTrapped chest = (TileChestHungryTrapped) tile;
			bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/trapped.png"));
			WorldChestRenderHelper.preRenderChest((float) x, (float) y, (float) z);

			if (chest.hasWorldObj()) {
				WorldChestRenderHelper.rotateChest(chest.getBlockMetadata());
			}

			WorldChestRenderHelper.postRenderChest(chest.prevLidAngle, chest.lidAngle, partialTick);
		}
	}
}