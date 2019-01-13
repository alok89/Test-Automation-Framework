package dataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader extends FilesReader{

	private Workbook workbook;
	private Sheet sheet;
	
	private String sheetName;
	private String fileTypeExtension;
	
	protected ExcelFileReader(String filePath, String sheetName) {
		super(filePath);
		this.sheetName = sheetName;
	}
	
	private Workbook initializeWorkbook()	{
		int startIndex = filePath.indexOf(".");
		fileTypeExtension = filePath.substring(startIndex+1, filePath.length());
		
		if(validateFile(filePath, fileTypeExtension)) {
			try(FileInputStream stream = new FileInputStream(new File(filePath))) {
				if("xlsx".equals(fileTypeExtension))
					workbook = new XSSFWorkbook(stream);
				else if("xls".equals(fileTypeExtension))
					workbook = new HSSFWorkbook(stream);
			} catch (IOException e) {
				System.out.println("\"Please check the file name and its extension type\""+e.getMessage());
			}
		}else 
			System.out.println("File doesn't exist at specified location. Please check the file location");
		
		return workbook;
	}

	private boolean isSheetPresent() {
		boolean isPresent = false;
		int numberOfSheets = 0;
		
		if(initializeWorkbook()!=null) {
			numberOfSheets = workbook.getNumberOfSheets();
			for(int i=0; i<numberOfSheets; i++) {
				sheet = workbook.getSheetAt(i);
				if((sheet.getSheetName()).equalsIgnoreCase(sheetName)) {
					isPresent = true;
					break;
				}	
			}
		}else
			System.out.println("No workbook found");

		return isPresent;
	}

	private int getNumberOfRows() {
		int iterateTillRowNumber = 0;
		if(isSheetPresent()) {
			System.out.println("Total number of rows which contains data : "+sheet.getPhysicalNumberOfRows());
			int secondLastRowNo = sheet.getLastRowNum();
			iterateTillRowNumber = secondLastRowNo+1;
			System.out.println("The last row number where data is present : "+iterateTillRowNumber);
		}
		return iterateTillRowNumber;
	}

	public Object[][] readFile() {
		Object[][] array = null;
		Row row;

		if(isSheetPresent()) {
			int count = 0;
			int rowCount = getNumberOfRows();
			array = new Object[sheet.getPhysicalNumberOfRows()-1][1];
			System.out.println("Number of Test Data : "+array.length);
			for (int i=1; i<rowCount; i++) {
				row = sheet.getRow(i);
				if(row!=null) {
					array[count][0] = addValuesToMap(i);
					count++;
				}	
			}
		}
		return array;
	}
	
	protected Map<String, String> addValuesToMap(Object object) {
		Map<String, String> map = new HashMap<>();
		int rowNumber = (int) object;
		int columnCount = sheet.getRow(rowNumber).getLastCellNum();
		for(int j=0; j<columnCount; j++) {
			Object value = getCellValue(rowNumber, j);
			map.put(sheet.getRow(0).getCell(j).toString(), value.toString());
		}
		return map;
	}

	private Object getCellValue(int rowNumber, int columnNumber) {
		Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);
		if(cell!=null) {
			CellType type = cell.getCellTypeEnum();
			if(type.equals(CellType.STRING))
				return cell.getStringCellValue();
			else if(type.equals(CellType.NUMERIC))
				return cell.getNumericCellValue();
			else if(type.equals(CellType.BOOLEAN))
				return cell.getBooleanCellValue();
			else if(type.equals(CellType.BLANK))
				return "";
		}
		return null;
	}

}
