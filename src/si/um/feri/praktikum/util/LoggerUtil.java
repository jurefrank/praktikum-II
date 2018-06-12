package si.um.feri.praktikum.util;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
	private final static Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());

	public static void init() {
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.FINER);
		LOGGER.addHandler(ch);
	}

	public static Logger getDefaultLogger(String className) {
		Logger log = Logger.getLogger(className);
		FileHandler fh = null;
		try {
			fh = new FileHandler(
					"log" + ".xml",// + new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()) +
					true);
			
		} catch (SecurityException | IOException e) {
			LOGGER.severe(e.toString());
			e.printStackTrace();
		}
		fh.setLevel(Level.FINER);
		log.addHandler(fh);
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