package si.um.feri.praktikum.controler;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import si.um.feri.praktikum.connection.MongoClientProvider;
import si.um.feri.praktikum.entity.User;

@ManagedBean(name="registrationControler")
@SessionScoped
public class RegistrationControler {
	final String database ="obdelavaDejavnostiDB";
	User newUser = new User();
	
		MongoClientProvider mongoClientProvider  = MongoClientProvider.getInstance();
		MongoClient mongoClient = (MongoClient) mongoClientProvider.getMongo();
		
		
		
		
		
		
		
		
		
		
		public String registerUser() {
			BasicDBObject document = new BasicDBObject("user", newUser);
			try {
				MongoDatabase db = mongoClient.getDatabase(database);
				
			}finally {
				
				
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
