package si.um.feri.praktikum.controler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

import si.um.feri.praktikum.connection.MongoClientProvider;
import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.util.MongoUtil;
import si.um.feri.praktikum.util.StringUtil;

@ManagedBean()
@RequestScoped
public class LoginControler {

	private String email;
	private String password;
	private List<String> persons = new ArrayList<>();

	public String login() throws JSONException {

		FacesContext context = FacesContext.getCurrentInstance();

		if (email == null || password == null) {
			context.addMessage(null, new FacesMessage("Unknown login, try again"));
			email = null;
			password = null;
			return null;
		}
		password = StringUtil.hashSHA256(this.password);

		MongoCollection<Document> collection = MongoUtil.getUsersCollection();
		collection.createIndex(Indexes.ascending("id"));
		MongoClient mongoClient = MongoUtil.getMongoClient();
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("id", StringUtil.hashSHA256(email + password));
		MongoCursor<Document> cursor = collection.find(searchObject).iterator();

		while (cursor.hasNext()) {
			persons.add(cursor.next().toJson());
			String person = String.join(", ", persons);
			JSONObject result = new JSONObject(person);
			if (result != null && result.get("id").toString().equals(StringUtil.hashSHA256(email + password))) {
				context.getExternalContext().getSessionMap().put("user", email);
				password = null;
				email = null;
				mongoClient.close();
				return "recordForm.xhtml";
			}
		}
		mongoClient.close();
		return "return";
	}

	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;

	}

}
