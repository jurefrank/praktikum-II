package si.um.feri.praktikum.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import si.um.feri.praktikum.util.MongoConnectionUtil;
import si.um.feri.praktikum.util.MongoUtil;

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

		ConnectionSettings cs = MongoConnectionUtil.getConnectionSettings();
		ServerAddress sa = new ServerAddress(cs.getServerIp(), cs.getServerPort());
		if (cs.getUserName() != null) {
			MongoCredential mc = MongoCredential.createCredential(cs.getUserName(), MongoUtil.DATABASE,
					cs.getPassword().toCharArray());
			mongo = new MongoClient(sa, mc, options);
		} else {
			mongo = new MongoClient(sa, options);
		}
		return mongo;
	}
}