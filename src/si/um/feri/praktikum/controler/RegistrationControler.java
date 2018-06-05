package si.um.feri.praktikum.controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bson.Document;
import org.bson.types.ObjectId;

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

		// BUBU
		BasicDBObject document = new BasicDBObject();
		document.append("_id", new ObjectId());
		document.append("firstname", newUser.getFirstName());
		document.append("lastname", newUser.getLastName());
		document.append("mobilenumber", newUser.getMobileNumber());
		document.append("email", newUser.getEmail());
		document.append("password", newUser.getPassword());

		BasicDBObject add = new BasicDBObject("users", document);
		MongoDatabase db = mongoClient.getDatabase(database);
		MongoCollection<Document> collection = db.getCollection("users");

		try {
			System.out.println("try: " + add);
			collection.insertOne(new Document(add));
			newUser = null;

		} finally {
			mongoClient.close();

		}

		return "welcomePage.xhtml";
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewuser(User newUser) {
		this.newUser = newUser;
	}

}
