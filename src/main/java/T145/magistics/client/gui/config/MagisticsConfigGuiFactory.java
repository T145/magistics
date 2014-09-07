package T145.magistics.client.gui.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;

public class MagisticsConfigGuiFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return MagisticsConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
}