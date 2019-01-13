package dataProviders;

import java.io.File;
import java.util.Map;

public abstract class FilesReader {

protected String filePath;
	
	public FilesReader(String filePath) {
		this.filePath = filePath;
	}
	
	public abstract Object[][] readFile();
	protected abstract Map<String, String> addValuesToMap(Object object);
	
	public boolean validateFile(String filePath, String extension) {
		boolean isValid = false;
		File file = new File(filePath);
		System.out.println("Found file path : "+filePath+"\nFound Extension : "+extension);
		
		if(file.exists() && file.isFile()) {
			System.out.println("File has been validated successfully");
			String fileNameWithExtension = file.getName();
			int startIndex = fileNameWithExtension.indexOf(".");
			String fileNameExtn = fileNameWithExtension.substring(startIndex+1, fileNameWithExtension.length()); 
			if(extension.equals(fileNameExtn)) {
				isValid = true;
				System.out.println("File Extension has been validated successfully");
			}
		}
		return isValid;
	}
	
	public static FilesReader getReaderInstance(ReaderType type, String filePath, String excelSheetNameOrXmlDataTagNameOrJsonDataArrayName) {
		FilesReader fileReader = null; 

		switch(type) {
		case EXCEL :
			fileReader = new ExcelFileReader(filePath, excelSheetNameOrXmlDataTagNameOrJsonDataArrayName);
			break;

		case JSON :
			fileReader = new JSONFileReader(filePath, excelSheetNameOrXmlDataTagNameOrJsonDataArrayName);
			break;

		case XML :
			fileReader = new XMLFileReader(filePath, excelSheetNameOrXmlDataTagNameOrJsonDataArrayName);
			break;
		}
		return fileReader;
	}

}
