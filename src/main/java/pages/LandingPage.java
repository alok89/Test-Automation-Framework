package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ui.WaitsForWebElement;

public class LandingPage extends PageBase {

	public LandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.LINK_TEXT, using = "Sign in")
	private WebElement signInLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign up")
	private WebElement signUpLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Bug tracker")
	private WebElement bugTrackerLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Test cases")
	private WebElement testCasesLink;
	
	@FindBy(how = How.LINK_TEXT, using = "In-app bug reporting")
	private WebElement bugReportingLink;

	private static final String PAGE_TITLE = "Lean Testing";
	
	@Override
	public boolean isAt() {
		String title = driver.getTitle();
		return title.contains(PAGE_TITLE);
	}
	
	public void goTo() {
		driver.get(properties.getProperty("URL"));
	}
	
	public SignInPage goToSignInPage() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, signInLink);
		signInLink.click();
		return PageFactory.initElements(driver, SignInPage.class);
	}
	
	public SignUpPage goToSignUpPage() {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, signUpLink);
		signUpLink.click();
		WaitsForWebElement.waitFor(2000);
		return PageFactory.initElements(driver, SignUpPage.class);
	}
	
	public void openFeaturesDetailsPage(final String featureName) {
		switch(featureName) {
		case "Bug Tracker" :
			bugTrackerLink.click();
			break;
		case "Test Cases" :
			testCasesLink.click();
			break;
		case "In-app bug reporting" :
			bugReportingLink.click();
			break;
		default :
				System.out.println("Feature Name not matched ..!!");
		}
	}

}
