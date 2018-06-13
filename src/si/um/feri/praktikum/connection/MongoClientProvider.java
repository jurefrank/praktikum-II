package si.um.feri.praktikum.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import si.um.feri.praktikum.util.MongoConnectionUtil;

/**
 * 
 * This class is used to provide instance with mongodb connection. It creates
 * MongoClient.
 *
 */
public class MongoClientProvider {

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

		ConnectionSettings cs = MongoConnectionUtil.getConnectionSettings();
		System.out.println(cs.getServerIp() + cs.getServerPort());
		ServerAddress sa = new ServerAddress(cs.getServerIp(), cs.getServerPort());
		if (cs.getServerIp() != null && cs.getPassword() != null) {

			// mongo = new MongoClient(sa, options);
			if (cs.getUserName() != null && cs.getUserName().length() > 0) {
				MongoCredential credential = MongoCredential.createCredential(cs.getUserName(), "admin.system.users",
						cs.getPassword().toCharArray());
				mongo = new MongoClient(sa, credential, options);

			} else {
				mongo = new MongoClient(sa, options);
			}
		}
		return mongo;
	}
}