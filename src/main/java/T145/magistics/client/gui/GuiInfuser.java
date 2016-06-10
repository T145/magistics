package T145.magistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerInfuser;
import T145.magistics.tiles.TileInfuser;

public class GuiInfuser extends GuiContainer {
	private TileInfuser infuserInventory;

	public GuiInfuser(InventoryPlayer var1, TileInfuser var2) {
		super(new ContainerInfuser(var1, var2));
		infuserInventory = var2;
		ySize = 239;
	}

	protected void drawGuiContainerForegroundLayer() {
		fontRendererObj.drawString("Infuser", 8, 5, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser.png"));
		int var5 = (width - xSize) / 2;
		int var6 = (height - ySize) / 2;
		drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
		int var7;

		if (infuserInventory.isCrafting()) {
			var7 = infuserInventory.getCookProgressScaled(46);
			drawTexturedModalRect(var5 + 160, var6 + 151 - var7, 176, 46 - var7, 9, var7);
		}

		if (infuserInventory.getBoost() > 0) {
			var7 = infuserInventory.getBoostScaled();
			drawTexturedModalRect(var5 + 161, var6 + 38 - var7, 192, 30 - var7, 7, var7);
		}
	}
}