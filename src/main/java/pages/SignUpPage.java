package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ui.WaitsForWebElement;

public class SignUpPage extends PageBase {

	public SignUpPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "email")
	private WebElement userEmailAddress;
	
	@FindBy(how = How.ID, using = "username")
	private WebElement username;

	@FindBy(how = How.ID, using = "password")
	private WebElement password;
	
	@FindBy(how = How.CSS, using = "button[type='submit']")
	private WebElement signUpButton;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign in here")
	private WebElement signInHereLink;
	
	private static final String PAGE_TITLE = "Register a CrowdTesting account";
	
	@Override
	public boolean isAt() {
		boolean atSignUpPage = WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, 
				ExpectedConditions.titleContains(PAGE_TITLE));
		return atSignUpPage;
	}
	
	public void enterUserEmailAddress(String userEmailAddress) {
		WaitsForWebElement.findElementUsingWebDriverWait(driver, 10, this.userEmailAddress);
		this.userEmailAddress.sendKeys(userEmailAddress);
	}
	
	public void enterUserName(String username) {
		this.username.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public void signUp() {
		signUpButton.click();
		WaitsForWebElement.waitFor(3000);
	}
	
	public SignInPage gotoSignInPage() {
		signInHereLink.click();
		return PageFactory.initElements(driver, SignInPage.class);
	}

}
