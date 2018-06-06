package si.um.feri.praktikum.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

public class MongoClientProvider {

	private static MongoClientProvider instance = new MongoClientProvider();

	// singleton for creating mongodb connection
	private MongoClientProvider() {
	}

	private MongoClient mongo = null;

	public static MongoClientProvider getInstance() {
		return instance;
	}

	public MongoClient getMongo() {
		MongoClientOptions options = new MongoClientOptions.Builder().connectionsPerHost(3)
				.maxConnectionIdleTime((60 * 1_000)).maxConnectionLifeTime((120 * 1_000)).build();

		// String serverName = "127.0.0.1";
		File file = new File("host");
		if (!file.exists() && !file.isDirectory())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String serverName = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String cur;
			while ((cur = br.readLine()) != null) {
				serverName = cur;
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int serverPort = 27017;
		ServerAddress sa = new ServerAddress(serverName.length() > 0 ? serverName : "localhost", serverPort);

		mongo = new MongoClient(sa, options);

		return mongo;
	}

}