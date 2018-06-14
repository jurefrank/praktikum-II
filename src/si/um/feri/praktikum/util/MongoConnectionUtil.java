package si.um.feri.praktikum.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import si.um.feri.praktikum.connection.ConnectionSettings;

public class MongoConnectionUtil {
	private static MongoConnectionUtil instance = null;
	private final static Logger LOGGER = LoggerUtil.getProductionLogger();
	private ConnectionSettings CONNECTIONSETTINGS = new ConnectionSettings();

	public static MongoConnectionUtil getInstance() {
		if (instance == null)
			instance = new MongoConnectionUtil();
		return instance;
	}

	public MongoConnectionUtil() {

		setup();
	}

	public void setup() {
		System.out.println("SETUP");
		File file = new File("host");
		System.out.println(file.getAbsolutePath());
		if (!file.exists() && !file.isDirectory()) {
		
			try {
				file.createNewFile();
				throw new IllegalArgumentException("Exception was thrown because host "
						+ "file has just been created and host file has to be filled out.\n" + "Host file location: "
						+ file.getAbsolutePath());
			} catch (IllegalArgumentException | IOException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
				org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(MongoConnectionUtil.class);
				logger.fatal(e.toString(), e);
			}
		}

	}

	public ConnectionSettings getConnectionSettings() {
		return CONNECTIONSETTINGS;
	}
}