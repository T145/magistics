package T145.magistics.client.gui.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.MagisticsConfig;
import cpw.mods.fml.client.config.GuiConfig;

public class MagisticsConfigGui extends GuiConfig {
	public MagisticsConfigGui(GuiScreen parent) {
		super(parent, new ConfigElement(MagisticsConfig.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Magistics.modid, false, false, GuiConfig.getAbridgedConfigPath(MagisticsConfig.config.toString()));
	}
}