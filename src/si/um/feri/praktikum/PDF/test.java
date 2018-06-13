package si.um.feri.praktikum.PDF;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class test {

	public static void main(String[] args) throws TransformerException, IOException, ParseException, JSONException, ParserConfigurationException, SAXException {

		String inputPath="C:\\Users\\Jakob\\Desktop\\fajl.json";
		String outputPath="C:\\Users\\Jakob\\Desktop\\faj1.xml";
		
		


		 //read JSONFile
		String json = JSONvXML.readFile(inputPath);
		
		//Convert JSON to XML
		String xml = JSONvXML.convert(json,"root");
		
		//write XML File
		JSONvXML.writeFile(outputPath,xml);
	
		
		XmlVPDF x = new XmlVPDF();
		
		x.vPDF(inputPath);
		
	} 

}
