package si.um.feri.praktikum.controler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

@ManagedBean(name="pdfController")
@SessionScoped
public class PDFController {
	
	
	public void download() throws IOException, FOPException, TransformerException {
		String fileName = "Verzija";
	    FacesContext fc = FacesContext.getCurrentInstance();
	    ExternalContext ec = fc.getExternalContext();

	    ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
	    ec.setResponseContentType("application/pdf");
	    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // Shrani z Pop-up window

	    OutputStream output = ec.getResponseOutputStream();
	    // Now you can write the InputStream of the file to the above OutputStream the usual way.
	    // ...
	    
	  
	  

	    fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}
	
	
	

}
