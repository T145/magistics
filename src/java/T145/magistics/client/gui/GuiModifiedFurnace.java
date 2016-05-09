package T145.magistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.containers.ContainerModifiedFurnace;
import T145.magistics.tiles.TileModifiedFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiModifiedFurnace extends GuiContainer {
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
	private TileModifiedFurnace tileFurnace;

	public GuiModifiedFurnace(InventoryPlayer inventory, TileModifiedFurnace tile) {
		super(new ContainerModifiedFurnace(inventory, tile));
		tileFurnace = tile;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		String s = tileFurnace.hasCustomInventoryName() ? tileFurnace.getInventoryName() : I18n.format(tileFurnace.getInventoryName(), new Object[0]);
		fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

		if (tileFurnace.isBurning()) {
			int i1 = tileFurnace.getBurnTimeRemainingScaled(13);
			drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
			i1 = tileFurnace.getCookProgressScaled(24);
			drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
		}
	}
}