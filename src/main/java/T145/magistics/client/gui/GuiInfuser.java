package T145.magistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerInfuser;
import T145.magistics.tiles.TileInfuser;

public class GuiInfuser extends GuiContainer {
	private TileInfuser infuser;

	public GuiInfuser(InventoryPlayer player, TileInfuser tile) {
		super(new ContainerInfuser(player, tile));
		infuser = tile;
		ySize = 239;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		fontRendererObj.drawString(infuser.getInventoryName(), 8, 5, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser.png"));
		int minX = (width - xSize) / 2;
		int minY = (height - ySize) / 2;
		drawTexturedModalRect(minX, minY, 0, 0, xSize, ySize);
		int scaling;

		if (infuser.isCrafting()) {
			scaling = infuser.getCookProgressScaled(46);
			drawTexturedModalRect(minX + 160, minY + 151 - scaling, 176, 46 - scaling, 9, scaling);
		}

		if (infuser.getBoost() > 0) {
			scaling = infuser.getBoostScaled();
			drawTexturedModalRect(minX + 161, minY + 38 - scaling, 192, 30 - scaling, 7, scaling);
		}
	}
}