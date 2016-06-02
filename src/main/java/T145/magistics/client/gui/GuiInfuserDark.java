package T145.magistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerInfuserDark;
import T145.magistics.tiles.TileInfuserDark;

public class GuiInfuserDark extends GuiContainer {
	private TileInfuserDark infuserInventory;

	public GuiInfuserDark(InventoryPlayer var1, TileInfuserDark var2) {
		super(new ContainerInfuserDark(var1, var2));
		infuserInventory = var2;
		ySize = 239;
	}

	protected void drawGuiContainerForegroundLayer() {
		fontRendererObj.drawString("Dark Infuser", 8, 5, 6307936);
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser_dark.png"));
		int var5 = (width - xSize) / 2;
		int var6 = (height - ySize) / 2;
		drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
		int var7;

		if (infuserInventory.isCooking()) {
			var7 = infuserInventory.getCookProgressScaled(46);
			int var8 = infuserInventory.getDarkCookProgressScaled(46);
			drawTexturedModalRect(var5 + 158, var6 + 151 - var7, 176, 46 - var7, 6, var7);
			drawTexturedModalRect(var5 + 164, var6 + 151 - var8, 182, 46 - var8, 6, var8);
		}

		var7 = mc.theWorld.getMoonPhase();
		drawTexturedModalRect(var5 + 160, var6 + 8, 192, var7 * 8, 8, 8);
	}
}