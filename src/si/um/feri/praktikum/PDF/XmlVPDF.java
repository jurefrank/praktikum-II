package si.um.feri.praktikum.PDF;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

public class XmlVPDF {

	public void vPDF(String xml, OutputStream out) throws TransformerException, IOException, SAXException, ParserConfigurationException {
		// xsl fo file
		File xsltFile = new File("C:\\Users\\Jakob\\Desktop\\template.xsl");
		
		// xml file with data
		StreamSource streamSource = new StreamSource(new StringReader(xml));//string to stream source

		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();



		// prints PDF datoteko
		//out = new java.io.FileOutputStream(new File("C:\\Users\\Jakob\\Desktop\\fajl.pdf"));

		try {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			Result res = new SAXResult(fop.getDefaultHandler());

			transformer.transform(streamSource, res);

		} finally {
			out.close();
		}

	}

}
