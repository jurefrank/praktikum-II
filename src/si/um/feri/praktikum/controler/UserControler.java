package si.um.feri.praktikum.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.util.MongoUtil;
import si.um.feri.praktikum.util.StringUtil;

@ManagedBean
@SessionScoped
public class UserControler {
	private User user;
	private String newPassword;
	private List<String> persons = new ArrayList<>();
	
	public String updateUserPassword() throws JSONException {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		user = (User) sessionMap.get("user");
		System.out.println("user:" + user.getEmail());
		
		newPassword = StringUtil.hashSHA256(this.newPassword);
		MongoCollection<Document> collection = MongoUtil.getUsersCollection();
		collection.createIndex(Indexes.ascending("email"));
		
		MongoClient mongoClient = MongoUtil.getMongoClient();
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("email", user.getEmail());
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
							.append("id", StringUtil.hashSHA256(user.getEmail() + newPassword)));
			
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	

}
