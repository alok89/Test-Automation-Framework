package manageDrivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public abstract class DriversManager {

	protected WebDriver driver;
	
	protected abstract void startService();
	protected abstract void stopService();
	protected abstract void createDriver();

	public void configureBrowser(TimeUnit unit, int timeToLoadPage) {
		if(null != driver) {
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(timeToLoadPage, unit);
		}else
			System.out.println("Driver instance found to be null. Please instantiate the driver");
	}

	public WebDriver getDriver() {
		if(null == driver) {
			startService();
			createDriver();
		}
		return driver;
	}

	public void quitDriver() {
		if(null != driver) {
			driver.quit();
			System.out.println("Quitting the driver instance");
		}
	}

}
