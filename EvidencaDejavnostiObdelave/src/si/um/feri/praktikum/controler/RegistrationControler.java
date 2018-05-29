package si.um.feri.praktikum.controler;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mongodb.MongoClient;

import si.um.feri.praktikum.connection.*;

@ManagedBean(name="registrationControler")
@SessionScoped
public class RegistrationControler {
	
	@EJB
	MongoClientProvider mongoClientProvider;
	
	

}
