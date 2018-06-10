package si.um.feri.praktikum.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
	private final static Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());

	public static void init() {
		LOGGER.setLevel(Level.FINER);
		LOGGER.addHandler(new ConsoleHandler());
	}

	public static Logger getDefaultLogger(String className) {
		Logger log = Logger.getLogger(className);
		log.setUseParentHandlers(false);
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.SEVERE);
		ch.setFormatter(new SimpleFormatter());
		log.addHandler(ch);
		File file = new File("log");
		if (!file.exists())
			if (file.mkdir())
				LOGGER.log(Level.FINE, "Log directory has been successfully created.");
		try {
			// Handler can have different levels. So we can have console and file handler.
			// handler.setformatter
			// to change formatting default XML.
			FileHandler fh = new FileHandler(
					"log/log_" + new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime()), true);
			fh.setLevel(Level.FINE);
			log.addHandler(fh);
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