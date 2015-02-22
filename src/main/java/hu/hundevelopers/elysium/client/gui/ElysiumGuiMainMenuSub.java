package hu.hundevelopers.elysium.client.gui;

import hu.hundevelopers.elysium.render.RenderingHelper;

public class ElysiumGuiMainMenuSub extends ElysiumGui {
	public ElysiumGuiButton btnBack;

	public int margin, btnHeight;

	public ElysiumGuiMainMenuSub(ElysiumGui parent, int x, int y, int width, int height) {
		super(parent, x, y, width, height);
	}

	@Override
	public void setBounds(int x, int y, int height, int width) {
		super.setBounds(x, y, width, height);

		this.margin = ((ElysiumGuiMainMenu) this.parent).margin;
		this.btnHeight = ((ElysiumGuiMainMenu) this.parent).btnHeight;

		this.btnBack = new ElysiumGuiButton(this, this.x + this.width * 3 / 4 + this.margin, this.y + this.height - this.margin - this.btnHeight,
				this.width / 4 - this.margin * 2, this.btnHeight, "Back");
		this.subs.add(this.btnBack);
	}

	@Override
	public void update(int mouseX, int mouseY, boolean pressed) {
		super.update(mouseX, mouseY, pressed);

		if (this.btnBack.wasClicked())
			((ElysiumGuiMainMenu) this.parent).setSubGui(null);
	}

	@Override
	public void render(float partialTick) {
		RenderingHelper.setColor(0F, 0F, 0F, 0.75F);
		RenderingHelper.Gui.drawRect(this.x, this.y, this.width, this.height);
		super.render(partialTick);
	}
}
