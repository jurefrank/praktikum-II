package si.um.feri.praktikum.PDF;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import si.um.feri.praktikum.bean.EvidenceBean;

import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONvXML {

	
	public void Json2xml(org.json.simple.JSONObject jsonObject) throws JSONException, ParserConfigurationException, SAXException, IOException, TransformerException{
	/*	String xml = XML.toString(jsonObject);
		System.out.println(xml);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		
		TransformerFactory transformerFac = TransformerFactory.newInstance();
		Transformer transformer = transformerFac.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult result = new StreamResult(new File("C:\\Users\\Jakob\\Desktop\\fajl1.xml"));
		transformer.transform(source, result);
		*/

	}
	

	//Pretvori JSON datoteko v xml datoteko
	public static String convert(String json, String root) throws JSONException {
		org.json.JSONObject jsonFileObject = new org.json.JSONObject(json);
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<"+root+">" 
                + org.json.XML.toString(jsonFileObject) + "</"+root+">";
		
		return xml;
	}
	
	//Prebere JSON datoteko
	public static String readFile(String file) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStream in = new FileInputStream(file);
		Charset encoding = Charset.defaultCharset();
		
		Reader reader = new InputStreamReader(in,encoding);
		
		int r = 0;
		while((r=reader.read())!= -1)
		{
			char ch = (char) r;
			sb.append(ch);
		}
		in.close();
		reader.close();
		
		return sb.toString();	
	}
	
	
	//Shrani XML dtoteko
	public static void writeFile(String filepath, String output) throws IOException {
		FileWriter ofstream = new FileWriter(filepath);
		try(BufferedWriter out = new BufferedWriter(ofstream)){
			out.write(output);
		}
	}

}

