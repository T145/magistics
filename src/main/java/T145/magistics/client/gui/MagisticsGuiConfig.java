package T145.magistics.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModConfig;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class MagisticsGuiConfig extends GuiConfig {
	private static Configuration config = ModConfig.getConfig();

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		for (String category : config.getCategoryNames())
			list.addAll(new ConfigElement(config.getCategory(category)).getChildElements());

		return list;
	}

	public MagisticsGuiConfig(GuiScreen parent) {
		super(parent, getConfigElements(), Magistics.MODID, false, false, getAbridgedConfigPath(config.toString()));
	}
}