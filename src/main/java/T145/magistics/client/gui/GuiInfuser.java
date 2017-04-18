package T145.magistics.client.gui;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerInfuser;
import T145.magistics.tiles.machines.TileInfuser;
import net.minecraft.client.gui.inventory.GuiContainer;
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
		this.ySize = 239;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString("Infuser", 8, 5, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		mc.renderEngine.bindTexture(new ResourceLocation("magistics", "textures/gui/gui_infuser.png"));

		int offsetX = (width - xSize) / 2;
		int offsetY = (height - ySize) / 2;

		drawTexturedModalRect(offsetX, offsetY, 0, 0, xSize, ySize);

		if (infuser.isCrafting()) {
			int cookProgress = infuser.getCookProgressScaled(46);
			drawTexturedModalRect(offsetX + 160, offsetY + 151 - cookProgress, 176, 46 - cookProgress, 9, cookProgress);
		}

		if (infuser.getBoost() > 0) {
			int boost = infuser.getBoostScaled();
			drawTexturedModalRect(offsetX + 161, offsetY + 38 - boost, 192, 30 - boost, 7, boost);
		}
	}
}