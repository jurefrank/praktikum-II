package si.um.feri.praktikum.PDF;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;
import org.xml.sax.SAXException;

public class JSONvXML {
	public static String inputPath= "C:\\Users\\Jakob\\Desktop\\fajl.json"; 
	public static String outputPath= "C:\\Users\\Jakob\\Desktop\\fajl1.xml";
	
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
	
	
	public static String convert(String json, String root) throws JSONException {
		org.json.JSONObject jsonFileObject = new org.json.JSONObject(json);
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<"+root+">" 
                + org.json.XML.toString(jsonFileObject) + "</"+root+">";
		
		return xml;
	}
	
	public static String readFile(String filepath) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStream in = new FileInputStream(inputPath);
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
	
	public static void writeFile(String filepath, String output) throws IOException {
		FileWriter ofstream = new FileWriter(filepath);
		try(BufferedWriter out = new BufferedWriter(ofstream)){
			out.write(output);
		}
	}

}

