package T145.magistics.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import T145.magistics.common.Magistics;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class GuiSettings extends GuiConfig {
	private static Configuration config = Magistics.config;

	public GuiSettings(GuiScreen parent) {
		super(parent, getConfigElements(), Magistics.modid, false, false, GuiConfig.getAbridgedConfigPath(config.toString()));
	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		for (String category : config.getCategoryNames())
			list.addAll(new ConfigElement(config.getCategory(category)).getChildElements());

		return list;
	}
}