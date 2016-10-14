package T145.magistics.client.gui.config;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigMagistics extends GuiConfig {
	private static Configuration config = Magistics.config.get();

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		for (String category : config.getCategoryNames()) {
			list.addAll(new ConfigElement(config.getCategory(category)).getChildElements());
		}

		return list;
	}

	public GuiConfigMagistics(GuiScreen parent) {
		super(parent, getConfigElements(), Magistics.MODID, false, false, getAbridgedConfigPath(config.toString()));
	}
}