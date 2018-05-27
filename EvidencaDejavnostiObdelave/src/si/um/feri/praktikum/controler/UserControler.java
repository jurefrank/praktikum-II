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
public class UserControler {
	private User newUser = new User();
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
	
	
	

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	
	
	
	
	

}
