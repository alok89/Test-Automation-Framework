package tests;

import org.testng.annotations.Test;

import pages.LandingPage;
import pages.SignInPage;
import pages.SignUpPage;

public class TestLandingPage extends TestBase {
	
	@Test(priority = 1)
	public void testNavigateToSignUpPage() {
		System.out.println("============== Starting Test : TestNavigateToSignUpPage =============");
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		boolean atLandingPage = landingPage.isAt();
		if(atLandingPage) {
			SignUpPage signUpPage = landingPage.goToSignUpPage();
			boolean atSignUpPage = signUpPage.isAt();
			if(atSignUpPage) {
				signUpPage.navigateBack();
			}
		}
		System.out.println("============== Executed Test : TestNavigateToSignUpPage =============");
	}
	
	@Test(priority = 2)
	public void testNavigateToSignInPage() {
		System.out.println("============== Starting Test : TestSignIn =============");
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		boolean atLandingPage = landingPage.isAt();
		if(atLandingPage) {
			SignInPage signInPage = landingPage.goToSignInPage();
			boolean atSignPage = signInPage.isAt();
			if(atSignPage)
				signInPage.signInUsingAccount("BitBucket");
		}
		System.out.println("============== Executed Test : TestSignIn =============");
	}
}
