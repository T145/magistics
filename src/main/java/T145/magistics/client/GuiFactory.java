package T145.magistics.client;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import T145.magistics.client.gui.GuiSettings;
import cpw.mods.fml.client.IModGuiFactory;

public class GuiFactory implements IModGuiFactory {
	public void initialize(final Minecraft minecraftInstance) {}

	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return (Class<? extends GuiScreen>) GuiSettings.class;
	}

	public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(final IModGuiFactory.RuntimeOptionCategoryElement element) {
		return null;
	}
}