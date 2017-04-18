package T145.magistics.client.gui;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerInfuserDark;
import T145.magistics.tiles.machines.TileInfuserDark;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInfuserDark extends GuiContainer {

	private final TileInfuserDark infuser;

	public GuiInfuserDark(InventoryPlayer playerInventory, TileInfuserDark infuser) {
		super(new ContainerInfuserDark(playerInventory, infuser));
		this.infuser = infuser;
		this.ySize = 239;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString("Dark Infuser", 8, 5, 6307936);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		mc.renderEngine.bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser_dark.png"));

		int offsetX = (width - xSize) / 2;
		int offsetY = (height - ySize) / 2;

		drawTexturedModalRect(offsetX, offsetY, 0, 0, xSize, ySize);

		if (infuser.isCrafting()) {
			int cookProgress = infuser.getCookProgressScaled(46);
			int darkCookProgress = infuser.getDarkCookProgressScaled(46);
			drawTexturedModalRect(offsetX + 158, offsetY + 151 - cookProgress, 176, 46 - cookProgress, 6, cookProgress);
			drawTexturedModalRect(offsetX + 164, offsetY + 151 - darkCookProgress, 182, 46 - darkCookProgress, 6, darkCookProgress);
		}

		drawTexturedModalRect(offsetX + 160, offsetY + 8, 192, mc.world.getMoonPhase() * 8, 8, 8);
	}
}