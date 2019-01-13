package pages;

import org.openqa.selenium.WebDriver;

import tests.TestBase;
import ui.WaitsForWebElement;

public abstract class PageBase extends TestBase {

	protected WebDriver driver;
	
	public PageBase(WebDriver driver) {
		this.driver = driver;
	}

	public abstract boolean isAt();
	
	public void refreshPage() {
		driver.navigate().refresh();
		WaitsForWebElement.waitFor(3000);
	}
	
	public void navigateBack() {
		driver.navigate().back();
		WaitsForWebElement.waitFor(3000);
	}
	
	public void navigateForward() {
		driver.navigate().forward();
		WaitsForWebElement.waitFor(3000);
	}

}
