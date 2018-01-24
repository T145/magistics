package T145.magistics.client.gui.config;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.common.Magistics;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig {

	public ConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), Magistics.ID, false, false, "Logistical magic!");
		this.titleLine2 = "Another title!";
	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<>();

		// list.add(new ConfigGeneral());

		return list;
	}

	@Override
	public void initGui() {
		// add buttons and initialize fields here
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// do things like create animations, draw additional elements, etc. here
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		// process any additional buttons you may have added here
		super.actionPerformed(button);
	}
}
