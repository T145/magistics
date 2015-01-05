package T145.magistics.common.config;

import net.minecraft.util.EnumChatFormatting;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import T145.magistics.common.Magistics;

public class Log {
	public static final String EMPTY_STRING = "";
	public static Logger logger = LogManager.getLogger(Magistics.modid);

	public static void error(String msg) {
		logger.log(Level.ERROR, msg);
	}

	public static void error(String msg, Object... data) {
		logger.log(Level.ERROR, msg, data);
	}

	public static void error(String msg, Throwable err) {
		if (Magistics.proxy.debug)
			logger.log(Level.ERROR, msg, err);
		else
			err.printStackTrace();
	}

	public static void warn(String msg) {
		logger.log(Level.WARN, msg);
	}

	public static void inform(String msg) {
		logger.log(Level.INFO, msg);
	}

	public static String rainbow(String input) {
		int inputLength = input.length();
		if (inputLength > 0) {
			String output = EMPTY_STRING;
			EnumChatFormatting[] color = {
					EnumChatFormatting.RED,
					EnumChatFormatting.GOLD,
					EnumChatFormatting.YELLOW,
					EnumChatFormatting.GREEN,
					EnumChatFormatting.AQUA,
					EnumChatFormatting.BLACK,
					EnumChatFormatting.LIGHT_PURPLE,
					EnumChatFormatting.DARK_PURPLE
			};
			for (int i = 0; i < inputLength; i++)
				output += color[i % 8] + input.substring(i, i + 1);
			return output;
		} else
			return EMPTY_STRING;
	}
}