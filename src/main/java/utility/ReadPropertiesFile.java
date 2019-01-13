/**
 * 
 */
package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	
	private static ReadPropertiesFile readFile;
	private Properties properties;
	private String filePath;
	
	private ReadPropertiesFile(String filePath) {
		this.filePath = filePath;
	}
	
	public static ReadPropertiesFile getInstance(String filePath) {
		if(readFile == null)
			readFile = new ReadPropertiesFile(filePath);
		return readFile;
	}
	
	public Properties loadProperties() {
		File file = new File(filePath);
		if(file.exists() && file.isFile()) {
			try(BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
				properties = new Properties();
				properties.load(reader);
			} catch (IOException e) {
				System.out.println("Some problem occurred while reading the file : "+e.getMessage());
			}
		}else
			System.out.println("No Properties file found. Please check the file and its path");
		return properties;
	}

}
