package hu.hundevelopers.elysium.client.gui;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ElysiumGuiEmulator extends GuiScreen {
	public static ElysiumGuiEmulator instance;

	public ElysiumGui currentGui;
	public int mouseX, mouseY;
	public boolean mousePressed;

	public ElysiumGuiEmulator(ElysiumGui gui) {
		instance = this;
		this.currentGui = gui;
	}

	@Override
	protected void mouseClicked(int x, int y, int btn) {
		if (this.currentGui != null)
			this.currentGui.onMouseClicked(x, y, btn);
	}

	@Override
	protected void keyTyped(char c, int i) {
		if (this.currentGui != null)
			this.currentGui.onKeyTyped(c, i);
	}

	public void setGui(ElysiumGui gui) {
		if (this.currentGui != null)
			this.currentGui.close();
		if (gui != null)
			gui.setBounds(0, 0, this.width, this.height);
		this.currentGui = gui;
	}

	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height) {
		super.setWorldAndResolution(mc, width, height);
		if (this.currentGui != null)
			this.currentGui.setBounds(0, 0, this.width, this.height);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (this.currentGui != null)
			this.currentGui.update(this.mouseX, this.mouseY, this.mousePressed);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		super.drawScreen(mouseX, mouseY, partialTick);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mousePressed = Mouse.isButtonDown(0);
		if (this.currentGui != null)
			this.currentGui.render(partialTick);
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		if (this.currentGui != null)
			this.currentGui.close();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return this.currentGui != null && this.currentGui.pauseGame();
	}
}
