package si.um.feri.praktikum.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import si.um.feri.praktikum.connection.MongoClientProvider;

public class MongoUtil {
	public static final String DATABASE = "obdelavaDejavnostiDB";

	public static MongoCollection<Document> getUsersCollection() {
		return getCustomCollection("users");
	}

	public static MongoCollection<Document> getCustomCollection(String collection) {
		return getMongoClient().getDatabase(DATABASE).getCollection(collection);
	}

	public static MongoClient getMongoClient() {
		return MongoClientProvider.getInstance().getMongo();
	}

}
