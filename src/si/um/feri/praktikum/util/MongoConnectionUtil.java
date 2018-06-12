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
	private final static Logger LOGGER = LoggerUtil.getProductionLogger();
	private static ConnectionSettings CONNECTIONSETTINGS = null;

	public static void setup() {

		File file = new File("host");
		if (!file.exists() && !file.isDirectory())
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
		List<String> connectionStrings = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String cur;
			while ((cur = br.readLine()) != null) {
				connectionStrings.add(cur);
			}
			br.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		if (connectionStrings.size() == 2 || connectionStrings.size() == 4) {
			ConnectionSettings cs = new ConnectionSettings();
			for (int i = 0; i < connectionStrings.size(); i++)
				if (i == 0)
					cs.setServerIp(connectionStrings.get(i));
				else if (i == 1) {
					try {
						cs.setServerPort(Integer.parseInt(connectionStrings.get(i)));
					} catch (NumberFormatException e) {
						LOGGER.log(Level.SEVERE, e.toString(), e);
					}
				} else if (i == 2)
					cs.setUserName(connectionStrings.get(i));
				else if (i == 3)
					cs.setPassword(connectionStrings.get(i));
			CONNECTIONSETTINGS = cs;
		} else {
			LOGGER.severe("Something is not right with host file. "
					+ "Please make sure first line contains ip adress, second line port,"
					+ " third if necessary username and fourth password.\n" + "Host file location: "
					+ file.getAbsolutePath());
			throw new IllegalArgumentException("Something is not right with host file. "
					+ "Please make sure first line contains ip adress, second line port,"
					+ " third if necessary username and fourth password.\n" + "Host file location: "
					+ file.getAbsolutePath());
		}
	}

	public static ConnectionSettings getConnectionSettings() {
		return CONNECTIONSETTINGS;
	}
}