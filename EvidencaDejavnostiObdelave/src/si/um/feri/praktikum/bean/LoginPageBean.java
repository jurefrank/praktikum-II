package si.um.feri.praktikum.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

// "d6a34a5ad123804ca13477912ff62d72");
@ManagedBean (name ="loginPageBean")
@SessionScoped
public class LoginPageBean implements Serializable {
	private static final long serialVersionUID = -1611162265998907599L;

	public String getFacebookUrlAuth() {
		System.out.println("kuku");
		HttpSession session =
				(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String sessionId = session.getId();
		String appId = "532157647181482";
		String redirectUrl = "https://localhost:8080/EvidencaDejavnostiObdelave/faces/welcomePage.sec";
		String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
				+ appId+ "&redirect_uri=" + redirectUrl	+ "&scope=email&state=" + sessionId;
		System.out.println("return val " + returnValue);
		
		return "https://www.facebook.com/dialog/oauth?client_id="
				+ appId+ "&redirect_uri=" + redirectUrl	+ "&scope=email&state=" + sessionId;
	}

	public String getUserFromSession() {
		HttpSession session =
				(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String userName = (String) session.getAttribute("FACEBOOK_USER");
		if (userName != null) {
			return "Hello " + userName;
		}
		else {
			return "";
		}
	}
}
