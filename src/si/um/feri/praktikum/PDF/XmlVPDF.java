package si.um.feri.praktikum.PDF;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class XmlVPDF {

	public void vPDF() throws FOPException, TransformerException, IOException {
		//xsl fo file
		File xsltFile = new File("C:\\Users\\Jakob\\Desktop\\template.xsl");
		//xml file z data
		StreamSource xmlSource = new StreamSource(new File("C:\\Users\\Jakob\\Desktop\\faj1.xml"));
		
		
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		
		OutputStream out;
		
		out = new java.io.FileOutputStream(new File("C:\\Users\\Jakob\\Desktop\\fajl.pdf"));
		
		try {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
			
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
			
			Result res = new SAXResult(fop.getDefaultHandler());
			
			
			transformer.transform(xmlSource, res);
			
		} finally {
			out.close();
		}
		
	}
	
	
	public void convertToFO()  throws IOException, FOPException, TransformerException {
        // xsl fajl
        File xsltFile = new File("F:\\Temp\\template.xsl");
            
    
        
        // xml fajl za input
        StreamSource xmlSource = new StreamSource(new File("F:\\Temp\\Employees.xml"));
        

        OutputStream out;
        
        out = new java.io.FileOutputStream("F:\\Temp\\temp.fo");
    
        try {
           
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            

            Result res = new StreamResult(out);

            //zacetek xsl transformacije
            transformer.transform(xmlSource, res);


            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
	
	
}
