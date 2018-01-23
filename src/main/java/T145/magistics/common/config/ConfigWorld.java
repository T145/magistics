package T145.magistics.common.config;

import T145.magistics.common.Magistics;
import T145.magistics.common.config.base.ConfigBase;
import net.minecraftforge.common.config.Config;

@Config(modid = Magistics.ID, name = Magistics.NAME + "/World")
public class ConfigWorld extends ConfigBase {

	// for worldgen and other world properties

	@Config.LangKey("config.world.gen.overworld")
	@Config.Comment("Enable Overworld worldgen")
	public static boolean GEN_OVERWORLD = true;

	@Config.LangKey("config.world.gen.nether")
	@Config.Comment("Enable Nether worldgen")
	public static boolean GEN_NETHER = true;

	@Config.LangKey("config.world.gen.end")
	@Config.Comment("Enable End worldgen")
	public static boolean GEN_END = true;
}