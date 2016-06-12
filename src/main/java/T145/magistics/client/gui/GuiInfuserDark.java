package T145.magistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerInfuserDark;
import T145.magistics.tiles.TileInfuserDark;

public class GuiInfuserDark extends GuiContainer {
	private TileInfuserDark infuser;

	public GuiInfuserDark(InventoryPlayer player, TileInfuserDark tile) {
		super(new ContainerInfuserDark(player, tile));
		infuser = tile;
		ySize = 239;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		fontRendererObj.drawString(infuser.getInventoryName(), 8, 5, 6307936);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser_dark.png"));
		int minX = (width - xSize) / 2;
		int minY = (height - ySize) / 2;
		drawTexturedModalRect(minX, minY, 0, 0, xSize, ySize);
		int scaling;

		if (infuser.isCrafting()) {
			scaling = infuser.getCookProgressScaled(46);
			int var8 = infuser.getDarkCookProgressScaled(46);
			drawTexturedModalRect(minX + 158, minY + 151 - scaling, 176, 46 - scaling, 6, scaling);
			drawTexturedModalRect(minX + 164, minY + 151 - var8, 182, 46 - var8, 6, var8);
		}

		drawTexturedModalRect(minX + 160, minY + 8, 192, mc.theWorld.getMoonPhase() * 8, 8, 8);
	}
}