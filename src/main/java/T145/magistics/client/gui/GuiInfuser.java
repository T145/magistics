package T145.magistics.client.gui;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerInfuser;
import T145.magistics.tiles.TileInfuser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiInfuser extends GuiContainer {
	private TileInfuser infuser;

	public GuiInfuser(InventoryPlayer player, TileInfuser tile) {
		super(new ContainerInfuser(player, tile));
		infuser = tile;
		ySize = 239;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(infuser.getDisplayName().getFormattedText(), 8, 5, infuser.isDark() ? 6307936 : 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int scaling = 0;
		int minX = (width - xSize) / 2;
		int minY = (height - ySize) / 2;

		GL11.glColor4f(1F, 1F, 1F, 1F);

		if (infuser.isDark()) {
			mc.getTextureManager().bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser_dark.png"));
			drawTexturedModalRect(minX, minY, 0, 0, xSize, ySize);

			if (infuser.isCrafting()) {
				scaling = infuser.getCookProgressScaled(46);
				int var8 = infuser.getDarkCookProgressScaled(46);
				drawTexturedModalRect(minX + 158, minY + 151 - scaling, 176, 46 - scaling, 6, scaling);
				drawTexturedModalRect(minX + 164, minY + 151 - var8, 182, 46 - var8, 6, var8);
			}

			drawTexturedModalRect(minX + 160, minY + 8, 192, mc.theWorld.getMoonPhase() * 8, 8, 8);
		} else {
			mc.getTextureManager().bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser.png"));
			drawTexturedModalRect(minX, minY, 0, 0, xSize, ySize);

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
}