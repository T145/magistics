package T145.magistics.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import T145.magistics.common.Magistics;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class GuiSettings extends GuiConfig {
	public GuiSettings(GuiScreen parent) {
		super(parent, (List) getConfigElements(), Magistics.modid, false, false, GuiConfig.getAbridgedConfigPath(Magistics.proxy.config.toString()));
	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(Magistics.proxy.config.getCategory("general")).getChildElements());
		for (String category : Magistics.proxy.category)
			list.addAll(new ConfigElement(Magistics.proxy.config.getCategory(category)).getChildElements());
		return list;
	}
}