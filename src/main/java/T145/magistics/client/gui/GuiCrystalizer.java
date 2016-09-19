package T145.magistics.client.gui;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerCrystalizer;
import T145.magistics.tiles.TileCrystalizer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCrystalizer extends GuiContainer {
	private TileCrystalizer crystalizerInventory;

	public GuiCrystalizer(InventoryPlayer var1, TileCrystalizer var2) {
		super(new ContainerCrystalizer(var1, var2));
		crystalizerInventory = var2;
		ySize = 239;
	}

	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		fontRendererObj.drawString("Crystalizer", 5, 5, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation("magistics", "textures/gui/gui_crystalizer.png"));
		int var5 = (width - xSize) / 2;
		int var6 = (height - ySize) / 2;
		drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
		int var7;

		if (crystalizerInventory.isCooking()) {
			var7 = crystalizerInventory.getCookProgressScaled(46);
			drawTexturedModalRect(var5 + 160, var6 + 151 - var7, 176, 46 - var7, 9, var7);
		}

		if (crystalizerInventory.boost > 0) {
			var7 = crystalizerInventory.getBoostScaled();
			drawTexturedModalRect(var5 + 161, var6 + 38 - var7, 192, 30 - var7, 7, var7);
		}
	}
}