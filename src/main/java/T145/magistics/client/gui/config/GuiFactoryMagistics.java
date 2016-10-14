package T145.magistics.client.gui.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class GuiFactoryMagistics implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraft) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return GuiConfigMagistics.class;
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