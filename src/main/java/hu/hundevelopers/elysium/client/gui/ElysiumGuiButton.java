package hu.hundevelopers.elysium.client.gui;

import hu.hundevelopers.elysium.render.RenderingHelper;

import java.awt.Color;

public class ElysiumGuiButton extends ElysiumGui {
	public String text;
	protected int clicks, state;

	public ElysiumGuiButton(ElysiumGui parent, String text) {
		this(parent, 0, 0, 0, 0, text);
	}

	public ElysiumGuiButton(ElysiumGui parent, int x, int y, int width, int height, String text) {
		super(parent, x, y, width, height);
		this.text = text;
		this.clicks = 0;
	}

	public boolean wasClicked() {
		if (this.clicks > 0) {
			this.clicks--;
			return true;
		} else
			return false;
	}

	public boolean isPressed() {
		return this.state == 2;
	}

	public boolean isMouseOver() {
		return this.state == 1;
	}

	@Override
	public void onMouseClicked(int x, int y, int btn) {
		super.onMouseClicked(x, y, btn);
		if (btn == 0 && this.inBounds(x, y))
			this.clicks++;
	}

	@Override
	public void update(int mouseX, int mouseY, boolean pressed) {
		super.update(mouseX, mouseY, pressed);
		if (this.inBounds(mouseX, mouseY))
			if (pressed)
				this.state = 2;
			else
				this.state = 1;
		else
			this.state = 0;
	}

	@Override
	public void render(float partialTick) {
		super.render(partialTick);
		if (this.state == 1)
			RenderingHelper.setColor(0.1F, 0.1F, 0.1F, 0.75F);
		else
			RenderingHelper.setColor(0F, 0F, 0F, 0.75F);
		RenderingHelper.Gui.drawRect(this.x, this.y, this.width, this.height);
		RenderingHelper.Gui.drawCenteredStringWithShadow(this.x + this.width / 2, this.y + this.height / 2, Color.white.getRGB(), this.text);
	}
}
