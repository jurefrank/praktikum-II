package si.um.feri.praktikum.controler;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import si.um.feri.praktikum.entity.User;

@ManagedBean
@RequestScoped
public class LoginControler {
	private String email;
	private String password;
	
	
	
	public String login() {
		User user = null;
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("Unknown login, try again"));
            email = null;
            password = null;
            return null;
        } else {
            context.getExternalContext().getSessionMap().put("user", user);
            return "userhome?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

	




}



