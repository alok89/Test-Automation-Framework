package dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JSONFileReader extends FilesReader {

	private JsonParser parser;
	private JsonElement allTestData;
	
	private String dataName;
	private final String EXTENSION = "json";
	
 	protected JSONFileReader(String filePath, String dataName) {
		super(filePath);
		this.dataName = dataName;
	}

 	private JsonElement initializeJsonParser() {
		if(validateFile(filePath, EXTENSION)) {
			try {
				parser = new JsonParser();
				allTestData = parser.parse(new FileReader(new File(filePath)));
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return allTestData;
	}
 	
 	@Override
	public Object[][] readFile() {
		Object[][] array = null;
		try {
			JsonElement jsonObject = initializeJsonParser().getAsJsonObject().get(dataName);
			JsonArray jsonArray = jsonObject.getAsJsonArray(); 
			array = new Object[jsonArray.size()][1];
			System.out.println("Number of Test Data : "+array.length);
			
			for(int i=0; i<jsonArray.size(); i++) {
				JsonElement data = jsonArray.get(i);
				array[i][0] = addValuesToMap(data);
			}
		} catch (JsonIOException | JsonSyntaxException e) {
			e.printStackTrace();
		} 
		return array;
	}
	
	@Override
	protected Map<String, String> addValuesToMap(Object object) {
		Map<String, String> map = new HashMap<>();
		JsonElement data = (JsonElement) object;
		 for(Entry<String, JsonElement> dataEntries : data.getAsJsonObject().entrySet())
			 map.put(dataEntries.getKey(), dataEntries.getValue().getAsString());
		return map;
	}
}
