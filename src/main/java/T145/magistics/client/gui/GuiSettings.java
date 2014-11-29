package T145.magistics.client.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.Settings;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;

public class GuiSettings implements IModGuiFactory {
	public class ConfigGui extends GuiConfig {
		public ConfigGui(GuiScreen gui) {
			super(gui, new ConfigElement(Settings.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Magistics.modid, false, false, GuiConfig.getAbridgedConfigPath(Settings.config.toString()));
		}
	}

	@Override
	public void initialize(Minecraft instance) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigGui.class;
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