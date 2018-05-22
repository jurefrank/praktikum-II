package si.um.feri.praktikum.facebook;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

@Named
@SessionScoped
public class FBConnect implements Serializable {


	private static final long serialVersionUID = 3658300628580536494L;
	
	private SocialAuthManager socialManager;
	private Profile profile;

	private final String mainURL = "https://localhost:8080/EvidencaDejavnostiObdelave/faces/welcomePage.xhtml";
	private final String redirectURL = "https://localhost:8080/EvidencaDejavnostiObdelave/faces/recordForm.xhtml";
	private final String provider = "facebook";

	public void connect() {
		Properties prop = System.getProperties();
		prop.put("graph.facebook.com.consumer_key", "532157647181482");
		prop.put("graph.facebook.com.consumer_secret", "d6a34a5ad123804ca13477912ff62d72");
		prop.put("graph.facebook.com.custom_permissions", "public_profile,email");

		SocialAuthConfig socialConfig = SocialAuthConfig.getDefault();
		try {
			socialConfig.load(prop);
			socialManager = new SocialAuthManager();
			socialManager.setSocialAuthConfig(socialConfig);
			String URLRetorno = socialManager.getAuthenticationUrl(provider, redirectURL);
			FacesContext.getCurrentInstance().getExternalContext().redirect(URLRetorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPerfilUsuario() throws Exception {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ex.getRequest();

		Map<String, String> parametros = SocialAuthUtil.getRequestParametersMap(request);

		if (socialManager != null) {
			AuthProvider provider = socialManager.connect(parametros);
			this.setProfile(provider.getUserProfile());

		}

		FacesContext.getCurrentInstance().getExternalContext().redirect(mainURL);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}