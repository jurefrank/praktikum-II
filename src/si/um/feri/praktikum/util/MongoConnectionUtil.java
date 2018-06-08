package si.um.feri.praktikum.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import si.um.feri.praktikum.connection.ConnectionSettings;

public class MongoConnectionUtil implements ServletContextListener {

	public static ConnectionSettings CONNECTIONSETTINGS = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// DONT NEED
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		File file = new File("host");
		if (!file.exists() && !file.isDirectory())
			try {
				file.createNewFile();
				throw new IllegalArgumentException("Exception was thrown because host "
						+ "file has just been created and host file has to be filled out.");
			} catch (IOException e) {
				e.printStackTrace();
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
			e.printStackTrace();
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
						e.printStackTrace();
						// log
						System.exit(1);
					}
				} else if (i == 2)
					cs.setUserName(connectionStrings.get(i));
				else if (i == 3)
					cs.setPassword(connectionStrings.get(i));
			CONNECTIONSETTINGS = cs;
		}

	}

}
