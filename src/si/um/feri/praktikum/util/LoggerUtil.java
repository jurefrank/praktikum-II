package si.um.feri.praktikum.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
	private final static Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());

	public static void init() {
		LOGGER.setLevel(Level.FINER);
	}

	public static Logger getDefaultLogger(String className) {
		Logger log = Logger.getLogger(className);
		log.setLevel(Level.FINER);
		log.setUseParentHandlers(false);
		File file = new File("log");
		if (!file.exists())
			if (file.mkdir())
				LOGGER.log(Level.FINE, "Log directory has been successfully created.");
		try {
			//Handler can have different levels. So we can have console and file handler. handler.setformatter
			//to change formatting default XML.
			log.addHandler(new FileHandler(
					"log/log_" + new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime(), true));
		} catch (SecurityException | IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}
		return log;
	}

	public static Logger getCustomLogger(String className, Level level, boolean useParent, FileHandler handler) {
		Logger log = Logger.getLogger(className);
		log.setLevel(level);
		log.setUseParentHandlers(useParent);
		log.addHandler(handler);
		return log;
	}

}
