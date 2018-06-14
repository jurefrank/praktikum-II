package si.um.feri.praktikum.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import si.um.feri.praktikum.util.LoggerUtil;

/**
 * 
 * This class is used to provide instance with mongodb connection. It creates
 * MongoClient.
 *
 */
public class MongoClientProvider {
	private static final Logger LOGGER = LoggerUtil.getProductionLogger();

	private static MongoClientProvider instance = new MongoClientProvider();

	// singleton for creating mongodb connection
	private MongoClientProvider() {
	}

	private MongoClient mongo = null;

	/**
	 * @return Returns instance of this class. It is singleton because everyone have
	 *         to use the same connection.
	 */
	public static MongoClientProvider getInstance() {
		return instance;
	}

	/**
	 * @return Returns MongoClient with settings set from host file.
	 */
	public MongoClient getMongo() {
		MongoClientOptions options = new MongoClientOptions.Builder().connectionsPerHost(3)
				.maxConnectionIdleTime((60 * 1_000)).maxConnectionLifeTime((120 * 1_000)).build();

		// ConnectionSettings cs = MongoConnectionUtil.getConnectionSettings();
		String ip = null, username = null, password = null;
		int port = 0;
		File file = new File("host");
		List<String> connectionStrings = new ArrayList<>();
		try {
			System.out.println("KUKU");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String cur;
			while ((cur = br.readLine()) != null) {
				connectionStrings.add(cur);
				System.out.println(cur);
			}
			br.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}

		if (connectionStrings.size() == 2 || connectionStrings.size() == 4) {
			for (int i = 0; i < connectionStrings.size(); i++) {
				if (i == 0) {
					ip = connectionStrings.get(0);
				} else if (i == 1) {
					try {
						port = Integer.parseInt(connectionStrings.get(1));
					} catch (NumberFormatException e) {
						LOGGER.log(Level.SEVERE, e.toString(), e);
					}
				} else if (i == 2)
					username = connectionStrings.get(2);
				else if (i == 3)

					password = connectionStrings.get(3);
			}

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

		System.out.println("ciga" + ip + port);

		ServerAddress sa = new ServerAddress(ip, port);
		if (ip != null && port != 0) {

			// mongo = new MongoClient(sa, options);
			if (username != null && username.length() > 0) {
				MongoCredential credential = MongoCredential.createCredential(username, "admin",
						password.toCharArray());
				mongo = new MongoClient(sa, credential, options);

			} else {
				System.out.println("PRIDEM V SA OPTIONS");
				mongo = new MongoClient(sa, options);
			}
		}
		return mongo;
	}
}