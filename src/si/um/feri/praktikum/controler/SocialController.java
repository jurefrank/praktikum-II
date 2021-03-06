package si.um.feri.praktikum.controler;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.util.LoggerUtil;
import si.um.feri.praktikum.util.MongoConnectionUtil;
import si.um.feri.praktikum.util.MongoUtil;

@SuppressWarnings("deprecation")
@ManagedBean(name = "socialControler")
@SessionScoped
/**
 * 
 * Class used for managing social connections
 *
 */
public class SocialController implements Serializable {
	private final static Logger LOGGER = LoggerUtil.getDefaultLogger(MongoConnectionUtil.class.getName());

	private static final long serialVersionUID = 3658300628580536494L;
	private static SocialAuthManager socialManager;

	private Profile profile;
	private final String mainURL = "http://obdelavaDejavnosti.si:8080/praktikum-II/faces/login.xhtml";
	private final String redirectURL = "https://obdelavaDejavnosti.si:8443/praktikum-II/faces/redirect.xhtml";
	private final String provider = "facebook";

	/**
	 * Method for connection to Facebook
	 */
	public void connectToFacebook() {
		
		Properties prop = System.getProperties();
		prop.put("graph.facebook.com.consumer_key", "227414197855638");
		prop.put("graph.facebook.com.consumer_secret", "0cb6016366079a68428467f7c7696398");
		prop.put("graph.facebook.com.custom_permissions", "public_profile,email");
		SocialAuthConfig socialConfig = SocialAuthConfig.getDefault();
		try {
			socialConfig.load(prop);
			socialManager = new SocialAuthManager();
			socialManager.setSocialAuthConfig(socialConfig);
			String URLReturn = socialManager.getAuthenticationUrl(provider, redirectURL);
			FacesContext.getCurrentInstance().getExternalContext().redirect(URLReturn);
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}

	/**
	 * Method for getting user from Rest API
	 * 
	 * @throws Exception
	 */
	public void getUserProfile() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		MongoClient mongoClient = MongoUtil.getMongoClient();
		Map<String, String> parametersMap = SocialAuthUtil.getRequestParametersMap(request);

		if (socialManager != null) {
			AuthProvider provider = socialManager.connect(parametersMap);
			this.setProfile(provider.getUserProfile());

			// checking if we have this user already in database
			MongoCollection<Document> collection = MongoUtil.getCustomCollection("socialUsers");
			BasicDBObject searchMail = new BasicDBObject("email", profile.getEmail());
			long counter = collection.count(searchMail);
			if (counter > 0) {
				mongoClient.close();
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email exists!", null));

			}

		}
		// creating new collections of users that are using social login.
		MongoCollection<Document> socialCollection = MongoUtil.getCustomCollection("socialUsers");
		BasicDBObject document = new BasicDBObject();
		User user = new User(profile.getEmail());
		document.append("validatedId", profile.getValidatedId());
		document.append("firstName", profile.getFirstName());
		document.append("lastName", profile.getLastName());
		document.append("email", profile.getEmail());
		document.append("id", user.getId());
		try {
			// adding only social login users.
			System.out.println("try: " + document);
			socialCollection.insertOne(new Document(document));

		} finally {
			mongoClient.close();

		}
		
	
		context.getExternalContext().getSessionMap().put("user",user);
		FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
	}

	/**
	 * Method for invalidating Sessions
	 * 
	 * @return Redirects user to login.xhtml
	 */
	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.xhtml";
	}

	// GETTERS SETTERS
	/**
	 * Getter for getting user profile from another REST API
	 * 
	 * @return
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Sets profile for this class
	 * 
	 * @param profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
