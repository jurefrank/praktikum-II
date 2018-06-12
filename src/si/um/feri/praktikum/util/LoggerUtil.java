package si.um.feri.praktikum.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
	private final static Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());

	public static void init() {
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.FINER);
		LOGGER.addHandler(ch);
	}

	public static Logger getDefaultLogger(String className) {
		Logger log = Logger.getLogger(className);
		LogManager.getLogManager().reset();
		log.setUseParentHandlers(false);
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.SEVERE);
		log.addHandler(ch);
		
		File file = new File("log");
		if (!file.exists())
			if (file.mkdir())
				LOGGER.log(Level.FINE, "Log directory has been successfully created.");
		try {
			FileHandler fh = new FileHandler(
					"log"+  File.separator +"log_" +".log", true);//+ new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime())
			fh.setLevel(Level.FINE);
			log.addHandler(fh);
		} catch (SecurityException | IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return log;
	}
	
	public static void log2File() {
		
	}

	public static Logger getCustomLogger(String className, Level level, boolean useParent, FileHandler handler) {
		Logger log = Logger.getLogger(className);
		log.setLevel(level);
		log.setUseParentHandlers(useParent);
		log.addHandler(handler);
		return log;
	}

}