package si.um.feri.praktikum.PDF;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;	

public class PreberiJSON {
 
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
	
		try
		{
			Object obj = parser.parse(new FileReader("file.json"));
			JSONObject jsonObject = (JSONObject) obj; 
			String name = (String) jsonObject.get("name");
			
			
			//loop skozi vec stvari v arrayu
			JSONArray array = (JSONArray) jsonObject.get("stvari");
			
			Iterator<String> iterator = array.iterator();
			
			while(iterator.hasNext()) {
				System.out.println("Stvar :" + iterator.next());
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
