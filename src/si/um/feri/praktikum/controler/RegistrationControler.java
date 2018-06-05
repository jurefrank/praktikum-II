package si.um.feri.praktikum.controler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

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
	private static int workload = 12;
	User newUser = new User();

	MongoClientProvider mongoClientProvider = MongoClientProvider.getInstance();
	MongoClient mongoClient = mongoClientProvider.getMongo();

	public String registerUser() {

		BasicDBObject document = new BasicDBObject();
		document.append("_id", new ObjectId());
		document.append("firstname", newUser.getFirstName());
		document.append("lastname", newUser.getLastName());
		document.append("mobilenumber", newUser.getMobileNumber());
		document.append("email", newUser.getEmail());
		newUser.setPassword(hashPassword(newUser.getPassword())); // rewritting user password with hashed password
		document.append("password", newUser.getPassword());
		document.append("id", newUser.getId());

		BasicDBObject add = new BasicDBObject("users", document);
		MongoDatabase db = mongoClient.getDatabase(database);
		MongoCollection<Document> collection = db.getCollection("users");

		// ensureIndex uporabi.
		try {
			System.out.println("try: " + add);
			collection.insertOne(new Document(add));
			newUser = null;

		} finally {
			mongoClient.close();

		}

		return "welcomePage.xhtml";
	}

	public static String hashPassword(String password) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password, salt);

		return hashed_password;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewuser(User newUser) {
		this.newUser = newUser;
	}

}
