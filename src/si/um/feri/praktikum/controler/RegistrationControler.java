package si.um.feri.praktikum.controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import si.um.feri.praktikum.connection.MongoClientProvider;
import si.um.feri.praktikum.entity.User;

@SuppressWarnings("deprecation")
@ManagedBean(name = "registrationControler")
@SessionScoped
public class RegistrationControler {
	final String database = "obdelavaDejavnostiDB";
	User newUser = new User();

	MongoClientProvider mongoClientProvider = MongoClientProvider.getInstance();
	MongoClient mongoClient = mongoClientProvider.getMongo();

	public String registerUser() {

		BasicDBObject document = new BasicDBObject("user", newUser);

		String jsonString = document.toJson();

		System.out.println("REGCON: " + jsonString);

		BasicDBObject add = BasicDBObject.parse(jsonString);
		MongoDatabase db = mongoClient.getDatabase(database);
		MongoCollection<Document> collection = db.getCollection("users");

		try {
			System.out.println("try: " + add);
			collection.insertOne(new Document(add));
			newUser = null;

		} finally {
			mongoClient.close();

		}

		return "login.xhtml";
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewuser(User newUser) {
		this.newUser = newUser;
	}

}
