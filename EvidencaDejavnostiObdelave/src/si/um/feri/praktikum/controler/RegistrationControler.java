package si.um.feri.praktikum.controler;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.interfaces.IUser;

@ManagedBean(name = "registrationControler")
@SessionScoped
public class RegistrationControler {
	private User newUser;
	private User selectedUser;
	private List<User> allUsers = new ArrayList<>();
	
	
	@EJB
	IUser ejb;
	
	public String saveUser() {
		ejb.save(newUser);
		newUser = new User();
		
		//naredi public key
		//naredi private key;
		
		
		return "index.xhtml";
	}
	
	public String findUser(User user) {
		
		ejb.find(user.getEmail());
		
		return "index.xhtml";
	}
	
	
	
	public String updateUser() {
		ejb.update(selectedUser);
		
		return "userProfile.xhtml";
	}
	
	
	
	
	

}
