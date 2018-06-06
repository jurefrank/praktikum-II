package si.um.feri.praktikum.controler;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import si.um.feri.praktikum.util.MongoUtil;

@FacesValidator("emailExist")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		UIInput emailComponent = (UIInput) component.getAttributes().get("email");
		String email = (String) value;

		MongoClient mongoClient = MongoUtil.getMongoClient();
		MongoCollection<Document> collection = MongoUtil.getUsersCollection();
		BasicDBObject searchMail = new BasicDBObject("email", email);
		long counter = collection.count(searchMail);
		mongoClient.close();
		if (counter > 0) 
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email exists!", null));
			
			
	}

}

