package ui;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Alerts {
	private Alerts()
	{ }
	
	public static void passValuesToAlertPopUp(WebDriver driver, String valuesToBeEntered) {
		Alert alert = switchToAlert(driver);
		if(alert!=null) {
			alert.sendKeys(valuesToBeEntered);
			alert.accept();
			System.out.println("Values passed to alert and accepted it");
			WaitsForWebElement.waitFor(2000);
		}
	}
	
	public static void actionOnAlerts(WebDriver driver, String action) {
		Alert alert = switchToAlert(driver);
		if(alert!=null) {
			if(action.equalsIgnoreCase("accept")) {
				alert.accept();
				System.out.println("Accepted the Alert by clicking on OK button");
			}
			else if(action.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
				System.out.println("Dismissed the Alert by clicking on Cancel button");
			}
			WaitsForWebElement.waitFor(2000);
		}
	}

	public static Alert switchToAlert(WebDriver driver) {
		Alert alertInstance = null;
		try {
			WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, ExpectedConditions.alertIsPresent());
			alertInstance = driver.switchTo().alert();
			WaitsForWebElement.waitFor(2000);
		}catch(NoAlertPresentException ex) {
			System.out.println("No Alert present on the webpage. "+ex.getMessage());
		}
		return alertInstance;	
	}
}
