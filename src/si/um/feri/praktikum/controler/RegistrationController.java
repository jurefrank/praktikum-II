package si.um.feri.praktikum.controler;

import javax.faces.bean.ManagedBean;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.util.MongoUtil;
import si.um.feri.praktikum.util.StringUtil;

@SuppressWarnings("deprecation")
@ManagedBean(name = "registrationControler")
/**
 * 
 * Class for managing registration to our services
 *
 */
public class RegistrationController {

	private User newUser = new User();

	/**
	 * Method for registering new users.
	 * 
	 * @return redirects users to login.xhtml
	 */
	public String registerUser() {
		MongoCollection<Document> collection = MongoUtil.getUsersCollection();
		MongoClient mongoClient = MongoUtil.getMongoClient();
		BasicDBObject document = new BasicDBObject();
		document.append("firstname", newUser.getFirstName());
		document.append("lastname", newUser.getLastName());
		document.append("mobilenumber", newUser.getMobileNumber());
		document.append("email", newUser.getEmail());
		newUser.setPassword(StringUtil.hashSHA256(newUser.getPassword()));
		document.append("password", newUser.getPassword());
		document.append("id", newUser.getId());

		try {
			System.out.println("try: " + document);
			collection.insertOne(new Document(document));
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
