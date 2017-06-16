package T145.magistics.client.gui;

import T145.magistics.Magistics;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.tiles.crafting.TileInfuser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInfuser extends GuiContainer {

	private final TileInfuser infuser;

	public GuiInfuser(InventoryPlayer playerInventory, TileInfuser infuser) {
		super(new ContainerInfuser(playerInventory, infuser));
		this.infuser = infuser;
		ySize = 239;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(I18n.format("tile.infuser." + (infuser.isDark() ? "dark" : "light") + ".name"), 8, 5, infuser.isDark() ? 6307936 : 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int offsetX = (width - xSize) / 2;
		int offsetY = (height - ySize) / 2;
		int craftingProgress = infuser.getCookProgressScaled(46);

		GlStateManager.color(1F, 1F, 1F, 1F);

		if (infuser.isDark()) {
			mc.getTextureManager().bindTexture(new ResourceLocation(Magistics.MODID, "textures/gui/gui_infuser_dark.png"));

			drawTexturedModalRect(offsetX, offsetY, 0, 0, xSize, ySize);

			if (infuser.isCrafting()) {
				drawTexturedModalRect(offsetX + 158, offsetY + 151 - craftingProgress, 176, 46 - craftingProgress, 6, craftingProgress);
				drawTexturedModalRect(offsetX + 164, offsetY + 151 - craftingProgress, 182, 46 - craftingProgress, 6, craftingProgress);
			}

			drawTexturedModalRect(offsetX + 160, offsetY + 8, 192, mc.world.getMoonPhase() * 8, 8, 8);
		} else {
			mc.getTextureManager().bindTexture(new ResourceLocation(Magistics.MODID, "textures/gui/gui_infuser.png"));

			drawTexturedModalRect(offsetX, offsetY, 0, 0, xSize, ySize);

			if (infuser.isCrafting()) {
				drawTexturedModalRect(offsetX + 160, offsetY + 151 - craftingProgress, 176, 46 - craftingProgress, 9, craftingProgress);
			}

			if (infuser.getBoost() > 0) {
				int boostScaled = infuser.getBoostScaled();
				drawTexturedModalRect(offsetX + 161, offsetY + 38 - boostScaled, 192, 30 - boostScaled, 7, boostScaled);
			}
		}
	}
}