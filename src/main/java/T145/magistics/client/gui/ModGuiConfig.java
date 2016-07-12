package T145.magistics.client.gui;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig {
	private static Configuration config = Magistics.configHandler.getConfig();

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		for (String category : config.getCategoryNames()) {
			list.addAll(new ConfigElement(config.getCategory(category)).getChildElements());
		}

		return list;
	}

	public ModGuiConfig(GuiScreen parent) {
		super(parent, getConfigElements(), Magistics.MODID, false, false, getAbridgedConfigPath(config.toString()));
	}
}