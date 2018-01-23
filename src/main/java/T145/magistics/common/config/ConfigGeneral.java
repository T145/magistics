package T145.magistics.common.config;

import T145.magistics.common.Magistics;
import T145.magistics.common.config.base.ConfigBase;
import net.minecraftforge.common.config.Config;

@Config(modid = Magistics.ID, name = Magistics.NAME + "/General")
public class ConfigGeneral extends ConfigBase {

	// only for general properties, like gui styles, difficulty settings, etc.

	@Config.LangKey("config.general.blight.severity")
	@Config.Comment("0 - n00b mode; 1 - TC3-4; 2 - TC2; 3 - aids")
	public static short BLIGHT_SEVERITY = 1;
}