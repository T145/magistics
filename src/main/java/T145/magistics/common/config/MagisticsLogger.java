package T145.magistics.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import T145.magistics.common.Magistics;

public class MagisticsLogger {
	public static Logger logger = LogManager.getLogger(Magistics.modid);

	public static void log(String message) {
		logger.info(message);
	}

	public static void error(String message, Exception e) {
		logger.error(message, e);
	}
}