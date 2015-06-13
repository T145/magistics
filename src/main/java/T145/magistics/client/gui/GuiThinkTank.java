package T145.magistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.common.containers.ContainerThinkTank;
import T145.magistics.common.tiles.TileThinkTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiThinkTank extends GuiContainer {
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("magistics:textures/gui/gui_thinktank.png");
	private TileThinkTank tank;

	public GuiThinkTank(InventoryPlayer playerInv, TileThinkTank tile) {
		super(new ContainerThinkTank(playerInv, tile));
		tank = tile;
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		GL11.glEnable(3042);
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

		drawTexturedModalRect(k + 91, l + 24, 198, 11, 32, tank.getCookProgressScaled(28));
		GL11.glDisable(3042);
	}
}