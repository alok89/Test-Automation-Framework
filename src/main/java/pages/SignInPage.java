package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ui.WaitsForWebElement;

public class SignInPage extends PageBase {

	public SignInPage(WebDriver driver) {
		super(driver);
	}
	
	protected PageBase pageBase;
	private static final String PAGE_TITLE = "Sign in to your CrowdTesting account";
	
	@FindBy(how = How.ID, using = "username")
	private WebElement emailAddress;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement password;
	
	@FindBy(how = How.CSS, using = "button[type='submit']")
	private WebElement signInButton;
	
	@FindBy(how = How.LINK_TEXT, using = "Forgot your password?")
	private WebElement forgotPasswordLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign in with GitHub")
	private WebElement gitHubSignInButton;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign in with BitBucket")
	private WebElement bitBucketSignInButton;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign up now")
	private WebElement signUpNowLink;

	@Override
	public boolean isAt() {
		boolean atSignPage = WaitsForWebElement.findElementUsingWebDriverWait(driver, 15, 
				ExpectedConditions.titleContains(PAGE_TITLE));
		return atSignPage;
	}
	
	private void enterEmailAddress() {
		WaitsForWebElement.waitFor(2000);
		emailAddress.sendKeys(properties.getProperty("Username"));
	}
	
	private void enterPassword() {
		password.sendKeys(properties.getProperty("Password"));
	}
	
	private void signInUsingLeanTestingAccount() {
		enterEmailAddress();
		enterPassword();
		signInButton.click();
		WaitsForWebElement.waitFor(5000);
	}
	
	private void signInUsingGitHubAccount() {
		gitHubSignInButton.click();
	}
	
	private void signInUsingbitBucketAccount() {
		bitBucketSignInButton.click();
	}
	
	public SignUpPage goToSignUpPage() {
		signUpNowLink.click();
		return PageFactory.initElements(driver, SignUpPage.class);
	}

	public PageBase signInUsingAccount(String accountName) {
		if(accountName.equalsIgnoreCase("LeanTesting")) {
			signInUsingLeanTestingAccount();
			return PageFactory.initElements(driver, HomePage.class);
		}else if(accountName.equalsIgnoreCase("GitHub")) {
			signInUsingGitHubAccount();
			return pageBase;
		}else if(accountName.equalsIgnoreCase("BitBucket")) {
			signInUsingbitBucketAccount();
			return pageBase;
		}
		return pageBase;
	}

}
