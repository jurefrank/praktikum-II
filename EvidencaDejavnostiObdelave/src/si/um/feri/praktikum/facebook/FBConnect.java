package si.um.feri.praktikum.facebook;

import java.io.IOException;
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

@Named(value = "userSession")
@SessionScoped
public class FBConnect implements Serializable {
    private SocialAuthManager manager;
    private String            originalURL;
    private String            providerID;
    private Profile           profile;
    
    
    public SocialAuthManager getManager() {
		return manager;
	}

	public void setManager(SocialAuthManager manager) {
		this.manager = manager;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public void socialConnect() throws Exception {
        // Put your keys and secrets from the providers here 
        Properties props = System.getProperties();
        props.put("graph.facebook.com.consumer_key", "532157647181482");
        props.put("graph.facebook.com.consumer_secret", "d6a34a5ad123804ca13477912ff62d72");
        // Define your custom permission if needed
        props.put("graph.facebook.com.custom_permissions", "public_profile,email,user_birthday");
        
        // Initiate required components
        SocialAuthConfig config = SocialAuthConfig.getDefault();
        try {
        config.load(props);
        manager = new SocialAuthManager();
        manager.setSocialAuthConfig(config);
        
        // 'successURL' is the page you'll be redirected to on successful login
        ExternalContext externalContext   = FacesContext.getCurrentInstance().getExternalContext();
        String          successURL        = "https://localhost:8080"+externalContext.getRequestContextPath() +"/faces/socialLoginSuccess.xhtml"; 
        String          authenticationURL = manager.getAuthenticationUrl(providerID, successURL);
        FacesContext.getCurrentInstance().getExternalContext().redirect(authenticationURL);
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void pullUserInfo() {
        try {
            // Pull user's data from the provider
            ExternalContext    externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request         = (HttpServletRequest) externalContext.getRequest();
            Map                map             = SocialAuthUtil.getRequestParametersMap(request);
            if (this.manager != null) {
                AuthProvider provider = manager.connect(map);
                this.setProfile(provider.getUserProfile());
            
                // Do what you want with the data (e.g. persist to the database, etc.)
                System.out.println("User's Social profile: " + profile);
            
                // Redirect the user back to where they have been before logging in
                FacesContext.getCurrentInstance().getExternalContext().redirect(originalURL);
            
            } //else FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "/faces/welcomePage.xhtml");
        } catch (Exception ex) {
            System.out.println("UserSession - Exception: " + ex.toString());
        } 
    }
    
    public void logOut() {
        try {
            // Disconnect from the provider
            manager.disconnectProvider(providerID);
            
            // Invalidate session
            ExternalContext    externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request         = (HttpServletRequest) externalContext.getRequest();
            //this.invalidateSession(request);
            
            // Redirect to home page
            FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "home.xhtml");
        } catch (IOException ex) {
            System.out.println("UserSessionBean - IOException: " + ex.toString());
        }
    }
}