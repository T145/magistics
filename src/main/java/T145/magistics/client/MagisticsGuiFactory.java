package T145.magistics.client;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import T145.magistics.client.gui.MagisticsGuiConfig;
import cpw.mods.fml.client.IModGuiFactory;

public class MagisticsGuiFactory implements IModGuiFactory {
	public void initialize(Minecraft instance) {}

	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return MagisticsGuiConfig.class;
	}

	public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element) {
		return null;
	}
}