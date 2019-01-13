package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		
		return false;
	}

}
