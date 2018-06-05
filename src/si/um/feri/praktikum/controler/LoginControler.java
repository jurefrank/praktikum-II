package si.um.feri.praktikum.controler;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import si.um.feri.praktikum.connection.MongoClientProvider;
import si.um.feri.praktikum.entity.User;

@ManagedBean
@RequestScoped
public class LoginControler {
	final String database = "obdelavaDejavnostiDB";
	private String email;
	private String password;
	MongoClientProvider mongoClientProvider = MongoClientProvider.getInstance();
	MongoClient mongoClient = mongoClientProvider.getMongo();
	
	
	
	
	
	
	public String login() {
	//Ni še fertik
		User user = null;
		MongoDatabase db = mongoClient.getDatabase(database);
		MongoCollection<Document> collection = db.getCollection("users");
		BasicDBObject index = new BasicDBObject();
		index.put("email", 1);
		index.put("password", 1);
		collection.createIndex(index);
		
		
		
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("Unknown login, try again"));
            email = null;
            password = null;
            return null;
        }else {

           context.getExternalContext().getSessionMap().put("user", user);
            return "userhome?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

	




}



