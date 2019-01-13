package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FileUpload {
	private FileUpload()
	{ }
	
	// Where there are different buttons i.e. one for choosing the file and another is for uploading the chosen file
	public static void usingSendKeys(WebDriver driver, By locatorValueOfChooseFile, By locatorValueOfUpload, String filePath) throws FileNotFoundException {
		WebElement chooseFileButton = WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, locatorValueOfChooseFile);
		if(checkFilePath(filePath))
			chooseFileButton.sendKeys(filePath);
		else
			throw new FileNotFoundException("No file found. Please provide a valid file path");
		WebElement uploadButton = WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, locatorValueOfUpload);
		uploadButton.click();
		WaitsForWebElement.waitFor(2000);
	}
	
	
	public static void usingRobotClass(WebDriver driver, By locatorValueOfUpload, String filePath) throws FileNotFoundException {
		WebElement uploadButton = WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, locatorValueOfUpload);
		uploadButton.click();
		WaitsForWebElement.waitFor(2000);
		if(checkFilePath(filePath))
			KeyboardActions.copyPasteValuesOnWindowPopups(filePath);
		else
			throw new FileNotFoundException("No file found. Please provide a valid file path");
	}
	
	// Where there is no textbox available to enter file path
	public static void usingAutoIT(WebDriver driver, By locatorValueOfUpload, String autoITScriptPath) {
		WebElement uploadButton = WaitsForWebElement.findElementUsingWebDriverWait(driver, 5, locatorValueOfUpload);
		uploadButton.click();
		WaitsForWebElement.waitFor(2000);
		try {
			Runtime.getRuntime().exec(autoITScriptPath);
		}catch (IOException e) {
			System.out.println("Please check the script file. "+e.getMessage());
		}
	}
	
	private static boolean checkFilePath(String filePath) {
		File file = new File(filePath);
		if(file.exists() && file.isFile())
			return true;
		else
			return false;
	}
}
