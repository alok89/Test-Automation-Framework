package tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import manageDrivers.DriverManagerFactory;
import manageDrivers.DriverType;
import manageDrivers.DriversManager;
import utility.ReadPropertiesFile;

public class TestBase {
	
	public WebDriver driver;
	private DriversManager manager;
	public static Properties properties;
	
	private static final String FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties";
	
	
	@BeforeSuite
	public void configure() {
		properties = ReadPropertiesFile.getInstance(FILE_PATH).loadProperties();
	}
	
	@BeforeMethod
	public void startBrowser() {
		manager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
		driver = manager.getDriver();
		manager.configureBrowser(TimeUnit.MINUTES, 2);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
	
	@AfterTest
	public void shutDown() {
		manager.quitDriver();
	}

}
