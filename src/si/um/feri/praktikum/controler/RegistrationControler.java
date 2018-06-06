package si.um.feri.praktikum.controler;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import si.um.feri.praktikum.connection.MongoClientProvider;
import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.util.MongoUtil;
import si.um.feri.praktikum.util.StringUtil;

@SuppressWarnings("deprecation")
@ManagedBean(name = "registrationControler")
@SessionScoped
public class RegistrationControler {

	private User newUser = new User();

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

		return "welcomePage.xhtml";
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewuser(User newUser) {
		this.newUser = newUser;
	}

}
