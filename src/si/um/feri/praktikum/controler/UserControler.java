package si.um.feri.praktikum.controler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Indexes;

import si.um.feri.praktikum.util.MongoUtil;
import si.um.feri.praktikum.util.StringUtil;

@ManagedBean
@SessionScoped
public class UserControler {
	
	private String email;
	private String password;
	private String newPassword;
	private List<String> persons = new ArrayList<>();
	FacesContext context = FacesContext.getCurrentInstance();
	
	
	
	public String updateUserPassword() throws JSONException {

		
		
		newPassword = StringUtil.hashSHA256(this.newPassword);
		MongoCollection<Document> collection = MongoUtil.getUsersCollection();
		collection.createIndex(Indexes.ascending("email"));
		MongoClient mongoClient = MongoUtil.getMongoClient();
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("email", email);
		System.out.println("search: "+ searchObject);
		MongoCursor<Document> cursor = collection.find(searchObject).iterator();
		
		while (cursor.hasNext()) {
			persons.add(cursor.next().toJson());
			String person = String.join(", ", persons);
			JSONObject result = new JSONObject(person);
			System.out.println("result:"+ result);
			if(result.get("password").equals(newPassword)) {
				context.addMessage(null, new FacesMessage("Password is the same!"));
				
				mongoClient.close();
				return "userProfile.xhtml";
			}
			BasicDBObject updatePassword = new BasicDBObject();
			
			updatePassword.append("$set", new BasicDBObject()
							.append("id", StringUtil.hashSHA256(email + newPassword)));
			
			collection.updateOne(searchObject, updatePassword);
			
			return "welcomePage.xhtml";
		}
		
		
		return "login.xhtml";
	}







	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	

}
