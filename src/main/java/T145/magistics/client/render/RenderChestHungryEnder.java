package T145.magistics.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import T145.magistics.client.lib.WorldChestRenderHelper;
import T145.magistics.tiles.TileChestHungryEnder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChestHungryEnder extends TileEntitySpecialRenderer {
	public static final RenderChestHungryEnder INSTANCE = new RenderChestHungryEnder();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (tile instanceof TileChestHungryEnder) {
			TileChestHungryEnder chest = (TileChestHungryEnder) tile;
			bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/ender.png"));
			WorldChestRenderHelper.preRenderChest((float) x, (float) y, (float) z);

			if (chest.hasWorldObj()) {
				WorldChestRenderHelper.rotateChest(chest.getBlockMetadata());
			}

			WorldChestRenderHelper.postRenderChest(chest.prevLidAngle, chest.lidAngle, partialTick);
		}
	}
}